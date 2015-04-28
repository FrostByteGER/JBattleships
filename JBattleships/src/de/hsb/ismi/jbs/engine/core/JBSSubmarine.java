/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

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
		setName("Submarine");
		addShipPart(new JBSActor());
		addShipPart(new JBSActor());
	}

}
