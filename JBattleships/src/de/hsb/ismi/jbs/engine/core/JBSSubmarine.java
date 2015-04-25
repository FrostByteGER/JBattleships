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
		super(1, 0, 2);
		setName("Submarine");
		setHealth(2);
		setLength(2);
	}

	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSSubmarine(int cooldownLimit, int cooldown, DamageType damageType) {
		super(cooldownLimit, cooldown, damageType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param replicated
	 */
	public JBSSubmarine(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
