/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSGameField {

	private JBSActor[] fields;
	private int size;
	
	public JBSActor water;
	public JBSActor water_hit;
	
	/**
	 * 
	 */
	public JBSGameField() {
		water = new JBSActor();
		water_hit = new JBSActor();
		
		water.setHit(false);
		water_hit.setHit(true);
	}
	
	/**
	 * @param fields
	 * @param size
	 */
	public JBSGameField(JBSActor[] fields, int size) {
		super();
		this.fields = fields;
		this.size = size;
	}

	public Vector2i mouseToFieldCoords(int x, int y, int fieldSize){
		return null;
	}

	/**
	 * @return the fields
	 */
	public final JBSActor[] getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public final void setFields(JBSActor[] fields) {
		this.fields = fields;
	}

	/**
	 * @return the size
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public final void setSize(int size) {
		this.size = size;
	}

}
