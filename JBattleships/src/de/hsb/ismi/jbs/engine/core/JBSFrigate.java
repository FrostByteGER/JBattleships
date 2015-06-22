/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSFrigate extends JBSShip {

	/**
	 * 
	 */
	public JBSFrigate() {
		super(2, 0, 2, JBSDamageType.DAMAGE_MEDIUM);
		setName(JBattleships.game.getLocalization("Frigate"));
		addShipPart(new JBSShipActor("JBSFrigate",this));
		addShipPart(new JBSShipActor("JBSFrigate", this));
		
	}
	
	@Override
	public void setPositon(int x, int y, Direction direction) {
		super.setPositon(x, y, direction);
		
		for(int i = 0 ; i < getShipActors().size() ; i++){
			getShipActors().get(i).setComponents(new JBSActorComponent(new String[]{"frigatte_"+i+"_"+direction.getStringDirection()+".png"}));
		}
	}
	
}
