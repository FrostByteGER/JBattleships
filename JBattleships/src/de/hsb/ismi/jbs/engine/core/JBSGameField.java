/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JBSGameField {

	@XmlTransient
	private JBSActor[][] actorFields = null;
	@XmlElement(name = "GameFieldSize")
	private int size = -1;
	
	// OLD TODO
	@XmlTransient
	public JBSActor water = new JBSActor("water");
	@XmlTransient
	public JBSActor waterHitDummy = new JBSActor("waterhit");
	
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
		if(x > size || y > size || y < 0 || x < 0){
			return false;
		}else{
			actorFields[x][y].setHit(true);
			if(actorFields[x][y].getName().equals("water")){
				actorFields[x][y].setName("waterhit");
				return false;
			}
			return true;
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
		
	public boolean setShip(JBSShip ship){
		if(shipCanBePlaced(ship)){
			for(JBSActor actor : ship.getShipActors()){
				actorFields[actor.getLocation().getX()][actor.getLocation().getY()] = actor;
			}
			return true;
		}
		return false;
	}
	
	// DEBUG
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
