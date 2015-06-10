package de.hsb.ismi.jbs.engine.network.client.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class ChatClient implements Runnable{
	
	private Socket socket;
	private DataOutputStream dout;
	private DataInputStream din;
	private ClientMessageListener listener;
	private String name;
	private boolean active;
	
	/*
	public ChatClient(String ip, int port){
		try{
			socket = new Socket(ip, port);
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());
			new Thread(this).start();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	*/
	
	public ChatClient(){
		socket = new Socket();
		active = false;
	}
	
	public ChatClient(String ip, int port, String username) throws UnknownHostException, IOException{
		this.name = username;
		socket = new Socket(ip, port);
		active = true;
		din = new DataInputStream(socket.getInputStream());
		dout = new DataOutputStream(socket.getOutputStream());
		//sendAuthentification(username, password);
		new Thread(this).start();
	}
	
	public void setMessageListener(ClientMessageListener listener){
		this.listener = listener;
	}
	
	public void sendMessage(String message){
		try {
			dout.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated
	public void sendAuthentification(String username, String password){
		try {
			dout.writeUTF(username + "/p" + password);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try{
			String message = null;
			while(!socket.isClosed()){
				message = din.readUTF();
				if(message.equals("0x0033=")){
					
				}else if(message.equals("0x0033=")){
					
				}
				System.out.println(message);
				if(message.equals("sendAuth")){
				}else if(message.equals("AuthDenied")){
					closeConnection();
					return;
				}else{
					listener.messageReceived(message);
				}
			}
		}catch(SocketException se){
			//se.printStackTrace();
			active = false;
			listener.connectionLost(socket.getInetAddress().getHostAddress());
		}catch(IOException ioe){
			ioe.printStackTrace();
			active = false;
			listener.connectionLost(socket.getInetAddress().getHostAddress());
		}finally{
			active = false;
			closeConnection();
		}
		
	}
	
	public boolean closeConnection(){
		try {
			active = false;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isClosed(){
		return socket.isClosed();
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
