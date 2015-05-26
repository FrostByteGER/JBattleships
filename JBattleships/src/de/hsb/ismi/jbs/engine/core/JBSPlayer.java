/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSPlayer {
	
	@XmlElement(name = "Ship")
	@XmlElementWrapper(name = "PlayerShips")
	private ArrayList<JBSShip> ships;
	@XmlElement(name = "Alive")
	private boolean isAlive;
	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "PlayerField")
	private JBSGameField playerField;
	@XmlTransient
	private JBSProfile profile;
	
	/**
	 * 
	 */
	public JBSPlayer() {
		ships = new ArrayList<JBSShip>();
		playerField = null;
		isAlive = true;
		name = "undefined";
		profile = null;
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name) {
		ships = new ArrayList<JBSShip>();
		playerField = null;
		isAlive = true;
		this.name = name;
		profile = null;
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name, JBSGameField playerField) {
		ships = new ArrayList<JBSShip>();
		this.playerField = playerField;
		isAlive = true;
		this.name = name;
	}
	
	public void setShips(ArrayList<JBSShip> ships){
		this.ships = ships;
	}
	
	public ArrayList<JBSShip> getShips(){
		return ships;
	}
	
	public void addShip(JBSShip ship){
		ships.add(ship);
	}
	
	public void removeShips(){
		ships.clear();
	}
	
	public void subAllCooldown(){
		for(JBSShip s : ships){
			s.subCooldown();
		}
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the playerField
	 */
	public final JBSGameField getPlayerField() {
		return playerField;
	}

	/**
	 * @return the profile
	 */
	public final JBSProfile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public final void setProfile(JBSProfile profile) {
		this.profile = profile;
	}

	/**
	 * @param playerField the playerField to set
	 */
	public final void setPlayerField(JBSGameField playerField) {
		this.playerField = playerField;
	}
	
	
	
	
}
