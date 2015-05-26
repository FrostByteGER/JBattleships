/**
 * 
 */
package de.hsb.ismi.jbs.engine.core.manager;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.RoundListener;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSShip;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class RoundManager implements RoundListener{
	
	private boolean ended;
	
	private JBSPlayer target;
	private JBSPlayer source;
	private JBSShip ship;
	private int x;
	private int y;
	private Direction direction;

	public RoundManager(){
		this.target = null;
		this.source = null;
		this.ship = null;
		this.x = -1;
		this.y = -1;
		this.direction = null;
		ended = false;
	}
	
	private void processRound(){
		if(ship.isAlife() && ship.canShot()){
			ship.shoot(x, y, direction, target.getPlayerField());
		}
	}
	
	private void analyzeRound(){
		target.getPlayerField().isFieldWaterHit(x, y);
	}
	
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
	public void fireAnalyzeRound() {
		analyzeRound();
	}
	

	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.engine.core.RoundListener#fireEndRound()
	 */
	@Override
	public void fireEndRound() {
		ended = true;
	}
	
	
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
