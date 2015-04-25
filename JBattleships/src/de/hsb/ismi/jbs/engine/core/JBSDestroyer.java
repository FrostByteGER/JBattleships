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
	public JBSDestroyer() {
		super(3, 0, 5);
		setName("Destroyer");
		setHealth(5);
		setLength(5);
	}

	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSDestroyer(int cooldownLimit, int cooldown, DamageType damageType) {
		super(cooldownLimit, cooldown, damageType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param replicated
	 */
	public JBSDestroyer(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
