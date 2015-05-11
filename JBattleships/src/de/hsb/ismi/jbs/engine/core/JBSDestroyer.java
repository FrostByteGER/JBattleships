/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.io.manager.DataManager;

/**
 * @author Jan Schult
 *
 */
public class JBSDestroyer extends JBSShip {

	/**
	 * 
	 */
	public JBSDestroyer(DataManager manager) {
		super(3, 0, 5, JBSDamageType.DAMAGE_LARGE);
		setName(manager
				.getLocalizationManager()
				.getLocalization("Destroyer"));
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}
}
