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
	
	public void fireRound(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction);

}
