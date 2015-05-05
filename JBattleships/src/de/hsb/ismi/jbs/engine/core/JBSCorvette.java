/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.io.manager.DataManager;

/**
 * @author Jan Schult
 *
 */
public class JBSCorvette extends JBSShip {

	/**
	 * 
	 */
	public JBSCorvette(DataManager manager) {
		super(1, 0, 3, JBSDamageType.DAMAGE_SMALL);
		setName(manager.getLocalizationManager().getWord("Corvette"));
		addShipPart(new JBSActor());	
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}

}
