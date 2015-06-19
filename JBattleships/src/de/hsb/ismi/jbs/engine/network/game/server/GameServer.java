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
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.GameListener;
import de.hsb.ismi.jbs.engine.core.RoundListener;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameServer extends Thread {
	
	private int gamePort = 15750;
	private int roundListenerPort = 15751;
	private int gameListenerPort = 15752;
	
	private InetAddress ip = null;

	private ServerSocket server = null;
	private ArrayList<GameServerThread> clients = new ArrayList<>(0);
	public static final int MAX_LOGIN_COUNT = 3;
	private volatile boolean endServer = false;	
	
	private GameManager gm;
	
	private RoundListener roundLStub;
	private GameListener gameLStub;
	


	public GameServer(int gamePort, int roundListenerPort, int gameListenerPort){
		super("Game-Server");
		this.gamePort = gamePort;
		this.roundListenerPort = roundListenerPort;
		this.gameListenerPort = gameListenerPort;

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
		System.out.println("Removing client thread " + id);
		toTerminate.closeConnection();
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
	
	public boolean startServer(){
		JBSCoreGame game = JBattleships.game;
		
		try {
			RemoteServer.setLog(new FileOutputStream(new File("Data/Server.log")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			// RoundListener Stub
			LocateRegistry.createRegistry(roundListenerPort);
			roundLStub = (RoundListener) UnicastRemoteObject.exportObject(game.getGameManager().getRoundManager(), roundListenerPort);
			Naming.bind("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + roundListenerPort + "/RoundListener", roundLStub);
			
			// GameListener Stub
			LocateRegistry.createRegistry(gameListenerPort);
			gameLStub = (GameListener) UnicastRemoteObject.exportObject(game.getGameManager(), gameListenerPort);
			Naming.bind("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + gameListenerPort + "/GameListener", gameLStub);
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
			return false;
		} catch(RemoteException re){
			re.printStackTrace();
			return false;
		} catch(AlreadyBoundException abe){
			abe.printStackTrace();
			return false;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("Stub creation successfull!");
		start();
		return true;
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
	
}
