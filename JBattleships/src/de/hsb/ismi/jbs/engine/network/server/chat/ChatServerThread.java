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
	private String id = null;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;
	private boolean endThread = false;
	private ChatState state = ChatState.LOGIN;

	public ChatServerThread(ChatServer server, Socket socket, String id) {
		super();
		this.server = server;
		this.socket = socket;
		this.id = id;
	}

	public void send(String msg) {
		try {
			streamOut.writeUTF(msg);
			streamOut.flush();
		} catch (IOException ioe) {
			System.out.println(id + " ERROR sending: " + ioe.getMessage());
			// server.remove(ID);
			endThread();
		}
	}

	public void run() {
		System.out.println("Server Thread " + id + " running.");
		while (!endThread) {
			try {
				String input = streamIn.readUTF();
				switch(state){
					case LOGIN: 
						if(server.authenticate(this ,input)){
						state = ChatState.AUTHENTICATED;
						}
						break;
					case AUTHENTICATED:
						server.handle(id, input);
						break;
					case BANNED:
						server.remove(id);
						break;
				}
				
			} catch (IOException ioe) {
				System.out.println(id + " ERROR reading: " + ioe.getMessage());
				server.remove(id);
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
		endThread();
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

	/**
	 * @return the state
	 */
	public final ChatState getChatState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public final void setChatState(ChatState state) {
		this.state = state;
	}
	
	public String getUsername() {
		return id;
	}
	
	public synchronized void setUsername(String id) {
		this.id = id;
	}
}
