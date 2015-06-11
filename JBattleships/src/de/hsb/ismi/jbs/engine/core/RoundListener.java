/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface RoundListener {

	//public void processRound(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction);
	
	//public void analyzeRound(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction);
	
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
	
	public void fireAnalyzeRound(JBSPlayer source);
	
	public void fireEndRound(JBSPlayer source);

}
