/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Resolution extends Vector2i {

	/**
	 * 
	 */
	public Resolution() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Resolution(int x, int y) {
		super(x, y);
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#toString()
	 */
	@Override
	public String toString() {
		return getX() + "x" + getY();
	}
	

}
