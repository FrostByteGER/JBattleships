/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface GameListener extends Remote{

	public void fireStartedGame();
	
	//public void firePausedGame();
	
	public void fireEndedGame();
}
