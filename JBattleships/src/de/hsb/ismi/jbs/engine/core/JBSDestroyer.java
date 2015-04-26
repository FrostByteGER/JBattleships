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
		super(3, 0, 5, JBSDamageType.DAMAGE_SMALL);
		setName("Destroyer");
		setHealth(5);
	}

	/**
	 * @param replicated
	 */
	public JBSDestroyer(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
