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
public class JBSCorvette extends JBSShip {

	/**
	 * 
	 */
	public JBSCorvette() {
		super(1, 0, 3, JBSDamageType.DAMAGE_SMALL);
		setName(JBattleships.game.getLocalization("Corvette"));
		addShipPart(new JBSShipActor("JBSCorvette" ,this));	
		addShipPart(new JBSShipActor("JBSCorvette" ,this));
		addShipPart(new JBSShipActor("JBSCorvette", this));	
	}
	
	@Override
	public void setPositon(int x, int y, Direction direction) {
		super.setPositon(x, y, direction);
		
		for(int i = 0 ; i < getShipActors().size() ; i++){
			String[] animations = new String[]{"corvette_"+i+"_"+direction.getStringDirection()+".png",
											   "corvette_dmg_"+i+"_"+direction.getStringDirection()+".png"};
			getShipActors().get(i).setComponents(new JBSActorComponent(animations));
		}
	}
}
