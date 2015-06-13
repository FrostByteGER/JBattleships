/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.server.game;

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
public class GameServerThread extends Thread {

	private GameServer server = null;
	private Socket socket = null;
	private String id = null;
	private DataInputStream streamIn = null;
	private DataOutputStream streamOut = null;
	private boolean endServerThread = false;
	private GameServerState state = GameServerState.LOGIN;
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
		System.out.println("Server Thread " + id + " running.");
		while (!endServerThread) {
			try {
				String input = streamIn.readUTF();
				switch(state){
					case LOGIN: 
						if(loginCount > GameServer.MAX_LOGIN_COUNT){
							state = GameServerState.BANNED;
						}
						if(server.authenticate(this ,input)){
							state = GameServerState.AUTHENTICATED;
							loginCount++;
						}else{
							loginCount++;
						}
						break;
					case AUTHENTICATED:
						//TODO: uncomment!
						//server.handle(id, input);
						break;
					case BANNED:
						server.removeClient(id);
						break;
				}
				
			} catch (IOException ioe) {
				System.out.println(id + " ERROR reading: " + ioe.getMessage());
				server.removeClient(id);
				closeConnection();
			}
		}
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
	public final GameServerState getChatState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public synchronized final void setChatState(GameServerState state) {
		this.state = state;
	}
	
	public String getUsername() {
		return id;
	}
	
	public synchronized void setUsername(String id) {
		this.id = id;
	}
}
