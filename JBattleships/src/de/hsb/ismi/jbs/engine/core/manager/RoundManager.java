/**
 * 
 */
package de.hsb.ismi.jbs.engine.core.manager;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.FireListener;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSShip;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class RoundManager {

	public RoundManager(){
		
	}
	
	public void processRound(JBSPlayer player){
		new FireListener() {
			@Override
			public void fireShot(JBSPlayer target, JBSPlayer source, JBSShip ship ,int x, int y, Direction direction) {
				//ship.shoot(x, y, direction, target.getPlayerField());
				System.out.println("Derp");
			}
		};
		System.out.println("Herp");
	}
	
}
