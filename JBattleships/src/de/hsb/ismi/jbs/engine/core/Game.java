/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Game {
	
	private JBSGameType gameType;
	private JBSGameField gameField;
	private Player[] players;

	/**
	 * 
	 */
	public Game() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gameType
	 * @param gameField
	 * @param players
	 */
	public Game(JBSGameType gameType, JBSGameField gameField, Player[] players) {
		super();
		this.gameType = gameType;
		this.gameField = gameField;
		this.players = players;
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
	public final JBSGameField getGameField() {
		return gameField;
	}

	/**
	 * @param gameField the gameField to set
	 */
	public final void setGameField(JBSGameField gameField) {
		this.gameField = gameField;
	}

	/**
	 * @return the players
	 */
	public final Player[] getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public final void setPlayers(Player[] players) {
		this.players = players;
	}

}
