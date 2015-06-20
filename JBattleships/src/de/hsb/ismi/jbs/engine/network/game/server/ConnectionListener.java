/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game.server;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface ConnectionListener {

	public void PlayerConnected(GameServerThread client, int position);
	
}
