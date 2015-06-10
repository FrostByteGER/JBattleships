/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.server.chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ChatServerThread extends Thread {

	private ChatServer server = null;
	private Socket socket = null;
	private int ID = -1;
	private String id = null;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;
	private boolean endThread = false;

	public ChatServerThread(ChatServer server, Socket socket) {
		super();
		this.server = server;
		this.socket = socket;
		this.ID = socket.getPort();
	}

	public void send(String msg) {
		try {
			streamOut.writeUTF(msg);
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
			// server.remove(ID);
			endThread();
		}
	}

	public int getID() {
		return ID;
	}

	public void run() {
		System.out.println("Server Thread " + ID + " running.");
		while (!endThread) {
			try {
				server.handle(ID, streamIn.readUTF());
			} catch (IOException ioe) {
				System.out.println(ID + " ERROR reading: " + ioe.getMessage());
				server.remove(ID);
				endThread();
			}
		}
	}

	public void open() throws IOException {
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	public synchronized void endThread(){
		endThread = true;
	}

	public void close() throws IOException {
		if (socket != null){
			socket.close();
		}
		if (streamIn != null){
			streamIn.close();
		}
		if (streamOut != null){
			streamOut.close();
		}
	}
}
