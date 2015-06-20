/**
 * 
 */
package de.hsb.ismi.jbs.engine.core.manager;

import java.rmi.RemoteException;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.JBSRoundListener;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class RoundManager implements JBSRoundListener{
	
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
	 * @return
	 */
	public boolean hasRoundEnded(){
		return ended;
	}

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.JBSRoundListener#fireRound(de.hsb.ismi.jbs.engine.core.JBSPlayer, de.hsb.ismi.jbs.engine.core.JBSPlayer, de.hsb.ismi.jbs.engine.core.JBSShip, int, int, de.hsb.ismi.jbs.engine.core.Direction)
	 */
	@Override
	public boolean processRound(JBSPlayer target, JBSPlayer source, JBSShip ship, int x, int y, Direction direction) {
		this.target = target;
		this.source = source;
		this.ship = ship;
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		if(ship.isAlive() && ship.canShoot()){
			boolean hit = ship.shoot(x, y, direction, target.getPlayerField());
			
			JBattleships.game.getGameManager().getGame().checkShipsHealth();
			target.checkIsAlive();
			return hit;
		}else{
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.JBSRoundListener#fireAnalyzeRound()
	 */
	@Override
	public void analyzeRound(JBSPlayer source) {
		this.source = source;
		target.getPlayerField().isFieldWaterHit(x, y);
		if(JBattleships.game.getGameManager().getGame().isGameOver()){
			endRound(source);
			JBattleships.game.getGameManager().endGame2(false);
		}else{
		}
	}
	

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.JBSRoundListener#fireEndRound()
	 */
	@Override
	public void endRound(JBSPlayer source) {
		this.source = source;
		source.decreaseCooldownAll();
		if(ship != null){
			ship.setMaxCooldown();
		}
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

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.JBSRoundListener#printRMITest(int)
	 */
	@Override
	public void printRMITest(int x) throws RemoteException {
		System.out.println("RMI reference was: " + x);
	}
	
}
