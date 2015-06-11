/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.server.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ChatServer extends Thread {
	
	private ArrayList<ChatServerThread> clients = new ArrayList<>(0);
	private ServerSocket server = null;
	private boolean endThread = false;

	public ChatServer(int port) {
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
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
				endThread();
			}
		}
	}

	private ChatServerThread findClient(String id) {
		for (int i = 0; i < clients.size(); i++)
			if (clients.get(i).getUsername() == id){
				return clients.get(i);
			}
		return null;
	}
	
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

	public synchronized void handle(String id, String input) {
		if (input.equals("/end")) {
			findClient(id).send("/end");
			remove(id);
		} else if(input.startsWith("/auth")){
			
		}else{
			for (int i = 0; i < clients.size(); i++){
				System.out.println("Server received Message from Client " + id + ": " + input);
				clients.get(i).send(id + ": " + input);
			}
		}
	}

	public synchronized void remove(String id) {
		ChatServerThread toTerminate = findClient(id);
		System.out.println("Removing client thread " + id);
		try {
			toTerminate.close();
		} catch (IOException ioe) {
			System.out.println("Error closing thread: " + ioe);
		}
	}
	
	public synchronized void endThread(){
		endThread = true;
	}

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
