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
		return super.getX();
	}
	
	/**
	 * 
	 * @param width
	 */
	public void setWidth(int width){
		super.setX(width);
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#getX()
	 */
	@Deprecated
	@Override
	public int getX() {
		return super.getX();
	}
	
	/* 
	 * (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#setX(int)
	 */
	@Deprecated
	@Override
	public void setX(int x) {
		super.setX(x);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHeight(){
		return super.getY();
	}
	
	/**
	 * 
	 * @param height
	 */
	public void setHeight(int height){
		super.setY(height);
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#getY()
	 */
	@Deprecated
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return super.getY();
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.utility.Vector2i#setY(int)
	 */
	@Deprecated
	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		super.setY(y);
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
