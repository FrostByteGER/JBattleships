/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSProfile {
	
	@XmlTransient
	private int id;
	@XmlElement(name = "PlayerName")
	private String name;
	@XmlTransient
	private HashMap<String,Float> stats;
	@XmlTransient
	private HashMap<String,Achievement> achievements;

	/**
	 * 
	 */
	public JBSProfile() {
		id = 0;
		name = "default";
	}
	
	/**
	 * 
	 */
	public JBSProfile(String name) {
		id = 0;
		this.name = name;
	}
	
	/**
	 * 
	 */
	public JBSProfile(String name, int id) {
		this.id = id;
		this.name = name;
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
