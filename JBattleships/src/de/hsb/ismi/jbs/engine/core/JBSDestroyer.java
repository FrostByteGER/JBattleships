/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Jan Schult
 *
 */
public class JBSDestroyer extends JBSShip {

	/**
	 * 
	 */
	public JBSDestroyer(Game game) {
		super(3, 0, 5, JBSDamageType.DAMAGE_LARGE);
		setName(game.getDataManager()
				.getLocalizationManager()
				.getWord("Destroyer"));
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}
}
