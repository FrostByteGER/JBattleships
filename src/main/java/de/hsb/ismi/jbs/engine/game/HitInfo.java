/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

import de.hsb.ismi.jbs.engine.actors.JBSActor;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * HitInfo contains important information about an hit-event that was<br>
 * fired by a player.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class HitInfo {

	private JBSPlayer source = null;
	private JBSPlayer target = null;
	
	private JBSShip sourceShip = null;
	private JBSActor hitActor = null;
	
	private boolean hasHit = false;
	private int damage = 0;
	private Vector2i hitLocation = null;
	private Direction hitDirection = null;
	private JBSDamageType damageType = null;

	/**
	 * 
	 */
	public HitInfo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param source
	 * @param target
	 * @param sourceShip
	 * @param hitActor
	 * @param hasHit
	 * @param hitLocation
	 * @param hitDirection
	 * @param damageType
	 */
	public HitInfo(JBSPlayer source, JBSPlayer target, JBSShip sourceShip, JBSActor hitActor, boolean hasHit, int damage,
			Vector2i hitLocation, Direction hitDirection, JBSDamageType damageType) {
		this.source = source;
		this.target = target;
		this.sourceShip = sourceShip;
		this.hitActor = hitActor;
		this.hasHit = hasHit;
		this.damage = damage;
		this.hitLocation = hitLocation;
		this.hitDirection = hitDirection;
		this.damageType = damageType;
	}


	/**
	 * @return the source
	 */
	public final JBSPlayer getSource() {
		return source;
	}


	/**
	 * @param source the source to set
	 */
	public final void setSource(JBSPlayer source) {
		this.source = source;
	}


	/**
	 * @return the target
	 */
	public final JBSPlayer getTarget() {
		return target;
	}


	/**
	 * @param target the target to set
	 */
	public final void setTarget(JBSPlayer target) {
		this.target = target;
	}


	/**
	 * @return the sourceShip
	 */
	public final JBSShip getSourceShip() {
		return sourceShip;
	}


	/**
	 * @param sourceShip the sourceShip to set
	 */
	public final void setSourceShip(JBSShip sourceShip) {
		this.sourceShip = sourceShip;
	}


	/**
	 * @return the hitActor
	 */
	public final JBSActor getHitActor() {
		return hitActor;
	}


	/**
	 * @param hitActor the hitActor to set
	 */
	public final void setHitActor(JBSActor hitActor) {
		this.hitActor = hitActor;
	}


	/**
	 * @return the hasHit
	 */
	public final boolean hasHit() {
		return hasHit;
	}


	/**
	 * @param hasHit the hasHit to set
	 */
	public final void setHasHit(boolean hasHit) {
		this.hasHit = hasHit;
	}


	/**
	 * @return the damage
	 */
	public final int getDamage() {
		return damage;
	}


	/**
	 * @param damage the damage to set
	 */
	public final void setDamage(int damage) {
		this.damage = damage;
	}


	/**
	 * @return the hitLocation
	 */
	public final Vector2i getHitLocation() {
		return hitLocation;
	}


	/**
	 * @param hitLocation the hitLocation to set
	 */
	public final void setHitLocation(Vector2i hitLocation) {
		this.hitLocation = hitLocation;
	}


	/**
	 * @return the hitDirection
	 */
	public final Direction getHitDirection() {
		return hitDirection;
	}


	/**
	 * @param hitDirection the hitDirection to set
	 */
	public final void setHitDirection(Direction hitDirection) {
		this.hitDirection = hitDirection;
	}


	/**
	 * @return the damageType
	 */
	public final JBSDamageType getDamageType() {
		return damageType;
	}


	/**
	 * @param damageType the damageType to set
	 */
	public final void setDamageType(JBSDamageType damageType) {
		this.damageType = damageType;
	}
	
	
}
