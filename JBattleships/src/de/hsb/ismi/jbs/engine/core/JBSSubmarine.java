/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSSubmarine extends JBSShip {

	/**
	 * 
	 */
	public JBSSubmarine() {
		super(1, 0, 3, JBSDamageType.DAMAGE_SMALL);
		setName(JBattleships.game.getLocalization("Submarine"));
		addShipPart(new JBSShipActor("JBSSubmarine", this));
		addShipPart(new JBSShipActor("JBSSubmarine", this));
		addShipPart(new JBSShipActor("JBSSubmarine", this));
		
	}
	
	@Override
	public void setPositon(int x, int y, Direction direction) {
		super.setPositon(x, y, direction);
		
		for(int i = 0 ; i < getShipActors().size() ; i++){
			getShipActors().get(i).setComponents(new JBSActorComponent(new String[]{"submarine_"+i+"_"+direction.getStringDirection()+".png"}));
		}
	}
	
}
