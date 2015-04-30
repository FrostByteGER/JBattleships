/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Jan Schult
 *
 */
public class JBSCorvette extends JBSShip {

	/**
	 * 
	 */
	public JBSCorvette(Game game) {
		super(1, 0, 3, JBSDamageType.DAMAGE_SMALL);
		setName(game.getDataManager().getLocalizationManager().getWord("Corvette"));
		addShipPart(new JBSActor());	
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}

}
