/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSSubmarine extends JBSShip {

	/**
	 * 
	 */
	public JBSSubmarine() {
		super(1, 0, 2, JBSDamageType.DAMAGE_SMALL);
		setName(JBattleships.game.getDataManager().getLocalizationManager().getLocalization("Submarine"));
		addShipPart(new JBSActor("JBSSubmarine"));
		addShipPart(new JBSActor("JBSSubmarine"));
		
	}

}
