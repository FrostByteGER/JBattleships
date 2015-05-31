package de.hsb.ismi.jbs.engine.core.manager;

import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSCorvette;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSFrigate;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSSubmarine;
import de.hsb.ismi.jbs.engine.io.manager.DataManager;

public class GameManager {
	
	private Game game;
	private ArrayList<JBSPlayer> players;
	private int[] shipcount;
	private RoundManager roundManager;
	
	public GameManager() {
		players = new ArrayList<JBSPlayer>();
		shipcount = new int[]{0,0,0,0};	
		game = null;
		roundManager = null;
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
		}
		
		game = new Game(type, tplayers);
		
		return game;
	}
	
	/**
	 * Starts the actual match with the current data.
	 */
	public void startGame(){
		if(game != null){
			roundManager = new RoundManager();
			runGame();
		}
	}
	
	/**
	 * Main game-loop
	 */
	public void runGame(){
		while(!game.isGameOver()){
			game.nextPlayer();
			while(!roundManager.hasRoundEnded()){ 
				//Wait till player fires {@link de.hsb.ismi.jbs.engine.core.RoundListener#fireEndRound() fireEndRound} 
			}
			roundManager.reset();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GameManager | Players: " + players.size() + " | ShipCount: " + shipcount.length;
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
	
}
