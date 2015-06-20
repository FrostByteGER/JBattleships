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
public interface JBSGameListener extends Remote{
	
	public Game createGame(JBSGameType gameType) throws RemoteException;
	
	public Game createGame(JBSGameType gameType , JBSPlayer[] players, int fieldSize, int[] shipCount) throws RemoteException;

	public boolean startGame() throws RemoteException;
	
	//public void firePausedGame();
	
	public boolean endGame() throws RemoteException;
}
