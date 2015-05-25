/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface FireListener {

	public void fireShot(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction);
}
