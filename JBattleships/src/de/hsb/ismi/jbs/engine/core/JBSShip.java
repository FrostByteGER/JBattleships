/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSShip {

	private int cooldownLimit;
	private int cooldown;
	private int length;
	private JBSDamageType damageType;
	private int shotpower;
	private String name;
	private int health;
	private ArrayList<JBSActor> shipActors;
	private Vector2i positon;
	private Direction direction;
	
	/**
	 * 
	 */
	public JBSShip() {
		name = "unknown";
		shipActors = new ArrayList<JBSActor>();
		direction = Direction.NORTH;
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
		this.shipActors = new ArrayList<JBSActor>();
		this.positon = new Vector2i();
		if(JBSDamageType.DAMAGE_SMALL == damageType){
			shotpower = 1;
		}else if(JBSDamageType.DAMAGE_MEDIUM == damageType){
			shotpower = 2;
		}else if(JBSDamageType.DAMAGE_LARGE == damageType){
			shotpower = 3;
		}
		
	}
	
	public void addShipPart(JBSActor part) {
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
	
	public void checkHealth() {
		
		health = length;
		
		if(!isAlife()){
			return;
		}
		
		for(JBSActor actor : this.shipActors){
			if(actor.isHit()){
				this.health--;
			}
		}
	}
	
	public boolean isAlife(){
		return (health > 0);
	}
	
	public boolean canShot(){
		return isAlife() && cooldown == 0;
	}
	
	public void shoot(int x, int y, Direction direction, JBSGameField field){
		
		for(int i = 0 ; i < shotpower ; i++){
			if(direction == Direction.NORTH){				
				field.shootField(x, y-i);
			}else if(direction == Direction.EAST){
				field.shootField(x+i, y);
			}else if(direction == Direction.SOUTH){
				field.shootField(x, y+i);
			}else if(direction == Direction.WEST){
				field.shootField(x-i, y);
			}		
		}
				
	}
	
	public void setPositon(int x, int y, Direction direction) {
		this.positon.setX(x);
		this.positon.setY(y);
		
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
		}
	}
	
	public Vector2i getPositon() {
		return positon;
	}
	
	public ArrayList<JBSActor> getShipActors() {
		return shipActors;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	
}
