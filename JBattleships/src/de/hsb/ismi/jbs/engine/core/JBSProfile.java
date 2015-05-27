/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.HashMap;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSProfile {
	
	private int id;
	private String name;
	private HashMap<String,Float> stats;
	private HashMap<String,Achievement> achievements;

	/**
	 * 
	 */
	public JBSProfile() {
		id = 0;
		name = "default";
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
}
