/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

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
	
	public String getStringDirection(){
		if(value == 0){
			return "n";
		}else if(value == 1){
			return "e";
		}else if(value == 2){
			return "s";
		}else if(value == 3){
			return "w";
		}else if(value == 4){
			return "h";
		}else if(value == 5){
			return "v";
		}else if(value == -1){
			return "no";
		}
		return "";
	}
	
	public static Direction getRandomDirection(Random r){
		return values()[r.nextInt(3)];
	}
}
