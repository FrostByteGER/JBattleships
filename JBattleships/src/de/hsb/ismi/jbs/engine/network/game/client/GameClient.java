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
import java.util.concurrent.CopyOnWriteArrayList;

import de.hsb.ismi.jbs.engine.core.JBSGameListener;
import de.hsb.ismi.jbs.engine.core.JBSRoundListener;
import de.hsb.ismi.jbs.engine.network.game.GameConnectionState;
import de.hsb.ismi.jbs.engine.network.game.GameNetworkState;
import de.hsb.ismi.jbs.engine.network.game.GameServerListener;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameClient extends Thread {

	// Client Connection Data
	private InetAddress ip             = InetAddress.getLoopbackAddress();
	private int gamePort               = 15750;
	private int roundListenerPort      = 15751;
	private int gameListenerPort       = 15752;
	private int gameServerListenerPort = 15753;
	
	
	// Client Data
	private Socket socket = null;
	private DataOutputStream outputStream = null;
	private DataInputStream inputStream = null;
	private CopyOnWriteArrayList<GameClientListener> listeners = new CopyOnWriteArrayList<>();
	private String username = "undefined";
	private boolean endClient = false;
	
	// Game Data
	private GameConnectionState gameConnectionState = GameConnectionState.LOGIN;
	private GameNetworkState gameNetworkState       = GameNetworkState.LOBBY_PRE_CREATED;
	
	// Stubs
	private JBSRoundListener roundLStub        = null;
	private JBSGameListener gameLStub          = null;
	private GameServerListener gameServerLStub = null;
	
	public GameClient(InetAddress ip, String username, int gamePort, int roundListenerPort, int gameListenerPort, int gameServerListenerPort) throws UnknownHostException, IOException{
		super("GameClient-Thread");
		this.ip = ip;
		this.username = username;
		this.gamePort = gamePort;
		this.roundListenerPort = roundListenerPort;
		this.gameListenerPort = gameListenerPort;
		this.gameServerListenerPort = gameServerListenerPort;
		socket = new Socket(ip, gamePort);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void addMessageListener(GameClientListener listener){
		this.listeners.add(listener);
	}
	
	@Deprecated
	public synchronized void removeMessageListener(GameClientListener listener){
		//this.listeners.remove(listener);
	}
	
	/**
	 * Sends a message to the connected server.
	 * @param message
	 * @throws IOException 
	 */
	public void sendMessage(String message) throws IOException{
		System.err.println("GameClient: Client sending Message: " + message);
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
			while(!endClient){
				
				
				//Initial Authentification
				while(gameConnectionState == GameConnectionState.LOGIN){
					message = inputStream.readUTF();
					System.err.println("GameClient: Client " + getName() + " received Message: " + message);
					for(GameClientListener listener : listeners){
						listener.messageReceived(message);
					}
					if(message.equals("/end") || message.equals("/duplicateusername") || message.equals("/kick")){
						gameConnectionState = GameConnectionState.CLOSED;
						closeClient();
					}else if(message.equals("/ban")){
						gameConnectionState = GameConnectionState.BANNED;
						closeClient();
					}else if(message.equals("/full")){
						gameConnectionState = GameConnectionState.FULL;
						System.err.println("GameClient: LOBBY FULL");
						closeClient();
					}else if(message.equals("/success")){
						gameConnectionState = GameConnectionState.AUTHENTICATED;
						gameNetworkState = GameNetworkState.LOBBY_WAITING;
					}
				}
				
				
				// Game Network Logic
				while(gameConnectionState == GameConnectionState.AUTHENTICATED){
					message = inputStream.readUTF();
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
			}
		}catch(SocketException se){
			if(!endClient){
				for(GameClientListener listener : listeners){
					listener.connectionLost(socket.getInetAddress().getHostAddress());
				}
			}
		}catch(IOException ioe){
			if(!endClient){
				for(GameClientListener listener : listeners){
					listener.connectionLost(socket.getInetAddress().getHostAddress());
				}
			}
		}finally{
			closeClient();
		}
		
	}
	
	/**
	 * Closes the connection to the server. After successfully closing, the client object <b>cannot</b> be restarted!
	 * @return True if client was closed successful. False if an error occured.
	 */
	public boolean closeClient(){
		try {
			endClient = true;
			gameConnectionState = GameConnectionState.CLOSED;
			socket.close();
			System.err.println("GameClient: Closed Connection");
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
		
		start();
		try {
			sendAuthentification(username);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			roundLStub = (JBSRoundListener) Naming.lookup("rmi://" + ip.getHostAddress() + ":" + roundListenerPort + "/JBSRoundListener");
			roundLStub.printRMITest(1337);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			gameLStub = (JBSGameListener) Naming.lookup("rmi://" + ip.getHostAddress() + ":" + gameListenerPort + "/JBSGameListener");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			gameServerLStub = (GameServerListener) Naming.lookup("rmi://" + ip.getHostAddress() + ":" + gameServerListenerPort + "/JBSGameServerListener");
			
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
		return endClient;
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
	public final JBSRoundListener getRoundListener() {
		return roundLStub;
	}

	/**
	 * @return the gameLStub
	 */
	public final JBSGameListener getGameListener() {
		return gameLStub;
	}

	/**
	 * @return the gameServerLStub
	 */
	public final GameServerListener getGameServerListener() {
		return gameServerLStub;
	}
	
	
}
