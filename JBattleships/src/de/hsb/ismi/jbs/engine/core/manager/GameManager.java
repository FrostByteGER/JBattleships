package de.hsb.ismi.jbs.engine.core.manager;

import java.util.ArrayList;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.GameListener;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;

public class GameManager{
	
	private Game game = new Game();
	private ArrayList<JBSPlayer> players = new ArrayList<JBSPlayer>(2);
	private int[] shipcount = new int[]{0,0,0,0};
	private RoundManager roundManager = new RoundManager();
	private boolean started = false;
	
	private ArrayList<GameListener> listeners = new ArrayList<GameListener>(0);
	
	public GameManager() {
	}
	
	public void addPlayer(JBSPlayer player){
		players.add(player);
	}
	
	public void setDestroyerCount(int size){
		shipcount[0] = size;
	}
	public void setFrigateCount(int size){
		shipcount[1] = size;
	}
	public void setSubmarineCount(int size){
		shipcount[2] = size;
	}
	public void setCorvetteCount(int size){
		shipcount[3] = size;
	}
	
	public int getDestroyerCount(){
		return shipcount[0];
	}
	public int getFrigateCount(){
		return shipcount[1];
	}
	public int getSubmarineCount(){
		return shipcount[2];
	}
	public int getCorvetteCount(){
		return shipcount[3];
	}
	
	public Game createGame(JBSGameType type ,int fieldsize){
		
		for(JBSPlayer p : players){
			p.setPlayerField(new JBSGameField(fieldsize));
		}
	
		JBSPlayer[] tplayers = new JBSPlayer[players.size()];

		for(int i = 0 ; i < tplayers.length ; i++){
			tplayers[i] = players.get(i);
			/*
			for(int j = 0 ; j < shipcount[0] ; j++){
				tplayers[i].addShip(new JBSDestroyer());
			}
			for(int j = 0 ; j < shipcount[1] ; j++){
				tplayers[i].addShip(new JBSFrigate());
			}
			for(int j = 0 ; j < shipcount[2] ; j++){
				tplayers[i].addShip(new JBSSubmarine());
			}
			for(int j = 0 ; j < shipcount[3] ; j++){
				tplayers[i].addShip(new JBSCorvette());
			}
			*/
		}
		game = new Game(type, tplayers);
		
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
		return "GameManager | Players: " + players.size() + " | ShipCount: " + (shipcount[0] + shipcount[1] + shipcount[2] + shipcount[3]);
	}

	/**
	 * @return the players
	 */
	public final ArrayList<JBSPlayer> getPrePlayers() {
		return players;
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

}
