/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import de.hsb.ismi.jbs.engine.network.chat.ChatState;
import de.hsb.ismi.jbs.engine.network.chat.server.ChatServer;
import de.hsb.ismi.jbs.engine.network.game.GameConnectionState;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameServerThread extends Thread {

	private GameServer server = null;
	private Socket socket = null;
	private String id = null;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;
	private boolean endServerThread = false;
	private GameConnectionState state = GameConnectionState.LOGIN;
	private int loginCount = 0;

	/**
	 * 
	 * @param server
	 * @param socket
	 * @param id
	 */
	public GameServerThread(GameServer server, Socket socket, String id) {
		super("GameServerConnection-Thread");
		setDaemon(true);
		this.server = server;
		this.socket = socket;
		this.id = id;
	}

	/**
	 * Sends the given String to the outputstream.
	 * @param msg The String to send.
	 */
	public void send(String msg) {
		try {
			streamOut.writeUTF(msg);
			streamOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.err.println("GameServerThread: Server Thread " + id + " running.");
		while (!endServerThread) {
			try {
				String input = streamIn.readUTF();
				System.err.println("GameServerThread: Received input: " + input);
				processInput(input);
				
			} catch (IOException ioe) {
				System.err.println("GameServerThread: " + id + " ERROR reading: " + ioe.getMessage());
				server.removeClient(id);
				closeConnection();
			}
		}
	}
	
	/**
	 * Processes the given input.
	 * @param input
	 */
	public void processInput(String input){
		switch(state){
			case LOGIN: 
				if(loginCount > ChatServer.MAX_LOGIN_COUNT){
					state = GameConnectionState.BANNED;
				}
				if(server.authenticate(this ,input)){
					state = GameConnectionState.AUTHENTICATED;
					loginCount++;
					send("/success");
				}else{
					loginCount++;
					send("/duplicateusername");
					server.removeClient(id);
					closeConnection();
				}
				break;
			case AUTHENTICATED:
				//server.handle(id, input);
				break;
			case BANNED:
				banPlayer();
				break;
			case CLOSED:
				closeConnection();
				break;
			case KICKED:
				kickPlayer();
				break;
			case FULL:
				send("/full");
				System.err.println("SENDING FULL");
				closeConnection();
		}
	}
	
	/**
	 * 
	 */
	public void kickPlayer(){
		send("/kick");
		server.removeClient(id);
		closeConnection();
	}
	
	/**
	 * 
	 */
	public void banPlayer(){
		send("/ban");
		server.removeClient(id);
		closeConnection();
	}


	/**
	 * Opens the connection.
	 * @throws IOException
	 */
	public void open() throws IOException {
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	/**
	 * Closes the connection and kills the thread.
	 */
	public synchronized void closeConnection(){
		try {
			endServerThread = true;
			if (socket != null){
				socket.close();
			}
			if (streamIn != null){
				streamIn.close();
			}
			if (streamOut != null){
				streamOut.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the state
	 */
	public final GameConnectionState getChatState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public synchronized final void setConnectionState(GameConnectionState state) {
		this.state = state;
	}
	
	public String getUsername() {
		return id;
	}
	
	public synchronized void setUsername(String id) {
		this.id = id;
	}
}
