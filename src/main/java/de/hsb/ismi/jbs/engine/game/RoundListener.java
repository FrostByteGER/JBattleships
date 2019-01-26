/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;

/**
 * The RoundListener interface is essential for firing the round-events.
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
	public HitInfo fireRound(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction);
	
	/**
	 * Fires a analyze round processing event. Used to analyze the shot and check health as well as end the game.
	 * @param source
	 */
	public boolean fireAnalyzeRound(JBSPlayer source);
	
	/**
	 * Fires a end round processing event. Notifys the game that the player has ended its round.
	 * @param source
	 */
	public void fireEndRound(JBSPlayer source);

}
