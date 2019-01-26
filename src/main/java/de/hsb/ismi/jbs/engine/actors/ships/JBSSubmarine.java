/**
 * 
 */
package de.hsb.ismi.jbs.engine.actors.ships;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import de.hsb.ismi.jbs.engine.actors.JBSActorComponent;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.game.JBSDamageType;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
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
			String[] animations = new String[]{"submarine_"+i+"_"+direction.getStringDirection()+".png",
											   "submarine_dmg_"+i+"_"+direction.getStringDirection()+".png"};
			getShipActors().get(i).setComponents(new JBSActorComponent(animations));
		}
	}
	
}
