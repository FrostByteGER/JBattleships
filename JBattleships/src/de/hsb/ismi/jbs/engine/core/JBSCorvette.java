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
		super(1, 0, 3, DamageType.DAMAGE_SMALL);
		setName("Corvette");
		setHealth(3);	
	}

	/**
	 * @param replicated
	 */
	public JBSCorvette(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

}
