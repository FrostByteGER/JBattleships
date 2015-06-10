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

	private ChatServerThread findClient(int ID) {
		for (int i = 0; i < clients.size(); i++)
			if (clients.get(i).getID() == ID){
				return clients.get(i);
			}
		return null;
	}

	public synchronized void handle(int ID, String input) {
		if (input.equals(".bye")) {
			findClient(ID).send(".bye");
			remove(ID);
		} else{
			for (int i = 0; i < clients.size(); i++){
				clients.get(i).send(ID + ": " + input);
			}
		}
	}

	public synchronized void remove(int ID) {
		ChatServerThread toTerminate = findClient(ID);
		System.out.println("Removing client thread " + ID);
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
		ChatServerThread cst = new ChatServerThread(this, socket);
		clients.add(cst);
		try {
			cst.open();
			cst.start();
		} catch (IOException ioe) {
			System.out.println("Error opening thread: " + ioe);
		}

	}
}
