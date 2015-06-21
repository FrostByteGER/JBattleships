package de.hsb.ismi.jbs.engine.core.manager;

import java.rmi.RemoteException;
import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameListener;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.network.game.GameNetworkState;

public class GameManager implements JBSGameListener{
	
	private Game game = new Game();
	private RoundManager roundManager = new RoundManager();
	
	private ArrayList<JBSGameListener> listeners = new ArrayList<JBSGameListener>(0);
	
	private GameNetworkState gameState = GameNetworkState.LOBBY_PRE_CREATED;
	
	/**
	 * 
	 */
	public GameManager() {
		
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.JBSGameListener#createGame(de.hsb.ismi.jbs.engine.core.JBSGameType)
	 */
	@Override
	public Game createGame(JBSGameType gameType) throws RemoteException{
		game = new Game();
		game.setGameType(gameType);
		gameState = GameNetworkState.LOBBY_CREATED;
		return game;
	}

	/**
	 * Creates the GameObject with its data. 
	 * @param gameType
	 * @param players
	 * @param fieldSize
	 * @param shipCount
	 * @return
	 */
	@Override
	public Game createGame(JBSGameType gameType , JBSPlayer[] players, int fieldSize, int[] shipCount) throws RemoteException{
		for(JBSPlayer p : players){
			p.setPlayerField(new JBSGameField(fieldSize));
		}
		game = new Game(gameType, players, shipCount);
		gameState = GameNetworkState.LOBBY_CREATED;
		return game;
	}
	

	@Override
	public boolean startGame() throws RemoteException {
		if(game != null){
			roundManager = new RoundManager();
			gameState = GameNetworkState.GAME_STARTED;
			/*
			for(JBSGameListener gl : listeners){
				try {
					gl.startGame();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean endGame() throws RemoteException {
		if((gameState == GameNetworkState.GAME_STARTED && game.isGameOver())){
			gameState = GameNetworkState.GAME_ENDED;
			/*
			for(JBSGameListener gl : listeners){
				try {
					gl.endGame();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			return true;
		}else{
			return false;
		}
	}
	
	@Deprecated
	public boolean startGame2(){
		if(game != null){
			roundManager = new RoundManager();
			gameState = GameNetworkState.GAME_STARTED;
			for(JBSGameListener gl : listeners){
				try {
					gl.startGame();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			return true;
		}else{
			return false;
		}
	}

	@Deprecated
	public boolean endGame2(boolean force){
		if((gameState == GameNetworkState.GAME_STARTED && game.isGameOver()) || force){
			gameState = GameNetworkState.GAME_ENDED;
			for(JBSGameListener gl : listeners){
				try {
					gl.endGame();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
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
	
	/**
	 * 
	 * @return
	 */
	public final int getFieldSize(){
		return game.getFieldSize();
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
	
	public final void addGameListener(JBSGameListener gl){
		listeners.add(gl);
	}

	/**
	 * @return the listeners
	 */
	public final ArrayList<JBSGameListener> getListeners() {
		return listeners;
	}

	/**
	 * @param listeners the listeners to set
	 */
	public final void setListeners(ArrayList<JBSGameListener> listeners) {
		this.listeners = listeners;
	}

	/**
	 * @param game the game to set
	 */
	public final void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the gameState
	 */
	public final GameNetworkState getGameState() {
		return gameState;
	}

	/**
	 * @param gameState the gameState to set
	 */
	public final void setGameState(GameNetworkState gameState) {
		this.gameState = gameState;
	}

}
