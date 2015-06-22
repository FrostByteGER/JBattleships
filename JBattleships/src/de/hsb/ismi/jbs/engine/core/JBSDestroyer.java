/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSDestroyer extends JBSShip {
	
	/**
	 * 
	 */
	public JBSDestroyer() {
		super(3, 0, 5, JBSDamageType.DAMAGE_LARGE);
		setName(JBattleships.game.getLocalization("Destroyer"));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		
	}
	
}
