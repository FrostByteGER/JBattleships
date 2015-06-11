/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.Random;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public enum Direction {
	
	NORTH(0),
	EAST(1),
	SOUTH(2),
	WEST(3),
	HORIZONTAL(4),
	VERTICAL(5),
	NONE(-1);
	
	public final int value;
	
	private Direction(int value) {
		this.value = value; // fuck this Enums suck balls
		// ^ Yes yes you can't just work with them lol.
		// KennyS was here.
	}
	
	public static Direction getRandomDirection(Random r){
		return values()[r.nextInt(3)];
	}
}
