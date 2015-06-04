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
	
	@XmlElement(name = "PlayerProfile")
	private JBSProfile profile;
	@XmlElement(name = "Ship")
	@XmlElementWrapper(name = "PlayerShips")
	private ArrayList<JBSShip> ships;
	@XmlElement(name = "Alive")
	private boolean isAlive;
	@XmlElement(name = "PlayerField")
	private JBSGameField playerField;

	
	/**
	 * 
	 */
	public JBSPlayer(JBSProfile profile) {
		ships = new ArrayList<JBSShip>();
		playerField = null;
		isAlive = true;
		this.profile = profile;
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name) {
		ships = new ArrayList<JBSShip>();
		playerField = null;
		isAlive = true;
		profile = new JBSProfile(name);
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name, JBSGameField playerField) {
		ships = new ArrayList<JBSShip>();
		this.playerField = playerField;
		isAlive = true;
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
	
	/**
	 * Decreases the cooldown of all ships by one.
	 */
	public void decreaseCooldownAll(){
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

	public void chackIsAlive(){
		if(isAlive){
			for(JBSShip s : ships){
				s.checkHealth();
				if(s.isAlife()){
					return;
				}
			}
		}
	}
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return profile.getName();
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
