/**
 * 
 */
package de.hsb.ismi.jbs.engine.actors.ships;

import de.hsb.ismi.jbs.engine.actors.JBSActorComponent;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.game.JBSDamageType;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSDestroyer extends JBSShip {
	
	/**
	 * 
	 */
	public JBSDestroyer() {
		super(3, 0, 5, JBSDamageType.DAMAGE_LARGE);
		setName(JBattleships.game.getLocalization("Destroyer"));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		addShipPart(new JBSShipActor("JBSDestroyer", this));
		
	}
	
	@Override
	public void setPositon(int x, int y, Direction direction) {
		super.setPositon(x, y, direction);
		
		for(int i = 0 ; i < getShipActors().size() ; i++){
			String[] animations = new String[]{"destroyer_"+i+"_"+direction.getStringDirection()+".png",
											   "destroyer_dmg_"+i+"_"+direction.getStringDirection()+".png"};
			getShipActors().get(i).setComponents(new JBSActorComponent(animations));
		}
	}
}
