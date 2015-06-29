/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

/**
 * Simple Vector class. Used to save x and y coordinates.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Vector2i{
	
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
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vector2i)) {
			return false;
		}
		Vector2i other = (Vector2i) obj;
		if (x == other.x && y == other.y) {
			return true;
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "X = " + x + " | Y = " + y;
	}

}
