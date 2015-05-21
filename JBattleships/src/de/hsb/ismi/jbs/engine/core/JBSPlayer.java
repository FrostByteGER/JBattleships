/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSPlayer {
	
	private ArrayList<JBSShip> ships;
	private boolean isAlive;
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
