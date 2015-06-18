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
public interface RoundListener extends Remote{
	
	/**
	 * Fires a round processing event. Used to shoot at a ship and notify the game.
	 * @param target
	 * @param source
	 * @param ship
	 * @param x
	 * @param y
	 * @param direction
	 * @throws RemoteException
	 */
	public boolean fireRound(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction) throws RemoteException;
	
	/**
	 * Fires a analyze round processing event. Used to analyze the shot and check health as well as end the game.
	 * @param source
	 * @throws RemoteException
	 */
	public void fireAnalyzeRound(JBSPlayer source) throws RemoteException;
	
	/**
	 * Fires a end round processing event. Notifys the game that the player has ended its round.
	 * @param source
	 * @throws RemoteException
	 */
	public void fireEndRound(JBSPlayer source) throws RemoteException;

}
