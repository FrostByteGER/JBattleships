/**
 * 
 */
package de.hsb.ismi.jbs.engine.game.managers;

import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShipActor;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.game.GameStatistics;
import de.hsb.ismi.jbs.engine.game.HitInfo;
import de.hsb.ismi.jbs.engine.game.RoundListener;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * The RoundManager processes an entire round.
 * From the initial processing to the analyzing to the ending.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class RoundManager implements RoundListener{
	
	private boolean ended = false;
	
	private JBSPlayer target = null;
	@SuppressWarnings("unused")
	private JBSPlayer source = null;
	private JBSShip ship = null;
	
	private HitInfo hitInfo = new HitInfo();

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
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireRound(de.hsb.ismi.jbs.engine.core.JBSPlayer, de.hsb.ismi.jbs.engine.core.JBSPlayer, de.hsb.ismi.jbs.engine.core.JBSShip, int, int, de.hsb.ismi.jbs.engine.core.Direction)
	 */
	@Override
	public HitInfo fireRound(JBSPlayer target, JBSPlayer source, JBSShip ship, int x, int y, Direction direction) {
		this.target = target;
		this.source = source;
		this.ship = ship;
		if(ship.isAlive() && ship.canShoot()){
			hitInfo = ship.shoot(x, y, direction, target.getPlayerField());
			JBattleships.game.getGameManager().getGame().checkShipsHealth();
			target.checkIsAlive();
		}
		hitInfo.setSource(source);
		hitInfo.setTarget(target);
		hitInfo.setSourceShip(ship);
		return hitInfo;
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireAnalyzeRound()
	 */
	@Override
	public boolean fireAnalyzeRound(JBSPlayer source) {
		this.source = source;
		
		GameStatistics sourceStats = source.getStatistics();
		GameStatistics targetStats = target.getStatistics();
		
		sourceStats.increaseFiredShots(hitInfo.getDamageType().value);
		if(hitInfo.hasHit()){
			sourceStats.increaseShotsHit(hitInfo.getDamage());
			if(hitInfo.getHitActor() instanceof JBSShipActor){
				JBSShip ship = ((JBSShipActor)hitInfo.getHitActor()).getParent();
				sourceStats.increaseDestroyedShips(ship.isAlive() ? 1 : 0);
				targetStats.increaseLostShips(ship.isAlive() ? 0 : 1);
			}
		}else{
			sourceStats.increaseMissedShots(hitInfo.getDamageType().value);
		}
		
		
		if(JBattleships.game.getGameManager().getGame().isGameOver()){
			fireEndRound(source);
			sourceStats.setWin(true);
			boolean flawless = true;
			for(JBSShip ship : source.getShips()){
				if(!ship.isAlive()){
					flawless = false;
					break;
				}
			}
			sourceStats.setFlawlessWin(flawless);
			JBattleships.game.getGameManager().endGame(false);
			return true;
		}
		return false;
	}
	

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireEndRound()
	 */
	@Override
	public void fireEndRound(JBSPlayer source) {
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
		ended = false;
	}

}
