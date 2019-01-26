/**
 * 
 */
package de.hsb.ismi.jbs.engine.players;

import java.util.ArrayList;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.game.GameStatistics;
import de.hsb.ismi.jbs.engine.game.JBSGameField;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({JBSAIPlayer.class})
public class JBSPlayer{
	
	@XmlTransient
	private JBSProfile profile = new JBSProfile();
	@XmlElement(name = "Ship")
	@XmlElementWrapper(name = "PlayerShips")
	private ArrayList<JBSShip> ships = new ArrayList<JBSShip>();
	@XmlElement(name = "Alive")
	private boolean isAlive = true;
	@XmlElement(name = "PlayerField")
	private JBSGameField playerField = null;
	@XmlElement(name = "PlayerStatistics")
	private GameStatistics statistics = new GameStatistics();
	@XmlElement(name = "Name")
	private String name = "default";

	
	public JBSPlayer(){
		
	}
	
	/**
	 * 
	 */
	public JBSPlayer(JBSProfile profile) {
		this.profile = profile;
		this.name = profile.getName();
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name) {
		profile = new JBSProfile(name);
		this.name = name;
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name, JBSGameField playerField) {
		this.playerField = playerField;
		this.name = name;
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name, JBSGameField playerField, GameStatistics stats) {
		this.playerField = playerField;
		this.statistics = stats;
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
	 * 
	 * @param name
	 */
	public final void setName(String name){
		this.name = name;
		this.profile.setName(name);
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
		this.profile.setName(this.name);
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
