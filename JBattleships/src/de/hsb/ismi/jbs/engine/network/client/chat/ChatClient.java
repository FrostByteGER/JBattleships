package de.hsb.ismi.jbs.engine.network.client.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * 
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ChatClient extends Thread{
	
	private Socket socket = null;
	private DataOutputStream outputStream = null;
	private DataInputStream inputStream = null;
	private ArrayList<ClientMessageListener> listeners = new ArrayList<>(0);
	private String username = "undefined";
	private boolean endThread = false;
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @param username
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public ChatClient(String ip, int port, String username) throws UnknownHostException, IOException{
		//super("ChatClient-Thread");
		this.username = username;
		socket = new Socket(ip, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		sendAuthentification(username);
		System.out.println(getName());
		this.start();
	}
	
	public void addMessageListener(ClientMessageListener listener){
		this.listeners.add(listener);
	}
	
	/**
	 * Sends a message to the connected server.
	 * @param message
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws IOException{
		System.out.println("Client sending Message: " + message);
		outputStream.writeUTF(message);
		for(ClientMessageListener listener : listeners){
			listener.messageSent(message);
		}
	}
	
	@Deprecated
	public void sendAuthentification(String username, String password) throws IOException{
		sendMessage(username + "|" + password);
	}
	
	/**
	 * 
	 * @param username
	 * @throws IOException
	 */
	public void sendAuthentification(String username) throws IOException{
		sendMessage(username);
	}

	@Override
	public void run() {
		try{
			String message = null;
			while(!socket.isClosed()){
				message = inputStream.readUTF();
				System.out.println("Client " + getName() + "received Message: " + message);
				for(ClientMessageListener listener : listeners){
					listener.messageReceived(message);
				}
			}
		}catch(SocketException se){
			for(ClientMessageListener listener : listeners){
				listener.connectionLost(socket.getInetAddress().getHostAddress());
			}
			
		}catch(IOException ioe){
			for(ClientMessageListener listener : listeners){
				listener.connectionLost(socket.getInetAddress().getHostAddress());
			}

		}finally{
			closeConnection();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean closeConnection(){
		try {
			endThread = true;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String name) {
		this.username = name;
	}
	
	public boolean isClosed(){
		return socket.isClosed();
	}

	public boolean isActive() {
		return endThread;
	}
}
