/**
 * 
 */
package de.hsb.ismi.jbs.engine.actors.ships;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import de.hsb.ismi.jbs.engine.actors.JBSActor;
import de.hsb.ismi.jbs.engine.actors.JBSActorComponent;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSShipActor extends JBSActor {

	@XmlTransient
	private JBSShip parent;
	/**
	 * 
	 */
	public JBSShipActor() {
	}

	/**
	 * @param name
	 */
	public JBSShipActor(String name ,JBSShip parent) {
		super(name);
		this.parent = parent;
	}

	/**
	 * @param player
	 * @param location
	 * @param rotation
	 * @param health
	 * @param visibility
	 * @param components
	 */
	public JBSShipActor(JBSPlayer player, Vector2i location, Direction rotation, int health, boolean visibility, JBSActorComponent components) {
		super(player, location, rotation, health, visibility, components);
	}

	/**
	 * @return the parent
	 */
	public JBSShip getParent() {
		return parent;
	}
	
	public void afterUnmarshal(Unmarshaller u, Object parent) {
		this.parent = (JBSShip)parent;
	}
	
}
