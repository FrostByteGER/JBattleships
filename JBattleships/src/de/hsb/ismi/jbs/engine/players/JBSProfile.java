/**
 * 
 */
package de.hsb.ismi.jbs.engine.players;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlRootElement(name = "Profile")
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSProfile {
	
	@XmlTransient
	private int id = 0;
	@XmlElement(name = "PlayerName")
	private String name = "default";
	@XmlElement(name = "PlayerStatistics")
	private ProfileStatistics stats = new ProfileStatistics();
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

	/**
	 * @return the stats
	 */
	public final ProfileStatistics getStats() {
		return stats;
	}

	/**
	 * @param stats the stats to set
	 */
	public final void setStats(ProfileStatistics stats) {
		this.stats = stats;
	}
}
