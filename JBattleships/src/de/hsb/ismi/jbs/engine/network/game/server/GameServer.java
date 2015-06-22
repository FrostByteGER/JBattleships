/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameListener;
import de.hsb.ismi.jbs.engine.core.JBSRoundListener;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.engine.network.game.GameConnectionState;
import de.hsb.ismi.jbs.engine.network.game.GameServerListener;
import de.hsb.ismi.jbs.engine.network.game.LobbyInfo;
import de.hsb.ismi.jbs.engine.utility.Utility;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameServer extends Thread implements GameServerListener{
	
	// Server Connection Data
	/** Server IP address. */
	private InetAddress ip             = InetAddress.getLoopbackAddress();
	/** Main server socket port. */
	private int gamePort               = 15750;
	/** RoundListener RMI port. */
	private int roundListenerPort      = 15751;
	/** GameListener RMI port. */
	private int gameListenerPort       = 15752;
	/** GameServerListener RMI port. */
	private int gameServerListenerPort = 15753;
	
	// Server Data
	/** The main server socket. */
	private ServerSocket server                = null;
	/** Array of clients that have connected to the server. */
	private ArrayList<GameServerThread> clients = new ArrayList<>(MAX_PLAYER_COUNT);
	private boolean[] openSlots = {true, true, true, true, true, true, true, true};
	private boolean[] aiSlots = new boolean[8];
	/** The maximum login count till the server kills the connection to a client. <br> TODO: add arraylist of banned IPs. */
	public static final int MAX_LOGIN_COUNT    = 3;
	/** The maximum player/client count on this server. Connect more clients than this number, the server will reject the connection. */
	public static final int MAX_PLAYER_COUNT   = 8;
	/** */
	private int currentPlayerCount             = 0;
	/** Indicates wether the server closed its connections or not. */
	private volatile boolean endServer         = false;	
	/** Listeners that are registered on this server. */
	private List<ConnectionListener> listeners = Collections.synchronizedList(new ArrayList<>(0));
	
	//Game Data
	/** The game object. */
	private GameManager gm = new GameManager();
	
	// Server Stubs
	private JBSRoundListener roundLStub        = null;
	private JBSGameListener gameLStub          = null;
	private GameServerListener gameServerLStub = null;
	
	

	/**
	 * 
	 * @param gamePort
	 * @param roundListenerPort
	 * @param gameListenerPort
	 * @param gameServerListenerPort
	 */
	public GameServer(int gamePort, int roundListenerPort, int gameListenerPort, int gameServerListenerPort){
		super("Game-Server");
		this.gamePort = gamePort;
		this.roundListenerPort = roundListenerPort;
		this.gameListenerPort = gameListenerPort;
		this.gameServerListenerPort = gameServerListenerPort;
		clients = (ArrayList<GameServerThread>) Utility.fillList(clients, null, MAX_PLAYER_COUNT);
		try {
			System.err.println("GameServer: Binding to gamePort " + this.gamePort + ", please wait...");
			server = new ServerSocket(this.gamePort);
			System.err.println("GameServer: Gameserver started: " + server);
		} catch (IOException ioe) {
			System.err.println("GameServer: Can't bind to gamePort " + this.gamePort + ": " + ioe.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!endServer) {
			try {
				System.err.println("GameServer: Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.err.println("GameServer: Server accept error: " + ioe);
				closeServer();
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private GameServerThread findClient(String id) {
		for (int i = 0; i < clients.size(); i++){
			if (clients.get(i) != null &&clients.get(i).getUsername().equals(id)){
				return clients.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param client
	 * @param input
	 * @return
	 */
	public synchronized boolean authenticate(GameServerThread client ,String input){
		System.err.println("GameServer: Client " + client.getUsername() + " requesting Authentification...");
		if(findClient(input) == null){
			findClient(client.getUsername()).setUsername(input);
			synchronized(listeners){
				for(ConnectionListener cl : listeners){
					cl.PlayerConnected(client, clients.size());
				}
			}
			System.err.println("GameServer: Authentification successfull.");
			return true;
		} else{
			System.err.println("GameServer: Authentification Denied, Duplicate Username.");
			return false;
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public synchronized void removeClient(String id) {
		GameServerThread toTerminate = findClient(id);
		System.err.println("GameServer: Trying to remove Client " + id);
		if(toTerminate != null){
			System.err.println("GameServer: Removing client thread " + id);
			toTerminate.closeConnection();
			clients.set(clients.indexOf(toTerminate), null);
		}
	}
	
	/**
	 * Closes the whole server with all connections.
	 */
	public synchronized void closeServer(){
		for(GameServerThread cst : clients){
			cst.closeConnection();
		}
		endServer = true;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void kickPlayer(String id){
		GameServerThread client = findClient(id);
		if(client != null){
			client.setConnectionState(GameConnectionState.KICKED);
			client.kickPlayer();
		}
	}

	/**
	 * 
	 * @param socket
	 */
	private synchronized void addThread(Socket socket) {
		System.err.println("GameServer: Client accepted: " + socket);
		GameServerThread cst = new GameServerThread(this, socket, socket.getInetAddress().toString());
		try {
			cst.open();
			cst.start();
			int index = -1;
			for(int i = 0; i < clients.size(); i++){
				if(clients.get(i) == null && openSlots[i] == true && aiSlots[i] == false){
					index = i;
				}
			}
			
			if(index == -1){
				cst.setConnectionState(GameConnectionState.FULL);
				System.err.println("GameServer: Lobby full");
			}else{
				clients.set(index, cst);
				System.err.println("GameServer: Added new Client: " + cst.getUsername());
			}
			
		} catch (IOException ioe) {
			System.err.println("GameServer: Error opening thread: " + ioe);
		}
	}
	
	/**
	 * 
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws UnknownHostException
	 */
	public void startServer() throws RemoteException, MalformedURLException, UnknownHostException{
		JBSCoreGame game = JBattleships.game;
		
		try {
			RemoteServer.setLog(new FileOutputStream(new File("Data/Server.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// JBSRoundListener Stub
		LocateRegistry.createRegistry(roundListenerPort);
		roundLStub = (JBSRoundListener) UnicastRemoteObject.exportObject(game.getGameManager().getRoundManager(), roundListenerPort);
		Naming.rebind("rmi://" + ip.getHostAddress() + ":" + roundListenerPort + "/JBSRoundListener", roundLStub);
		
		// JBSGameListener Stub
		LocateRegistry.createRegistry(gameListenerPort);
		gameLStub = (JBSGameListener) UnicastRemoteObject.exportObject(game.getGameManager(), gameListenerPort);
		Naming.rebind("rmi://" + ip.getHostAddress() + ":" + gameListenerPort + "/JBSGameListener", gameLStub);

		LocateRegistry.createRegistry(gameServerListenerPort);
		gameServerLStub = (GameServerListener) UnicastRemoteObject.exportObject(this, gameServerListenerPort);
		Naming.rebind("rmi://" + ip.getHostAddress() + ":" + gameServerListenerPort + "/JBSGameServerListener", gameServerLStub);

		System.err.println("GameServer: Stub creation successfull!");
		start();
	}
	
	/**
	 * Returns the external IP of the Server. If echo-servers do not respond, returns the local address.<br>
	 * If something else happened, returns null.
	 * @return The external ip of the server, local address if no online connection or null of network-interface offline.
	 */
	public InetAddress getIPAddress(){
		InetAddress ip = null;
		URL url = null;
		try {
			 url = new URL("http://ipecho.net/plain");
		} catch (IOException ioe1) {
			ioe1.printStackTrace();
			try {
				 url = new URL("http://checkip.amazonaws.com/");
			} catch (IOException ioe2) {
				ioe2.printStackTrace();
			}
		}

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			ip = InetAddress.getByName(in.readLine());
			this.ip = ip;
		} catch (IOException ioe3) {
			ioe3.printStackTrace();
		}
		return this.ip;
		
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.network.game.GameServerListener#getLobbyData()
	 */
	@Override
	public LobbyInfo getLobbyData() throws RemoteException {
		LobbyInfo data = new LobbyInfo();
		Game g = gm.getGame();
		data.setDestroyers(g.getDestroyerCount());
		data.setFrigates(g.getFrigateCount());
		data.setCorvettes(g.getCorvetteCount());
		data.setSubmarines(g.getSubmarineCount());
		data.setFieldSize(g.getFieldSize());
		String[] players = new String[clients.size()];
		for(int i = 0; i < clients.size(); i++){
			if(openSlots[i] == true && clients.get(i) != null){
				String s = clients.get(i).getUsername();
				players[i] = s;
			}
			
		}
		data.setConnectedPlayers(players);
		data.setOpenSlots(openSlots);
		data.setAiSlots(aiSlots);
		return data;
	}

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.network.game.GameServerListener#getGameData()
	 */
	@Override
	public void getGameData() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.network.game.GameServerListener#setReady(boolean)
	 */
	@Override
	public void setReady(String id, boolean ready) throws RemoteException {
		GameServerThread client = findClient(id);
		if(client != null){
			client.setReady(ready);
		}
	}

	/**
	 * @return the gamePort
	 */
	public final int getGamePort() {
		return gamePort;
	}

	/**
	 * @param gamePort the gamePort to set
	 */
	public final void setGamePort(int gamePort) {
		this.gamePort = gamePort;
	}

	/**
	 * @return the roundListenerPort
	 */
	public final int getRoundListenerPort() {
		return roundListenerPort;
	}

	/**
	 * @param roundListenerPort the roundListenerPort to set
	 */
	public final void setRoundListenerPort(int roundListenerPort) {
		this.roundListenerPort = roundListenerPort;
	}

	/**
	 * @return the gameListenerPort
	 */
	public final int getGameListenerPort() {
		return gameListenerPort;
	}

	/**
	 * @param gameListenerPort the gameListenerPort to set
	 */
	public final void setGameListenerPort(int gameListenerPort) {
		this.gameListenerPort = gameListenerPort;
	}

	/**
	 * @return the ip
	 */
	public final InetAddress getServerIP() {
		return ip;
	}

	/**
	 * @return the gm
	 */
	public final GameManager getServerGameManager() {
		return gm;
	}

	/**
	 * @param gm the gm to set
	 */
	public final void setServerGameManager(GameManager gm) {
		this.gm = gm;
	}
	
	public synchronized void addConnectionListener(ConnectionListener cl){
		listeners.add(cl);
	}
	
	public synchronized void removeConnectionListener(ConnectionListener cl){
		listeners.remove(cl);
	}

	/**
	 * @return the currentPlayerCount
	 */
	public final int getCurrentPlayerCount() {
		return currentPlayerCount;
	}

	/**
	 * Sets the currentPlayerCount to the given value 
	 * @param currentPlayerCount the currentPlayerCount to set
	 * @throws IllegalArgumentException
	 */
	public final void setCurrentPlayerCount(int count) throws IllegalArgumentException{
		if(count >= 0 && count <= MAX_PLAYER_COUNT){
			this.currentPlayerCount = count;
		}else{
			throw new IllegalArgumentException("PlayerCount must be <= MAX_PLAYER_COUNT and >= 0.");
		}
		
	}
	
	public final void toggleSlot(int index, boolean open){
		openSlots[index] = open;
		if(!open){
			
		}
	}
	
	public final void toggleAISlot(int index, boolean ai){
		aiSlots[index] = ai;
		
	}
	
}
