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
	public JBSCorvette() {
		super(1, 0, 3, JBSDamageType.DAMAGE_SMALL);
		setName("Corvette");
		addShipPart(new JBSActor());	
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}

}
