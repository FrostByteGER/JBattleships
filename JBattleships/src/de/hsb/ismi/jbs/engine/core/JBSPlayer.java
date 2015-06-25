/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({JBSAIPlayer.class})
public class JBSPlayer{
	
	@XmlElement(name = "PlayerProfile")
	private transient JBSProfile profile = new JBSProfile();
	@XmlElement(name = "Ship")
	@XmlElementWrapper(name = "PlayerShips")
	private ArrayList<JBSShip> ships = new ArrayList<JBSShip>(0);
	@XmlElement(name = "Alive")
	private boolean isAlive = true;
	@XmlElement(name = "PlayerField")
	private JBSGameField playerField = null;
	@XmlElement(name = "PlayerStatistics")
	private GameStatistics statistics = new GameStatistics();

	
	public JBSPlayer(){
		
	}
	
	/**
	 * 
	 */
	public JBSPlayer(JBSProfile profile) {
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

	public void checkIsAlive(){
		if(isAlive){
			for(JBSShip s : ships){
				s.checkHealth();
				if(s.isAlive()){
					return;
				}
			}
			isAlive = false;
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
	
	/**
	 * Don't manually call! This method gets called by the JAXB {@link javax.xml.bind.Unmarshaller Unmarshaller}.
	 * @param u
	 * @param parent
	 */
	public void afterUnmarshal(Unmarshaller u, Object parent){
		for(JBSShip ship : ships){
			playerField.setShip(ship);
		}
	}

	/**
	 * @return the statistics
	 */
	public final GameStatistics getStatistics() {
		return statistics;
	}

	/**
	 * @param statistics the statistics to set
	 */
	public final void setStatistics(GameStatistics statistics) {
		this.statistics = statistics;
	}
	
	
	
}
