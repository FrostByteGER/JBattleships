/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Jan Schult
 *
 */
public class JBSFrigate extends JBSShip {

	/**
	 * 
	 */
	public JBSFrigate(Game game) {
		super(2, 0, 4, JBSDamageType.DAMAGE_SMALL);
		setName(game.getDataManager().getLocalizationManager().getWord("Frigate"));
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}
}
