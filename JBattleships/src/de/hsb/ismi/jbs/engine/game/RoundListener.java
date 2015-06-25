/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface RoundListener{
	
	/**
	 * Fires a round processing event. Used to shoot at a ship and notify the game.
	 * @param target
	 * @param source
	 * @param ship
	 * @param x
	 * @param y
	 * @param direction
	 */
	public boolean fireRound(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction);
	
	/**
	 * Fires a analyze round processing event. Used to analyze the shot and check health as well as end the game.
	 * @param source
	 */
	public void fireAnalyzeRound(JBSPlayer source);
	
	/**
	 * Fires a end round processing event. Notifys the game that the player has ended its round.
	 * @param source
	 */
	public void fireEndRound(JBSPlayer source);

}
