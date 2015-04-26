/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Player {
	
	private ArrayList<JBSShip> ships;
	
	
	/**
	 * 
	 */
	public Player() {
		ships = new ArrayList<JBSShip>();
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
	
	
}
