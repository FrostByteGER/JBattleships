/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSShip {

	private int cooldownLimit;
	private int cooldown;
	private int length;

	private JBSDamageType damageType;
	private String name;
	
	private int health;
	
	private ArrayList<JBSActor> shipActors;
	
	/**
	 * 
	 */
	public JBSShip() {
		name = "unknown";
		shipActors = new ArrayList<JBSActor>();
	}
	
	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSShip(int cooldownLimit, int cooldown, int length, JBSDamageType damageType) {
		super();
		this.cooldownLimit = cooldownLimit;
		this.cooldown = cooldown;
		this.damageType = damageType;
		this.length = length;
		this.health = length;
		this.shipActors = new ArrayList<JBSActor>();
	}
	
	public void addShipPart(JBSActor part) {
		shipActors.add(part);
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
	public final JBSDamageType getDamageType() {
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void checkHealth() {
		
		health = length;
		
		for(JBSActor actor : this.shipActors){
			if(actor.isHit()){
				this.health--;
			}
		}
	}
	
	public boolean isAlife(){
		return (health > 0);
	}
	
}
