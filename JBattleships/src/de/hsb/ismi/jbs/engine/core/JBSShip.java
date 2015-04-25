/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSShip extends JBSActor {

	private int cooldownLimit;
	private int cooldown;
	private DamageType damageType;
	

	/**
	 * 
	 */
	public JBSShip() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSShip(int cooldownLimit, int cooldown, DamageType damageType) {
		super();
		this.cooldownLimit = cooldownLimit;
		this.cooldown = cooldown;
		this.damageType = damageType;
	}


	/**
	 * @param replicated
	 */
	public JBSShip(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the cooldown
	 */
	public final int getCooldown() {
		return cooldown;
	}

	/**
	 * @param cooldown the cooldown to set
	 */
	public final void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	/**
	 * @return the cooldownLimit
	 */
	public final int getCooldownLimit() {
		return cooldownLimit;
	}

	/**
	 * @return the damageType
	 */
	public final DamageType getDamageType() {
		return damageType;
	}

}
