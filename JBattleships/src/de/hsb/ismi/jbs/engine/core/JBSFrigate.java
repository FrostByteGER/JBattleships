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
		super(2, 0, 4, DamageType.DAMAGE_SMALL);
		setName("Frigate");
		setHealth(4);
	}

	/**
	 * @param replicated
	 */
	public JBSFrigate(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
