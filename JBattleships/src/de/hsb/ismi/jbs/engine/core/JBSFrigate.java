/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSFrigate extends JBSShip {

	/**
	 * 
	 */
	public JBSFrigate() {
		super(2, 0, 4, JBSDamageType.DAMAGE_MEDIUM);
		setName(JBattleships.game.getDataManager().getLocalizationManager().getLocalization("Frigate"));
		addShipPart(new JBSShipActor("JBSFrigate",this));
		addShipPart(new JBSShipActor("JBSFrigate", this));
		addShipPart(new JBSShipActor("JBSFrigate", this));
		addShipPart(new JBSShipActor("JBSFrigate", this));
		
	}
}
