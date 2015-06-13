package de.hsb.ismi.jbs.engine.core.manager;

import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.GameListener;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;

public class GameManager{
	
	private Game game = new Game();
	private RoundManager roundManager = new RoundManager();
	private boolean started = false;
	
	private ArrayList<GameListener> listeners = new ArrayList<GameListener>(0);
	
	/**
	 * 
	 */
	public GameManager() {
		
	}

	public Game createGame(JBSGameType gameType , JBSPlayer[] players, int fieldSize, int[] shipCount){
		for(JBSPlayer p : players){
			p.setPlayerField(new JBSGameField(fieldSize));
		}
		game = new Game(gameType, players, shipCount);
		
		return game;
	}
	
	/**
	 * Starts the actual match with the current data.
	 * @return
	 */
	public boolean startGame(){
		if(game != null){
			roundManager = new RoundManager();
			started = true;
			for(GameListener gl : listeners){
				gl.fireStartedGame();
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Ends the game.
	 * @param force Forces the game to end.
	 * @return True if game is over.
	 */
	public boolean endGame(boolean force){
		if((started && game.isGameOver()) || force){
			started = false;
			//JBSCoreGame.msgLogger.addMessage("Game has ended. Winner is: " + game.getActivePlayer().getName());
			System.out.println("Game has ended. Winner is: " + game.getActivePlayer().getName());
			for(GameListener gl : listeners){
				gl.fireEndedGame();
			}
			return true;
		}else{
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameManager";
	}
	
	public final int getFieldSize(){
		return game.getPlayer(0).getPlayerField().getSize(); //TODO: Change, could be null!
	}

	/**
	 * @return the game
	 */
	public final Game getGame() {
		return game;
	}

	/**
	 * @return the roundManager
	 */
	public final RoundManager getRoundManager() {
		return roundManager;
	}

	/**
	 * @param roundManager the roundManager to set
	 */
	public final void setRoundManager(RoundManager roundManager) {
		this.roundManager = roundManager;
	}
	
	public final void addGameListener(GameListener gl){
		listeners.add(gl);
	}

	/**
	 * @return the listeners
	 */
	public final ArrayList<GameListener> getListeners() {
		return listeners;
	}

	/**
	 * @param listeners the listeners to set
	 */
	public final void setListeners(ArrayList<GameListener> listeners) {
		this.listeners = listeners;
	}

	/**
	 * @param game the game to set
	 */
	public final void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the started
	 */
	public final boolean hasStarted() {
		return started;
	}

}
