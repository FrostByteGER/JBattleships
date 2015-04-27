/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

/**
 * Simple Vector class. Used to save x and y coordinates.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Vector2i {
	
	/** X-Coordinate */
	private int x;
	/** Y-Coordinate */
	private int y;
	
	/**
	 * Default Constructor. Initiates both coordinates with 0.
	 */
	public Vector2i() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructs and Vector2i object with the given coordinates.
	 * @param x The X-Coordinate
	 * @param y The Y-Coordinate
	 */
	public Vector2i(int x, int y){
		this.x = x;
		this.y = y;	
	}

	/**
	 * @return the x
	 */
	public final int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public final void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public final int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public final void setY(int y) {
		this.y = y;
	}

}
