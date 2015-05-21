/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

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
	@XmlElement(name = "PlayerName")
	private String name;
	
	/**
	 * 
	 */
	public JBSPlayer() {
		ships = new ArrayList<JBSShip>();
		isAlive = true;
		name = "undefined";
	}
	
	/**
	 * 
	 */
	public JBSPlayer(String name) {
		ships = new ArrayList<JBSShip>();
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
	
	
	
	
}
