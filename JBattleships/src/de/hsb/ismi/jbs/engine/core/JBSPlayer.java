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
	
	/**
	 * 
	 */
	public JBSPlayer() {
		ships = new ArrayList<JBSShip>();
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	
	
	
}
