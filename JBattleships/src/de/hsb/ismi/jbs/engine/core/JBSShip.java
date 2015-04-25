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
	private int length;

	private DamageType damageType;
	private String name;

	/**
	 * 
	 */
	public JBSShip() {
		name = "unknown";
	}
	
	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSShip(int cooldownLimit, int cooldown, int length, DamageType damageType) {
		super();
		this.cooldownLimit = cooldownLimit;
		this.cooldown = cooldown;
		this.damageType = damageType;
		this.length = length;
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
	public void setCooldown(int cooldown) {
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
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	
}
