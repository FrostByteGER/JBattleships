/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.io.manager.DataManager;

/**
 * @author Jan Schult
 *
 */
public class JBSSubmarine extends JBSShip {

	/**
	 * 
	 */
	public JBSSubmarine(DataManager manager) {
		super(1, 0, 2, JBSDamageType.DAMAGE_SMALL);
		setName(manager.getLocalizationManager().getWord("Submarine"));
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}

}
