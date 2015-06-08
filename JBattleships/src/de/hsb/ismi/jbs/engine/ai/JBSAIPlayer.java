/**
 * 
 */
package de.hsb.ismi.jbs.engine.ai;

import java.io.ObjectInputStream.GetField;
import java.util.Random;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSCorvette;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSFrigate;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSProfile;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSSubmarine;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class JBSAIPlayer extends JBSPlayer {
	
	public static int DIFFICULTIE_DUM;
	public static int DIFFICULTIE_LOW;
	public static int DIFFICULTIE_MID;
	public static int DIFFICULTIE_PRO;
	
	private Direction hitdirection;
	private int hitx;
	private int hity;
	private int hitfield;
	
	private Direction lasthitdirection;
	private int lasthitx;
	private int lasthity;
	private int lasthitfield;
	
	private boolean lasthit;
	
	private Random r;
	
	/**
	 * 
	 */
	public JBSAIPlayer() {
		super(new JBSProfile());
		
		hitdirection = Direction.NONE;
		hitx = -1;
		hity = -1;
		hitfield = -1;
		
		lasthitdirection = Direction.NONE;
		lasthitx = -1;
		lasthity = -1;
		lasthitfield = -1;
		
		lasthit = false;
		
		r = new Random();		
	}

	/**
	 * @param name
	 */
	public JBSAIPlayer(String name) {
		super(name);
		hitdirection = Direction.NONE;
		hitx = 0;
		hity = 0;
		hitfield = 0;
		
		lasthitdirection = Direction.NONE;
		lasthitx = 0;
		lasthity = 0;
		lasthitfield = 0;
		
		lasthit = false;
		
		r = new Random();	
	}

	public void shoot(Game game){
		
		for(JBSShip ship : getShips()){
			if(ship.canShot()){
				if(lasthit != lasthit){
					//TODO
				}else{
					hitfield = r.nextInt(game.getPlayers().length);
					for(int i = 0 ; i < game.getPlayers().length ; i++){
						
						hitfield = (lasthitfield+i)%game.getPlayers().length;
						
						if(game.getPlayer(hitfield).isAlive()){
							
							hitx = r.nextInt(game.getActivePlayer().getPlayerField().getSize());
							hity = r.nextInt(game.getActivePlayer().getPlayerField().getSize());
							
							hitdirection = Direction.getRandomDirection(r);
							
							ship.shoot(hitx, hity, hitdirection, game.getPlayer(hitfield).getPlayerField());
							
						}else{
							continue;
						}
					}
				}
			}
		}	
	}
	
	
	public void placeShips(GameManager manager){
		
		JBSShip ship;
		
		for(int i = 0 ; i < manager.getDestroyerCount() ; i++){		
			ship = new JBSDestroyer();		
			placeShip(getPlayerField(), ship);
		}
		
		for(int i = 0 ; i < manager.getFrigateCount() ; i++){		
			ship = new JBSFrigate();
			placeShip(getPlayerField(), ship);
		}
		
		for(int i = 0 ; i < manager.getCorvetteCount() ; i++){
			ship = new JBSCorvette();			
			placeShip(getPlayerField(), ship);
		}
		
		for(int i = 0 ; i < manager.getSubmarineCount(); i++){			
			ship = new JBSSubmarine();			
			placeShip(getPlayerField(), ship);
		}
		
	}
	
	private void placeShip(JBSGameField field, JBSShip ship){
		
		while(true){
			ship.setPositon(r.nextInt(field.getSize()), r.nextInt(field.getSize()), Direction.getRandomDirection(r));
			
			if(field.shipCanBePlaced(ship)){
				field.setShip(ship);
				break;
			}
		}	
	}
	
}
