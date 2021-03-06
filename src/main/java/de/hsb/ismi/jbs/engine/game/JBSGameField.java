/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import de.hsb.ismi.jbs.engine.actors.JBSActor;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShipActor;
import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * The GameField is the logical representation of the classic board.
 * Ships are stored here.
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSGameField{

	@XmlElement(name = "GamefieldActor")
	@XmlElementWrapper(name = "GamefieldActors")
	private JBSActor[][] actorFields = null;
	@XmlElement(name = "GamefieldSize")
	private int size = 0;
	
	// OLD TODO
	@XmlTransient
	private JBSActor water = new JBSActor("water");
	@XmlTransient
	private JBSActor waterHitDummy = new JBSActor("waterhit");
	
	/**
	 * 
	 */
	public JBSGameField() {
		water.setHit(false);
		waterHitDummy.setHit(true);
	}
	
	/**
	 * @param size
	 */
	public JBSGameField(int size) {
		this.size = size;
		
		water.setHit(false);
		waterHitDummy.setHit(true);
		
		actorFields = new JBSActor[size][size];
		
		for(int i = 0 ; i < size ; i++){
			for (int j = 0 ; j < size ; j++){
				actorFields[i][j] = new JBSActor("water");
			}
		}
	}
	
	public Vector2i mouseToFieldCoords(int x, int y, int fieldSize){
		return null;
	}

	/**
	 * @return the actorFields
	 */
	public final JBSActor[][] getFields() {
		return actorFields;
	}
	
	public JBSActor getField(int x,int y){
		if(x < 0 || y < 0 || x > size-1 || y > size-1){
			return null;
		}
		return actorFields[x][y];
	}
	
	public boolean isFieldWater(int x ,int y){
		return(actorFields[x][y].getName().equals("water"));
	}
	
	public boolean isFieldWaterHit(int x ,int y){
		return(actorFields[x][y].getName().equals("waterhit"));
	}
	
	/**
	 * @param actorFields the actorFields to set
	 */
	public final void setActorFields(JBSActor[][] fields) {
		this.actorFields = fields;
	}
	
	public void resetActorFields(){
		for(int i = 0 ; i < size ; i++){
			for(int j = 0 ; j < size ; j++){
				this.actorFields[i][j] = new JBSActor("water");
			}	
		}
	}
	
	public boolean shootField(int x,int y){
		if(x > size-1 || y > size-1 || y < 0 || x < 0){
			return false;
		}else{
			if(!actorFields[x][y].isHit()){
				actorFields[x][y].setHit(true);
				if(actorFields[x][y].getName().equals("water")){
					actorFields[x][y].setName("waterhit");
					return false;
				}
				if(actorFields[x][y] instanceof JBSShipActor){
					JBSShipActor temp = (JBSShipActor)actorFields[x][y];
					
					//TODO
					
					if(!temp.getParent().checkHealth()){
						shootAroundShip(temp.getParent());
					}	
				}
				
				return true;
			}
			return false;
		}
	}
	
	public void shootAroundShip(JBSShip ship){
		for(JBSShipActor a : ship.getShipActors()){
			for(int i = -1 ; i < 2 ; i++){
				for(int j = -1 ; j < 2 ; j++){
					shootField(a.getLocation().getX()+i, a.getLocation().getY()+j);
				}
			}
		}
	}
	
	/**
	 * @return the size
	 */
	public final int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public final void setSize(int size) {
		this.size = size;
	}
	
	public boolean shipCanBePlaced(JBSShip ship){
					
		for(JBSActor actor : ship.getShipActors()){
			if(actor.getLocation().getX() < 0 || actor.getLocation().getX() >= size||
					actor.getLocation().getY() < 0 || actor.getLocation().getY() >= size){
				
				return false;
			}
			
			for(int i = -1 ; i < 2 ; i++){
				for(int j = -1 ; j < 2 ; j++){
					if(actor.getLocation().getX()+i < 0 || actor.getLocation().getX()+i >= size){
						continue;
					}else if(actor.getLocation().getY()+j < 0 || actor.getLocation().getY()+j >= size){
						continue;
					}else if(!actorFields[actor.getLocation().getX()+i][actor.getLocation().getY()+j].getName().equals("water")){
						return false;
					}
				}
			}
		}
		return true;
	}
		
	public boolean addShip(JBSShip ship){
		if(shipCanBePlaced(ship)){
			for(JBSActor actor : ship.getShipActors()){
				actorFields[actor.getLocation().getX()][actor.getLocation().getY()] = actor;
			}
			return true;
		}
		return false;
	}
	
	public void setShip(JBSShip ship){
		for(JBSActor actor : ship.getShipActors()){
			actorFields[actor.getLocation().getX()][actor.getLocation().getY()] = actor;
		}
	}
	
	// DEBUG_MODE
	public void printField(boolean visible){
		System.out.print("x/y");
		for(int i = 0 ; i < actorFields.length ;i++){
			
			if(i < 10){
				System.out.print(" "+i+" ");
			}else{
				System.out.print(i+" ");
			}
		}
		System.out.println();
		for(int i = 0 ; i < actorFields.length ; i++){
			if(i < 10){
				System.out.print(i+"  ");
			}else{
				System.out.print(i+" ");
			}
			for(int j = 0 ; j < actorFields[i].length ; j++){
				
				if(actorFields[i][j] == water){
					System.out.print("--");
				}else if(actorFields[i][j] == waterHitDummy){
					System.out.print("~~");
				}else if(visible){
					System.out.print("BB");
				}else if(actorFields[i][j].isHit()){
					System.out.print("XX");
				}else{
					System.out.print("--");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
}
