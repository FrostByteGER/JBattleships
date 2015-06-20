/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlRootElement(name = "Game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game implements Serializable{

	@XmlElement(name = "GameType")
	private JBSGameType gameType = null;
	@XmlElement(name = "Player")
	@XmlElementWrapper(name = "Players")
	private JBSPlayer[] players = null;
	@XmlElement(name = "ActivePlayer")
	private int activePlayer = 0;
	@XmlElement(name = "NextPlayer")
	private int nextPlayer = 0;
	@XmlElement(name = "PlayerFieldSize")
	private int fieldSize = 10;
	@XmlElement(name = "ShipCount")
	@XmlElementWrapper(name = "Ships")
	private int[] shipCount = new int[]{1,2,3,4};

	public Game(){
		
	}
	
	/**
	 * @param gameType
	 * @param gameField
	 * @param players
	 */
	public Game(JBSGameType gameType, JBSPlayer[] players, int[] shipCount) {
		this.gameType = gameType;
		this.players = players;
		this.activePlayer = 0;//TODO may change
		this.shipCount = shipCount;
	}

	/**
	 * @return the gameType
	 */
	public final JBSGameType getGameType() {
		return gameType;
	}

	/**
	 * @param gameType the gameType to set
	 */
	public final void setGameType(JBSGameType gameType) {
		this.gameType = gameType;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public final JBSPlayer getPlayer(int id){
		return players[id];
	}

	/**
	 * @return the players
	 */
	public final JBSPlayer[] getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public final void setPlayers(JBSPlayer[] players) {
		this.players = players;
	}

	/**
	 * @return the activPlayer
	 */
	public int getActivePlayerInt() {
		return activePlayer;
	}
	
	public JBSPlayer getActivePlayer(){
		return players[activePlayer];
	}
	
	/**
	 * @param activePlayer the activPlayer to set
	 */
	public void setActivePlayerInt(int activePlayer) {
		this.activePlayer = activePlayer;
	}
	
	public boolean nextPlayer(){
		
		nextPlayer = activePlayer;
		
		do {
			nextPlayer = (nextPlayer+1)%players.length;
			
			if(players[nextPlayer].isAlive()){
				players[nextPlayer].checkIsAlive();
			}
			
		} while (!players[nextPlayer].isAlive()&&activePlayer!=nextPlayer);
		
		if(activePlayer==nextPlayer){
			return false;
		}	
		activePlayer = nextPlayer;
		return true;
	}
	
	/**
	 * Checks the health of the ships of all players
	 */
	public void checkShipsHealth(){
		for(JBSPlayer p : players){
			for(JBSShip s : p.getShips()){
				s.checkHealth();
			}
		}
	}
	
	public boolean isGameOver(){
		
		int amount = 0;
		/* not needed
		for(JBSPlayer p : players){
			for(JBSShip ship : p.getShips()){
				ship.checkHealth();
			}
		}
		*/
		
		for(JBSPlayer p : players){
			if(p.isAlive()){
				amount++;
				if(amount>=2){
					return false;
				}
			}
		}
		
		
		// TODO CAHNGE!!! OLD
		/*for(JBSPlayer p : players){
			p.setAlive(false);
			for(JBSShip ship : p.getShips()){
				if(ship.isAlife()){
					p.setAlive(true);
					amount++;
					break;
				}
			}
			if(amount>1){
				return false;
			}
		}*/
		return true;
	}
	
	/**
	 * @return the nextPlayer
	 */
	public final int getNextPlayer() {
		return nextPlayer;
	}

	/**
	 * @param nextPlayer the nextPlayer to set
	 */
	public final void setNextPlayer(int nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	/**
	 * @param activePlayer the activePlayer to set
	 */
	public final void setActivePlayer(int activePlayer) {
		this.activePlayer = activePlayer;
	}
	
	public final int[] getShipCount(){
		return this.shipCount;
	}
	
	public void setShipCount(int[] count){
		shipCount = count;
	}
	
	public void setDestroyerCount(int size){
		shipCount[0] = size;
	}
	public void setFrigateCount(int size){
		shipCount[1] = size;
	}
	public void setSubmarineCount(int size){
		shipCount[2] = size;
	}
	public void setCorvetteCount(int size){
		shipCount[3] = size;
	}
	
	public int getDestroyerCount(){
		return shipCount[0];
	}
	public int getFrigateCount(){
		return shipCount[1];
	}
	public int getSubmarineCount(){
		return shipCount[2];
	}
	public int getCorvetteCount(){
		return shipCount[3];
	}

	/**
	 * @return the fieldSize
	 */
	public final int getFieldSize() {
		return fieldSize;
	}

	/**
	 * @param fieldSize the fieldSize to set
	 */
	public final void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}
	
}
