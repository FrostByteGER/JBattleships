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
	private int id = -1;
	@XmlElement(name = "PlayerName")
	private String name = "default";
	@XmlTransient
	private HashMap<String, Float> stats = new HashMap<String, Float>(0);
	@XmlTransient
	private HashMap<String, Achievement> achievements = new HashMap<String, Achievement>(0);

	/**
	 * 
	 */
	public JBSProfile() {
	}
	
	/**
	 * 
	 */
	public JBSProfile(String name) {
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
