/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface GameListener{

	public void fireStartedGame();
	
	//public void firePausedGame();
	
	public void fireEndedGame();
}
