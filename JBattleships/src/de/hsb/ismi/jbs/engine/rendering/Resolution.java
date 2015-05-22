/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

import java.awt.DisplayMode;

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
	 * @param width
	 * @param height
	 */
	public Resolution(int width, int height) {
		super(width, height);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getWidth(){
		return getX();
	}
	
	/**
	 * 
	 * @param width
	 */
	public void setWidth(int width){
		setX(width);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHeight(){
		return getY();
	}
	
	/**
	 * 
	 * @param height
	 */
	public void setHeight(int height){
		setY(height);
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
		return getWidth() + "x" + getHeight();
	}
	
	/**
	 * Converts a given DisplayMode to a Resolution object. 
	 * @param m
	 * @return The converted Resolution
	 */
	public static Resolution convertDisplayModeToResolution(DisplayMode m){
		return new Resolution(m.getWidth(),m.getHeight());
	}
	

}
