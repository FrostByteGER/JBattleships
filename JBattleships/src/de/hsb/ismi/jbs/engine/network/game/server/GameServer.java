/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameServer extends Thread {
	
	private int port = -1;
	private ServerSocket server = null;
	private ArrayList<GameServerThread> clients = new ArrayList<>(0);
	public static final int MAX_LOGIN_COUNT = 3;
	private volatile boolean endServer = false;


	public GameServer(int port){
		super("Game-Server");
		this.port = port;
		try {
			System.out.println("Binding to port " + this.port + ", please wait...");
			server = new ServerSocket(this.port);
			System.out.println("Gameserver started: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("Can't bind to port " + this.port + ": " + ioe.getMessage());
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
	
}
