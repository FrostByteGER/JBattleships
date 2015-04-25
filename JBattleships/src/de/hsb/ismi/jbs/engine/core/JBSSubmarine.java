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
		super(1, 0, 2, DamageType.DAMAGE_SMALL);
		setName("Submarine");
		setHealth(2);
	}

	/**
	 * @param replicated
	 */
	public JBSSubmarine(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
