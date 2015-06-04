/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface GameListener {

	public void fireStartedGame();
	
	//public void firePausedGame();
	
	public void fireEndedGame();
}
