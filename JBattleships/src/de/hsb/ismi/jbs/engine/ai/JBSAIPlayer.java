/**
 * 
 */
package de.hsb.ismi.jbs.engine.ai;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import de.hsb.ismi.jbs.engine.actors.ships.JBSCorvette;
import de.hsb.ismi.jbs.engine.actors.ships.JBSDestroyer;
import de.hsb.ismi.jbs.engine.actors.ships.JBSFrigate;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShipActor;
import de.hsb.ismi.jbs.engine.actors.ships.JBSSubmarine;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.game.Game;
import de.hsb.ismi.jbs.engine.game.HitInfo;
import de.hsb.ismi.jbs.engine.game.JBSGameField;
import de.hsb.ismi.jbs.engine.game.managers.GameManager;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.engine.players.JBSProfile;
import de.hsb.ismi.jbs.engine.utility.Vector2i;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSAIPlayer extends JBSPlayer {
	
	public static int DIFFICULTY_RETARDED;
	public static int DIFFICULTY_EASY;
	public static int DIFFICULTY_MEDIUM;
	public static int DIFFICULTY_HARD;
	
	@XmlElement(name = "HitDirection")
	private Direction hitdirection = Direction.NONE;
	@XmlElement(name = "HitX")
	private int hitx = 0;
	@XmlElement(name = "HitY")
	private int hity = 0;
	@XmlElement(name = "HitFieldIndex")
	private int hitfield = 0;
	
	@XmlElement(name = "HitFields")
	private ArrayList<Vector2i> hitfields;
	@XmlElement(name = "HitShip")
	private JBSShip hitship;
	@XmlElement(name = "LastHits")
	private ArrayList<Vector2i> lasthits;
	
	//TODO: What?
	@XmlElement(name = "hasHit")
	private boolean hit = false;
	@XmlElement(name = "LastHitDirection")
	private Direction lasthitdirection = Direction.NONE;
	@XmlElement(name = "LastHitX")
	private int lasthitx = 0;
	@XmlElement(name = "LastHitY")
	private int lasthity = 0;
	@XmlElement(name = "LastHitFieldIndex")
	private int lasthitfield = 0;
	@XmlElement(name = "HasLastShotHit")
	private boolean lasthit = false;
	@XmlTransient
	private Random r = new Random();
	
	/**
	 * 
	 */
	public JBSAIPlayer() {
		super(new JBSProfile());
		init();
	}

	/**
	 * @param name
	 */
	public JBSAIPlayer(String name) {
		super(name);
		init();
	}
	
	private void init(){
		hitfields = new ArrayList<Vector2i>();
		lasthits = new ArrayList<Vector2i>();
	}
	
	public JBSShip processRound(Game game){

		for(JBSShip ship : getShips()){
			if(ship.canShoot()){
				
				hitx = 0;
				hity = 0;
				
				if(lasthit){
					for(int x = 0 ; x < game.getPlayer(lasthitfield).getPlayerField().getSize() && hitx == 0 ; x++){
						for(int y = 0 ; y < game.getPlayer(lasthitfield).getPlayerField().getSize() && hitx == 0 ; y++){
														
							if(game.getPlayer(lasthitfield).getPlayerField().getField(x, y) instanceof JBSShipActor &&
									game.getPlayer(lasthitfield).getPlayerField().getField(x, y).isHit()){
								
								if(game.getPlayer(lasthitfield).getPlayerField().getField(x, y-1) != null){
									if(!game.getPlayer(lasthitfield).getPlayerField().getField(x, y-1).isHit()){
										hitx = x;
										hity = y-1;
										hitdirection = Direction.NORTH;
									}
								}
								if(game.getPlayer(lasthitfield).getPlayerField().getField(x+1, y) != null){
									if(!game.getPlayer(lasthitfield).getPlayerField().getField(x+1, y).isHit()){
										hitx = x+1;
										hity = y;
										hitdirection = Direction.EAST;
									}
								}
								if(game.getPlayer(lasthitfield).getPlayerField().getField(x, y+1) != null){
									if(!game.getPlayer(lasthitfield).getPlayerField().getField(x, y+1).isHit()){
										hitx = x;
										hity = y+1;
										hitdirection = Direction.SOUTH;
									}
								}
								if(game.getPlayer(lasthitfield).getPlayerField().getField(x-1, y) != null){
									if(!game.getPlayer(lasthitfield).getPlayerField().getField(x-1, y).isHit()){
										hitx = x-1;
										hity = y;
										hitdirection = Direction.WEST;
									}
								}					
							}
						}
					}
					
					hitdirection = Direction.getRandomDirection(r);
					
					HitInfo h = JBattleships.game.getGameManager().getRoundManager().fireRound(game.getPlayer(lasthitfield), this, ship, hitx, hity, hitdirection);
					hit = h.hasHit();
					JBattleships.game.getGameManager().getRoundManager().fireAnalyzeRound(this);
					JBattleships.game.getGameManager().getRoundManager().fireEndRound(this);
					
					if(game.getPlayer(lasthitfield).getPlayerField().getField(hitx, hity) instanceof JBSShipActor){
						JBSShipActor tempactor = (JBSShipActor)game.getPlayer(lasthitfield).getPlayerField().getField(hitx, hity);
												
						if(!tempactor.getParent().checkHealth()){
							lasthit = false;
						}	
					}
					
					break;
					
				}else{
					hitfield = r.nextInt(game.getPlayers().length);
					for(int i = 0 ; i < game.getPlayers().length ; i++){
						
						hitfield = (hitfield+i)%game.getPlayers().length;
						
						if(game.getPlayer(hitfield).isAlive() && game.getPlayer(hitfield) != this){
								
							while(true){
								hitx = r.nextInt(game.getActivePlayer().getPlayerField().getSize()-1);
								hity = r.nextInt(game.getActivePlayer().getPlayerField().getSize()-1);
								
								if(!game.getPlayer(hitfield).getPlayerField().getField(hitx, hity).isHit()){
									break;
								}
							}	
							hitdirection = Direction.getRandomDirection(r);
														
							hit = JBattleships.game.getGameManager().getRoundManager().fireRound(game.getPlayer(hitfield), this, ship, hitx, hity, hitdirection).hasHit();
							
							DebugLog.logInfo(lasthit + " " + hitx + " " + hity + " "+ hitfield);
							
							HitInfo h = JBattleships.game.getGameManager().getRoundManager().fireRound(game.getPlayer(hitfield), this, ship, hitx, hity, hitdirection);
							hit = h.hasHit();
							JBattleships.game.getGameManager().getRoundManager().fireAnalyzeRound(this);
							JBattleships.game.getGameManager().getRoundManager().fireEndRound(this);
							
							
							lasthitfield  = hitfield;
							lasthit = hit;		
							
							
						}else{
							continue;
						}
					}
					return ship;
				}
			}
		}
		return null;
	}

	public void placeShips(){
		
		GameManager manager = JBattleships.game.getGameManager();
		JBSShip ship = null;
		
		for(int i = 0 ; i < manager.getGame().getDestroyerCount() ; i++){		
			ship = new JBSDestroyer();		
			placeShip(getPlayerField(), ship);
			addShip(ship);
		}
		
		for(int i = 0 ; i < manager.getGame().getFrigateCount() ; i++){		
			ship = new JBSFrigate();
			placeShip(getPlayerField(), ship);
			addShip(ship);
		}
		
		for(int i = 0 ; i < manager.getGame().getCorvetteCount() ; i++){
			ship = new JBSCorvette();			
			placeShip(getPlayerField(), ship);
			addShip(ship);
		}
		
		for(int i = 0 ; i < manager.getGame().getSubmarineCount(); i++){			
			ship = new JBSSubmarine();			
			placeShip(getPlayerField(), ship);
			addShip(ship);
		}
	}
	
	private void placeShip(JBSGameField field, JBSShip ship){
		
		while(true){
			ship.setPositon(r.nextInt(field.getSize()), r.nextInt(field.getSize()), Direction.getRandomDirection(r));
			
			if(field.shipCanBePlaced(ship)){
				field.addShip(ship);
				break;
			}
		}	
	}

	/**
	 * @return the hitdirection
	 */
	public final Direction getHitdirection() {
		return hitdirection;
	}

	/**
	 * @param hitdirection the hitdirection to set
	 */
	public final void setHitdirection(Direction hitdirection) {
		this.hitdirection = hitdirection;
	}

	/**
	 * @return the hitx
	 */
	public final int getHitx() {
		return hitx;
	}

	/**
	 * @param hitx the hitx to set
	 */
	public final void setHitx(int hitx) {
		this.hitx = hitx;
	}

	/**
	 * @return the hity
	 */
	public final int getHity() {
		return hity;
	}

	/**
	 * @param hity the hity to set
	 */
	public final void setHity(int hity) {
		this.hity = hity;
	}

	/**
	 * @return the hitfield
	 */
	public final int getHitfield() {
		return hitfield;
	}

	/**
	 * @param hitfield the hitfield to set
	 */
	public final void setHitfield(int hitfield) {
		this.hitfield = hitfield;
	}

	/**
	 * @return the hit
	 */
	public final boolean isHit() {
		return hit;
	}

	/**
	 * @param hit the hit to set
	 */
	public final void setHit(boolean hit) {
		this.hit = hit;
	}

	/**
	 * @return the lasthitdirection
	 */
	public final Direction getLasthitdirection() {
		return lasthitdirection;
	}

	/**
	 * @param lasthitdirection the lasthitdirection to set
	 */
	public final void setLasthitdirection(Direction lasthitdirection) {
		this.lasthitdirection = lasthitdirection;
	}

	/**
	 * @return the lasthitx
	 */
	public final int getLasthitx() {
		return lasthitx;
	}

	/**
	 * @param lasthitx the lasthitx to set
	 */
	public final void setLasthitx(int lasthitx) {
		this.lasthitx = lasthitx;
	}

	/**
	 * @return the lasthity
	 */
	public final int getLasthity() {
		return lasthity;
	}

	/**
	 * @param lasthity the lasthity to set
	 */
	public final void setLasthity(int lasthity) {
		this.lasthity = lasthity;
	}

	/**
	 * @return the lasthitfield
	 */
	public final int getLasthitfield() {
		return lasthitfield;
	}

	/**
	 * @param lasthitfield the lasthitfield to set
	 */
	public final void setLasthitfield(int lasthitfield) {
		this.lasthitfield = lasthitfield;
	}

	/**
	 * @return the lasthit
	 */
	public final boolean isLasthit() {
		return lasthit;
	}

	/**
	 * @param lasthit the lasthit to set
	 */
	public final void setLasthit(boolean lasthit) {
		this.lasthit = lasthit;
	}
	
}
