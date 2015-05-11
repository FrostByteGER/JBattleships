/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.io.manager.DataManager;

/**
 * @author Jan Schult
 *
 */
public class JBSFrigate extends JBSShip {

	/**
	 * 
	 */
	public JBSFrigate(DataManager manager) {
		super(2, 0, 4, JBSDamageType.DAMAGE_MEDIUM);
		setName(manager.getLocalizationManager().getLocalization("Frigate"));
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}
}
