/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import de.hsb.ismi.jbs.engine.io.manager.DataManager;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlRootElement(name = "Game")
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {
	
	@XmlTransient
	private DataManager dataManager;
	@XmlElement(name = "GameType")
	private JBSGameType gameType;
	@XmlElement(name = "Player")
	@XmlElementWrapper(name = "Players")
	private JBSPlayer[] players;
	@XmlElement(name = "ActivePlayer")
	private int activePlayer;
	@XmlElement(name = "NextPlayer") // TODO added
	private int nextPlayer;
	
	/**
	 * @param gameType
	 * @param gameField
	 * @param players
	 */
	public Game(JBSGameType gameType, JBSPlayer[] players) {
		super();
		dataManager = new DataManager();
		this.gameType = gameType;
		this.players = players;
		this.activePlayer = 0;//TODO may change
		this.nextPlayer = activePlayer;
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
				players[nextPlayer].chackIsAlive();
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
				if(amount==2){
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
	
	public final DataManager getDataManager(){
		return dataManager;
	}
	
	

}
