/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Jan Schult
 *
 */
public class JBSFrigate extends JBSShip {

	/**
	 * 
	 */
	public JBSFrigate() {
		super(2, 0, 4);
		setName("Frigate");
		setHealth(4);
		setHealth(4);
	}

	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSFrigate(int cooldownLimit, int cooldown, DamageType damageType) {
		super(cooldownLimit, cooldown, damageType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param replicated
	 */
	public JBSFrigate(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
