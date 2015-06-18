/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public enum JBSDamageType {
	DAMAGE_CANNON(1),
	DAMAGE_LARGE(3),
	DAMAGE_MEDIUM(2),
	DAMAGE_SMALL(1),
	DAMAGE_MINE(1);
	
	public final int value;
	
	private JBSDamageType(int value) {
		this.value = value; // fuck this Enums suck balls
		// ^ Yes yes you can't just work with them lol.
		// KennyS was here.
	}
	
	
}