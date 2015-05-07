/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.io.manager.DataManager;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Game {
	
	public DataManager DataM;
	private JBSGameType gameType;
	private JBSGameField[] gameFields;
	private JBSPlayer[] players;
	private int activPlayer;

	/**
	 * @param gameType
	 * @param gameField
	 * @param players
	 */
	public Game(JBSGameType gameType, JBSGameField[] gameField, JBSPlayer[] players) {
		super();
		DataM = new DataManager();
		this.gameType = gameType;
		this.gameFields = gameField;
		this.players = players;
		this.activPlayer = 0;//TODO may change
		
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
	 * @return the gameField
	 */
	public final JBSGameField[] getGameField() {
		return gameFields;
	}

	/**
	 * @param gameField the gameField to set
	 */
	public final void setGameField(JBSGameField[] gameField) {
		this.gameFields = gameField;
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
	public int getActivPlayer() {
		return activPlayer;
	}

	/**
	 * @param activPlayer the activPlayer to set
	 */
	public void setActivPlayer(int activPlayer) {
		this.activPlayer = activPlayer;
	}
	
	public boolean isGameOver(){
		
		int amount = 0;
		
		for(JBSPlayer p : players){
			for(JBSShip ship : p.getShips()){
				ship.checkHealth();
			}
		}
		// TODO CAHNGE!!!
		for(JBSPlayer p : players){
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
		}
		return true;
	}
	
	public final DataManager getDataManager(){
		return DataM;
	}

}
