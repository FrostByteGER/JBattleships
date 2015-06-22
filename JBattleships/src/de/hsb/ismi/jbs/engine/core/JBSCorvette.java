/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.gui.ShipStatLabel;
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
			getShipActors().get(i).setComponents(new JBSActorComponent(new String[]{"corvette_"+i+"_"+direction.getStringDirection()+".png"}));
		}
	}
}
