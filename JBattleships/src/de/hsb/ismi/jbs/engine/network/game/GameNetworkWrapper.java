/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Wrapper class for network-transportation of game-data.<br>
 * The class contains everything important to inform clients about
 * the current state of the game as well as extra information.
 * 
 * @author Kevin Kuegler
 * @version 1.00
 */
@SuppressWarnings("serial")
public class GameNetworkWrapper implements Serializable {

	
	
	/**
	 * 
	 */
	public GameNetworkWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public void send(Socket sender) throws IOException{
		OutputStream os = sender.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(this);
		oos.flush();
	}
	
	
}
