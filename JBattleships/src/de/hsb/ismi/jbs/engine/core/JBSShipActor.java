/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class JBSShipActor extends JBSActor {

	/**
	 * 
	 */
	public JBSShipActor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public JBSShipActor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param replicated
	 */
	public JBSShipActor(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param player
	 * @param location
	 * @param rotation
	 * @param health
	 * @param visibility
	 * @param components
	 */
	public JBSShipActor(JBSPlayer player, Vector2i location, Direction rotation, int health, boolean visibility, JBSActorComponent components) {
		super(player, location, rotation, health, visibility, components);
		// TODO Auto-generated constructor stub
	}

}
