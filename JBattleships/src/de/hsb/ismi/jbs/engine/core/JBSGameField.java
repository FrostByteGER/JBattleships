/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.engine.utility.Vector2i;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSGameField {

	private JBSActor[][] fields;
	private int size;
	private JBSPlayer player;
	public JBSActor water;
	public JBSActor water_hit;

	/**
	 * @param fields
	 * @param size
	 */
	public JBSGameField(JBSPlayer player, int size) {
		super();
		this.player = player;
		this.size = size;
		
		water = new JBSActor();
		water_hit = new JBSActor();
		
		water.setHit(false);
		water_hit.setHit(true);
		
		fields = new JBSActor[size][size];
		
		for(int i = 0 ; i < size ; i++){
			for (int j = 0 ; j < size ; j++){
				fields[i][j] = water;
			}
		}
		
	}
	
	public Vector2i mouseToFieldCoords(int x, int y, int fieldSize){
		return null;
	}

	/**
	 * @return the fields
	 */
	public final JBSActor[][] getFields() {
		return fields;
	}
	
	public JBSActor getField(int x,int y){
		return fields[x][y];
	}
	
	public boolean isFieldWater(int x ,int y){
		return(fields[x][y]==water);
	}
	
	public boolean isFieldWaterHit(int x ,int y){
		return(fields[x][y]==water_hit);
	}
	
	/**
	 * @param fields the fields to set
	 */
	public final void setFields(JBSActor[][] fields) {
		this.fields = fields;
	}
	
	public void shootField(int x,int y){
		if(x > size || y > size){
		}else{
			if(fields[x][y] == water){
				fields[x][y] = water_hit;
			}else if(fields[x][y] == water_hit){
			}else{
				fields[x][y].setHit(true);;
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
					}else if(fields[actor.getLocation().getX()+i][actor.getLocation().getY()+j] != water){
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
				fields[actor.getLocation().getX()][actor.getLocation().getY()] = actor;
			}
			return true;
		}
		return false;
	}
	
	// DEBUG
	public void printField(boolean visible){
		System.out.print("x/y");
		for(int i = 0 ; i < fields.length ;i++){
			System.out.print(i%10);
		}
		System.out.println();
		for(int i = 0 ; i < fields.length ; i++){
			if(i < 10){
				System.out.print(i+"  ");
			}else{
				System.out.print(i+" ");
			}
			for(int j = 0 ; j < fields[i].length ; j++){
				
				if(fields[i][j] == water){
					System.out.print("-");
				}else if(fields[i][j] == water_hit){
					System.out.print("X");
				}else if(visible){
					System.out.print("B");
				}else if(fields[i][j].isHit()){
					System.out.print("O");
				}else{
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
	
}
