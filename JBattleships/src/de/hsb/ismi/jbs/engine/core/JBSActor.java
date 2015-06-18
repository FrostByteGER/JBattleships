/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({JBSShipActor.class})
public class JBSActor extends JBSObject{
	
	@XmlTransient
	private JBSPlayer player = null;
	@XmlElement(name = "ActorLocation")
	private Vector2i location = new Vector2i();
	@XmlElement(name = "ActorRotation")
	private Direction rotation = null;
	@XmlElement(name = "Hitted")
	private boolean isHit = false;
	@XmlElement(name = "Visibility")
	private boolean visibility = true;
	@XmlElement(name = "Name")
	private String name;
	@XmlTransient
	private JBSActorComponent components = new JBSActorComponent(new String[]{"watertest64.png"});
	
	/**
	 * 
	 */
	public JBSActor() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public JBSActor(String name) {
		//components =  new JBSActorComponent(new String[]{"watertest64.png","watertest64_1.png","watertest64_2.png"});

		this.name = name; // TODO
	}

	/**
	 * @param replicated
	 */
	public JBSActor(boolean replicated) {
		super(replicated);
	}

	/**
	 * @param player
	 * @param location
	 * @param rotation
	 * @param health
	 * @param visibility
	 */
	public JBSActor(JBSPlayer player, Vector2i location, Direction rotation, int health, boolean visibility, JBSActorComponent components) {
		super();
		this.player = player;
		this.location = location;
		this.rotation = rotation;
		this.visibility = visibility;
		this.components = components;
	}
	
	public boolean isControlledByPlayer(){
		return false;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	/**
	 * @return the player
	 */
	public final JBSPlayer getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public final void setPlayer(JBSPlayer player) {
		this.player = player;
	}

	/**
	 * @return the location
	 */
	public final Vector2i getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public final void setLocation(Vector2i location) {
		this.location = location;
	}
	
	/**
	 * @return the rotation
	 */
	public final Direction getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public final void setRotation(Direction rotation) {
		this.rotation = rotation;
	}

	/**
	 * @return the visibility
	 */
	public final boolean isVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public final void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the components
	 */
	public final JBSActorComponent getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public final void setComponents(JBSActorComponent components) {
		this.components = components;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
