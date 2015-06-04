/**
 * 
 */
package de.hsb.ismi.jbs.engine.core.manager;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.RoundListener;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class RoundManager implements RoundListener{
	
	private boolean ended = false;
	
	private JBSPlayer target = null;
	private JBSPlayer source = null;
	private JBSShip ship = null;
	private int x = -1;
	private int y = -1;
	private Direction direction = null;

	public RoundManager(){
	}
	
	/**
	 * 
	 */
	private void processRound(){
		if(ship.isAlife() && ship.canShot()){
			ship.shoot(x, y, direction, target.getPlayerField());
			
			// Decreases the cooldown of all ships of the player.

			
			ship.setMaxCooldown();
			JBattleships.game.getGameManager().getGame().checkShipsHealth();
			target.checkIsAlive();
		}
	}
	
	/**
	 * 
	 */
	private void analyzeRound(){
		target.getPlayerField().isFieldWaterHit(x, y);
		//TODO: Fire GameEnd event!
		if(JBattleships.game.getGameManager().getGame().isGameOver()){
			fireEndRound(source);
			JBattleships.game.getGameManager().endGame(false);
		}else{
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasRoundEnded(){
		return ended;
	}

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireRound(de.hsb.ismi.jbs.engine.core.JBSPlayer, de.hsb.ismi.jbs.engine.core.JBSPlayer, de.hsb.ismi.jbs.engine.core.JBSShip, int, int, de.hsb.ismi.jbs.engine.core.Direction)
	 */
	@Override
	public void fireRound(JBSPlayer target, JBSPlayer source, JBSShip ship, int x, int y, Direction direction) {
		this.target = target;
		this.source = source;
		this.ship = ship;
		this.x = x;
		this.y = y;
		this.direction = direction;
		processRound();
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireAnalyzeRound()
	 */
	@Override
	public void fireAnalyzeRound(JBSPlayer source) {
		this.source = source;
		analyzeRound();
	}
	

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireEndRound()
	 */
	@Override
	public void fireEndRound(JBSPlayer source) {
		this.source = source;
		source.decreaseCooldownAll();
		ended = true;
	}
	
	/**
	 * Resets the RoundManager to a neutral state. Call after round has ended.
	 */
	public void reset(){
		this.target = null;
		this.source = null;
		this.ship = null;
		this.x = -1;
		this.y = -1;
		this.direction = null;
		ended = false;
	}
	
}
