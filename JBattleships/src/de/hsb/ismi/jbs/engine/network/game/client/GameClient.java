/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.core.GameListener;
import de.hsb.ismi.jbs.engine.core.RoundListener;
import de.hsb.ismi.jbs.engine.network.game.GameConnectionState;
import de.hsb.ismi.jbs.engine.network.game.GameNetworkState;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameClient extends Thread {

	// Client Connection Data
	private InetAddress ip = InetAddress.getLoopbackAddress();
	private int gamePort = 15750;
	private int roundListenerPort = 15751;
	private int gameListenerPort = 15752;
	
	
	// Client Data
	private Socket socket = null;
	private DataOutputStream outputStream = null;
	private DataInputStream inputStream = null;
	private ArrayList<GameClientListener> listeners = new ArrayList<>(0);
	private String username = "undefined";
	private boolean endThread = false;
	
	// Game Data
	private GameConnectionState gameConnectionState = GameConnectionState.LOGIN;
	private GameNetworkState gameNetworkState = GameNetworkState.LOBBY_PRE_CREATED;
	
	private RoundListener roundLStub;
	private GameListener gameLStub;
	
	public GameClient(InetAddress ip, String username, int gamePort, int roundListenerPort, int gameListenerPort) throws UnknownHostException, IOException{
		super("GameClient-Thread");
		this.ip = ip;
		this.username = username;
		this.gamePort = gamePort;
		this.roundListenerPort = roundListenerPort;
		this.gameListenerPort = gameListenerPort;
		socket = new Socket(ip, gamePort);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void addMessageListener(GameClientListener listener){
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
		for(GameClientListener listener : listeners){
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
				//Initial Authentification
				while(gameConnectionState == GameConnectionState.LOGIN){
					message = inputStream.readUTF();
					System.out.println("Client " + getName() + "received Message: " + message);
					if(message.equals("/end")){
						gameConnectionState = GameConnectionState.CLOSED;
						closeConnection();
					}else if(message.equals("/ban")){
						gameConnectionState = GameConnectionState.BANNED;
						closeConnection();
					}else if(message.equals("/success")){
						gameConnectionState = GameConnectionState.AUTHENTICATED;
						gameNetworkState = GameNetworkState.LOBBY_WAITING;
					}
				}
				while(gameConnectionState == GameConnectionState.AUTHENTICATED){
					
					while(gameNetworkState == GameNetworkState.LOBBY_WAITING || gameNetworkState == GameNetworkState.LOBBY_READY){
						
					}
					
					while(gameNetworkState == GameNetworkState.LOBBY_SHIPS_SELECTING){
						
					}
					
					
					//////////////
					//////////////
					//////////////
					//////////////
					//////////////
					//////////////
					
				}
			
			
			
				for(GameClientListener listener : listeners){
					listener.messageReceived(message);
				}
				
			}
		}catch(SocketException se){
			for(GameClientListener listener : listeners){
				listener.connectionLost(socket.getInetAddress().getHostAddress());
			}
			
		}catch(IOException ioe){
			for(GameClientListener listener : listeners){
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
	 * 
	 */
	public void startClient(){
		try {
			sendAuthentification(username);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			roundLStub = (RoundListener) Naming.lookup("rmi://" + ip.getHostAddress() + ":" + roundListenerPort + "/RoundListener");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			gameLStub = (GameListener) Naming.lookup("rmi://" + ip.getHostAddress() + ":" + gameListenerPort + "/GameListener");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
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

	/**
	 * @return the gameConnectionState
	 */
	public final GameConnectionState getGameConnectionState() {
		return gameConnectionState;
	}

	/**
	 * @param gameConnectionState the gameConnectionState to set
	 */
	public final void setGameConnectionState(GameConnectionState gameConnectionState) {
		this.gameConnectionState = gameConnectionState;
	}

	/**
	 * @return the gameNetworkState
	 */
	public final GameNetworkState getGameNetworkState() {
		return gameNetworkState;
	}

	/**
	 * @param gameNetworkState the gameNetworkState to set
	 */
	public final void setGameNetworkState(GameNetworkState gameNetworkState) {
		this.gameNetworkState = gameNetworkState;
	}

	/**
	 * @return the ip
	 */
	public final InetAddress getServerIP() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public final void setServerIP(InetAddress ip) {
		this.ip = ip;
	}

	/**
	 * @return the gamePort
	 */
	public final int getGamePort() {
		return gamePort;
	}

	/**
	 * @param gamePort the gamePort to set
	 */
	public final void setGamePort(int gamePort) {
		this.gamePort = gamePort;
	}

	/**
	 * @return the roundListenerPort
	 */
	public final int getRoundListenerPort() {
		return roundListenerPort;
	}

	/**
	 * @param roundListenerPort the roundListenerPort to set
	 */
	public final void setRoundListenerPort(int roundListenerPort) {
		this.roundListenerPort = roundListenerPort;
	}

	/**
	 * @return the gameListenerPort
	 */
	public final int getGameListenerPort() {
		return gameListenerPort;
	}

	/**
	 * @param gameListenerPort the gameListenerPort to set
	 */
	public final void setGameListenerPort(int gameListenerPort) {
		this.gameListenerPort = gameListenerPort;
	}

	/**
	 * @return the roundLStub
	 */
	public final RoundListener getRoundListener() {
		return roundLStub;
	}

	/**
	 * @return the gameLStub
	 */
	public final GameListener getGameListener() {
		return gameLStub;
	}
	
	
}
