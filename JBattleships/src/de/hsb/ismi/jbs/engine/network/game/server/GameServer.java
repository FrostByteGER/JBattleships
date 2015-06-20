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
import java.util.Vector;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameListener;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSRoundListener;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.engine.network.game.GameServerListener;
import de.hsb.ismi.jbs.engine.network.game.LobbyInfo;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameServer extends Thread implements GameServerListener{
	
	// Server Connection Data
	private InetAddress ip = InetAddress.getLoopbackAddress();
	private int gamePort = 15750;
	private int roundListenerPort = 15751;
	private int gameListenerPort = 15752;
	private int gameServerListenerPort = 15753;
	
	// Server Data
	private ServerSocket server = null;
	private ArrayList<GameServerThread> clients = new ArrayList<>(0);
	public static final int MAX_LOGIN_COUNT = 3;
	private volatile boolean endServer = false;	
	
	private Vector<ConnectionListener> connectionListeners = new Vector<>(0);
	
	//Game Data
	private GameManager gm = new GameManager();
	
	private JBSRoundListener roundLStub = null;
	private JBSGameListener gameLStub = null;
	private GameServerListener gameServerLStub = null;
	
	


	public GameServer(int gamePort, int roundListenerPort, int gameListenerPort, int gameServerListenerPort){
		super("Game-Server");
		this.gamePort = gamePort;
		this.roundListenerPort = roundListenerPort;
		this.gameListenerPort = gameListenerPort;
		this.gameServerListenerPort = gameServerListenerPort;

		try {
			System.out.println("Binding to gamePort " + this.gamePort + ", please wait...");
			server = new ServerSocket(this.gamePort);
			System.out.println("Gameserver started: " + server);
		} catch (IOException ioe) {
			System.out.println("Can't bind to gamePort " + this.gamePort + ": " + ioe.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!endServer) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
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
		for (int i = 0; i < clients.size(); i++)
			if (clients.get(i).getUsername().equals(id)){
				return clients.get(i);
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
		System.out.println("Client " + client.getUsername() + " requesting Authentification...");
		if(findClient(input) == null){
			findClient(client.getUsername()).setUsername(input);
			for(ConnectionListener cl : connectionListeners){
				cl.PlayerConnected(client, clients.size());
			}
			System.out.println("Authentification successfull.");
			return true;
		} else{
			System.out.println("Authentification Denied, Duplicate Username.");
			return false;
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	public synchronized void removeClient(String id) {
		GameServerThread toTerminate = findClient(id);
		System.out.println("Trying to remove Client " + id);
		if(toTerminate != null){
			System.out.println("Removing client thread " + id);
			toTerminate.closeConnection();
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
	 * @param socket
	 */
	private void addThread(Socket socket) {
		System.out.println("Client accepted: " + socket);
		GameServerThread cst = new GameServerThread(this, socket, socket.getInetAddress().toString());
		clients.add(cst);
		try {
			cst.open();
			cst.start();
		} catch (IOException ioe) {
			System.out.println("Error opening thread: " + ioe);
		}
	}
	
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

		System.out.println("Stub creation successfull!");
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
			try {
				return InetAddress.getLocalHost();
			} catch (UnknownHostException uhe) {
				uhe.printStackTrace();
			}
		}
		return ip;
		
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
	
	public void addConnectionListener(ConnectionListener cl){
		connectionListeners.addElement(cl);
	}
	
	public void removeConnectionListener(ConnectionListener cl){
		connectionListeners.remove(cl);
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
		JBSPlayer[] players = new JBSPlayer[clients.size()];
		for(int i = 0; i < clients.size(); i++){
			players[i] = new JBSPlayer(clients.get(i).getUsername());
		}
		return data;
	}

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.network.game.GameServerListener#getGameData()
	 */
	@Override
	public void getGameData() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
