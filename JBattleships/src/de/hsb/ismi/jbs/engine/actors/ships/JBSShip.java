/**
 * 
 */
package de.hsb.ismi.jbs.engine.actors.ships;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;

import de.hsb.ismi.jbs.engine.actors.JBSActor;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.game.HitInfo;
import de.hsb.ismi.jbs.engine.game.JBSDamageType;
import de.hsb.ismi.jbs.engine.game.JBSGameField;
import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({JBSDestroyer.class, JBSFrigate.class, JBSCorvette.class, JBSSubmarine.class})
public class JBSShip{

	@XmlElement(name = "CooldownLimit")
	private int cooldownLimit;
	@XmlElement(name = "Cooldown")
	private int cooldown = -1;
	@XmlElement(name = "Shiplength")
	private int length = -1;
	@XmlElement(name = "DamageType")
	private JBSDamageType damageType;
	@XmlElement(name = "Shotpower")
	private int shotpower = -1;
	@XmlElement(name = "Shipname")
	private String name = "PLACEHOLDER";
	@XmlElement(name = "ShipHealth")
	private int health = -1;
	@XmlElement(name = "ShipElement")
	@XmlElementWrapper(name = "ShipElements")
	private ArrayList<JBSShipActor> shipActors = new ArrayList<JBSShipActor>(0);
	@XmlElement(name = "Position")
	private Vector2i position = new Vector2i();
	@XmlElement(name = "Direction")
	private Direction direction = null;
	
	/**
	 * beinhalted die Teile des Schiffes
	 */
	public JBSShip() {
	}
	
	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSShip(int cooldownLimit, int cooldown, int length, JBSDamageType damageType) {
		super();
		this.cooldownLimit = cooldownLimit;
		this.cooldown = cooldown;
		this.damageType = damageType;
		this.length = length;
		this.health = length;
		this.shipActors = new ArrayList<JBSShipActor>();
		if(JBSDamageType.DAMAGE_SMALL == damageType){
			shotpower = 1;
		}else if(JBSDamageType.DAMAGE_MEDIUM == damageType){
			shotpower = 2;
		}else if(JBSDamageType.DAMAGE_LARGE == damageType){
			shotpower = 3;
		}
	}
	
	/**
	 * @param cooldownLimit
	 * @param cooldown
	 * @param damageType
	 */
	public JBSShip(int cooldownLimit, int cooldown, int length, JBSDamageType damageType, Vector2i location, Direction direction) {
		super();
		this.cooldownLimit = cooldownLimit;
		this.cooldown = cooldown;
		this.damageType = damageType;
		this.length = length;
		this.health = length;
		this.position = location;
		this.direction = direction;
		this.shipActors = new ArrayList<JBSShipActor>();
		if(JBSDamageType.DAMAGE_SMALL == damageType){
			shotpower = 1;
		}else if(JBSDamageType.DAMAGE_MEDIUM == damageType){
			shotpower = 2;
		}else if(JBSDamageType.DAMAGE_LARGE == damageType){
			shotpower = 3;
		}
	}
	
	public void addShipPart(JBSShipActor part) {
		shipActors.add(part);
	}
	
	
	/**
	 * @return the cooldown
	 */
	public final int getCooldown() {
		return cooldown;
	}
	
	/**
	 * @param cooldown the cooldown to set
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public void setMaxCooldown(){
		cooldown = cooldownLimit;
	}
	
	public void subCooldown(){
		if(cooldown > 0){
			cooldown--;
		}
	}
	
	/**
	 * @return the cooldownLimit
	 */
	public final int getCooldownLimit() {
		return cooldownLimit;
	}
	
	/**
	 * @return the damageType
	 */
	public final JBSDamageType getDamageType() {
		return damageType;
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

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Updates shipHealth and return false if ship is destroyed. Otherwise returns true.
	 * @return
	 */
	public boolean checkHealth() {
		if(!isAlive()){
			return false;
		}
		health = length;
		for(JBSActor actor : this.shipActors){
			if(actor.isHit()){
				this.health--;
			}
		}
		return isAlive();
	}
	
	public boolean isAlive(){
		return (health > 0);
	}
	
	public boolean canShoot(){
		return isAlive() && cooldown == 0;
	}
	
	/**
	 * Shoots with the given position and rotation on the specified gamefield.
	 * @param x
	 * @param y
	 * @param direction
	 * @param field
	 * @return
	 */
	public HitInfo shoot(int x, int y, Direction direction, JBSGameField field){
		HitInfo info = new HitInfo();
		int damage = 0;
		for(int i = 0 ; i < shotpower ; i++){
			if(direction == Direction.NORTH){				
				if(field.shootField(x, y-i)){
					damage++;
				};
			}else if(direction == Direction.EAST){
				if(field.shootField(x+i, y)){
					damage++;
				};
			}else if(direction == Direction.SOUTH){
				if(field.shootField(x, y+i)){
					damage++;
				};
			}else if(direction == Direction.WEST){
				if(field.shootField(x-i, y)){
					damage++;
				};
			}		
		}
		info.setHitLocation(new Vector2i(x, y));
		info.setHitDirection(direction);
		info.setDamageType(this.damageType);
		info.setHitActor(field.getField(x, y));
		System.out.println(damage);
		System.out.println(damage != 0);
		info.setDamage(damage);
		info.setHasHit(damage != 0);
		return info;			
	}
	
	/**
	 * Sets the ships position.
	 * @param x
	 * @param y
	 * @param direction
	 */
	public void setPositon(int x, int y, Direction direction) {
		this.position.setX(x);
		this.position.setY(y);
		this.direction = direction;
		
		for(int i = 0 ; i < shipActors.size() ; i++){
			if(direction == Direction.NORTH){
				shipActors.get(i).getLocation().setX(x);
				shipActors.get(i).getLocation().setY(y-i);
			}else if(direction == Direction.EAST){
				shipActors.get(i).getLocation().setX(x+i);
				shipActors.get(i).getLocation().setY(y);
			}else if(direction == Direction.SOUTH){
				shipActors.get(i).getLocation().setX(x);
				shipActors.get(i).getLocation().setY(y+i);
			}else if(direction == Direction.WEST){
				shipActors.get(i).getLocation().setX(x-i);
				shipActors.get(i).getLocation().setY(y);
			}
			shipActors.get(i).setRotation(direction);
		}
	}
	
	public Vector2i getPositon() {
		return position;
	}
	
	public ArrayList<JBSShipActor> getShipActors() {
		return shipActors;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the shotpower
	 */
	public final int getShotpower() {
		return shotpower;
	}

	/**
	 * @param shotpower the shotpower to set
	 */
	public final void setShotpower(int shotpower) {
		this.shotpower = shotpower;
	}

	/**
	 * @return the position
	 */
	public final Vector2i getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public final void setPosition(Vector2i position) {
		this.position = position;
	}

	/**
	 * @param cooldownLimit the cooldownLimit to set
	 */
	public final void setCooldownLimit(int cooldownLimit) {
		this.cooldownLimit = cooldownLimit;
	}

	/**
	 * @param length the length to set
	 */
	public final void setLength(int length) {
		this.length = length;
	}

	/**
	 * @param damageType the damageType to set
	 */
	public final void setDamageType(JBSDamageType damageType) {
		this.damageType = damageType;
	}

	/**
	 * @param shipActors the shipActors to set
	 */
	public final void setShipActors(ArrayList<JBSShipActor> shipActors) {
		this.shipActors = shipActors;
	}
	
	
	
}
