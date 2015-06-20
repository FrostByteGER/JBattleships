/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.network.chat.ChatState;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ChatServer extends Thread {
	
	private ArrayList<ChatServerThread> clients = new ArrayList<>(0);
	private ServerSocket server = null;
	private volatile boolean endThread = false;
	public static final int MAX_LOGIN_COUNT = 3;

	/**
	 * 
	 * @param port
	 */
	public ChatServer(int port) {
		super("ChatServer-Thread");
		try {
			System.out.println("Binding to port " + port + ", please wait...");
			server = new ServerSocket(port);
			System.out.println("ChatServer started: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("Can't bind to port " + port + ": " + ioe.getMessage());
		}
	}

	@Override
	public void run() {
		while (!endThread) {
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
	private ChatServerThread findClient(String id) {
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
	public synchronized boolean authenticate(ChatServerThread client ,String input){
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
	 * @param input
	 */
	public synchronized void handle(String id, String input) {
		if(input.equals("/end")) {
			findClient(id).send("/end");
			removeClient(id);
		}else if(input.equals("/success")){
			findClient(id).send("/success");
		}else{
			System.out.println("Server received Message from " + id + ": " + input);
			for (ChatServerThread cst : clients){
				if(cst != findClient(id) && cst.getChatState() != ChatState.BANNED && cst.getChatState() != ChatState.LOGIN){
					System.out.println("Server sending message: " + input + " to: " + cst.getUsername());
					cst.send(id + ": " + input);
				}else{
					System.out.println("Skipped sending message to: " + cst.getUsername());
				}
				
			}
		}
	}

	/**
	 * 
	 * @param id
	 */
	public synchronized void removeClient(String id) {
		ChatServerThread toTerminate = findClient(id);
		System.out.println("Removing client thread " + id);
		toTerminate.closeConnection();
	}
	
	/**
	 * Closes the whole server with all connections.
	 */
	public synchronized void closeServer(){
		for(ChatServerThread cst : clients){
			cst.closeConnection();
		}
		endThread = true;
	}

	/**
	 * 
	 * @param socket
	 */
	private void addThread(Socket socket) {
		System.out.println("Client accepted: " + socket);
		ChatServerThread cst = new ChatServerThread(this, socket, socket.getInetAddress().toString());
		clients.add(cst);
		try {
			cst.open();
			cst.start();
		} catch (IOException ioe) {
			System.out.println("Error opening thread: " + ioe);
		}
	}
}
