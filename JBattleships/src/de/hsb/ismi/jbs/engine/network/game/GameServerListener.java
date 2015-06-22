/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface GameServerListener extends Remote{
	
	public LobbyInfo getLobbyData() throws RemoteException;
	
	public void getGameData() throws RemoteException;
	
	public void setReady(String id, boolean ready) throws RemoteException;
	
	
	
	
}
