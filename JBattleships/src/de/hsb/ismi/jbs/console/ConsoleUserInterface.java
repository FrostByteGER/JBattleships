package de.hsb.ismi.jbs.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.io.manager.*;
import de.hsb.ismi.jbs.engine.core.*;

public class ConsoleUserInterface {
	
	private BufferedReader read;
	private Game game;
	private int intinput;
	private String stringinput;
	private int playeramount;
	private int fieldsize;
	
	public ConsoleUserInterface() {
		
		read = new BufferedReader( new InputStreamReader(System.in));
		
		startGame();
		
	}
	
	private void startGame(){
		
		PreGameManager pm = new PreGameManager(new DataManager());
		
		intinput = 0;
		
		while(intinput< 1 || intinput > 6){
			 readInt("Player amount 2-6");		
		}
		
		playeramount = intinput;
		
		while(intinput < 8 || intinput > 20){
			readInt("Fieldsize 8-20");
		}
		
		fieldsize = intinput;
		// TODO Spielername
		
		for(int i = 0 ; i < playeramount ; i++){
			pm.addPlayer(new JBSPlayer());
		}
		
		pm.setCorvetteCount(0);
		pm.setDestroyerCount(0);
		pm.setFrigateCount(0);
		pm.setSubmarineCount(1);
		
		game = pm.createGame(JBSGameType.GAME_LOCAL, fieldsize);
		
		placeShips();
		
		while(!game.isGameOver()){
			for(int i = 0 ; i < game.getPlayers().length ; i++){
				round(i);
			}
		}
		
		// TODO
		System.out.println("ENDE");
		
	}
	
	private void placeShips(){
		
		for(int i = 0 ; i < game.getPlayers().length ;i++){
			
			System.out.println(game.getPlayers().length+" "+i);
			
			JBSPlayer player = game.getPlayers()[i];
			
			int x = 0;
			int y = 0;
			
			for(JBSShip ship : player.getShips()){
				
				do {
					System.out.println("Player "+i);
					game.getGameField()[i].printField(true);
					System.out.println("Shiptype: "+ship.getName());
					readInt("Ship x");
					x = intinput;
					readInt("Ship y");
					y = intinput;
					
					while(true){
						printDirections();
						System.out.println("Ship Direction (n,o,s,w) type: "+ship.getName());
						readString();
						switch (stringinput) {
						case "n":ship.setDirection(Direction.NORTH);break;
						case "o":ship.setDirection(Direction.EAST);break;
						case "s":ship.setDirection(Direction.SOUTH);break;
						case "w":ship.setDirection(Direction.WEST);break;						
						default:System.out.println("No Direction (n,o,s,w)");continue;
						}
						break;

					}
					
					ship.setPositon(x,y,ship.getDirection());
					
					System.out.println(game.getGameField()[i].shipCanBePlaced(ship));
					
				} while (!game.getGameField()[i].shipCanBePlaced(ship));
				game.getGameField()[i].setShip(ship);
			}
			game.getGameField()[i].printField(true);
		}
	}
	
	private void round(int player){
		
		printShips(player);
		
		int shootat = 0;
		int xshoot = -1;
		int yshoot = -1;
		
		JBSShip ship;
		
		Direction direction;
		
		while(!stringinput.equals("y")){
			
			stringinput = "";
			
			do {
				for(int i = 0 ; i < game.getPlayers().length ; i++){
					if(game.getPlayers()[i].isAlive()&& i != player){
						System.out.println(i+" Player is Alive "+game.getPlayers()[i].isAlive());
					}
				}
				readInt("shoot at");
				
			} while (!game.getPlayers()[intinput].isAlive() || (intinput == player));
			
			shootat = intinput;
			
			game.getGameField()[shootat].printField(false);
			
			while(!(stringinput.equals("y") || stringinput.endsWith("n"))){
				System.out.println("Do you want to shoot him? y/n");
				readString();
			}
		}
		
		do {
			printShips(player);
			readInt("shot with");
		} while (!game.getPlayers()[player].getShips().get(intinput).isAlife() &&
				game.getPlayers()[player].getShips().get(intinput).getCooldown() > 0);
		
		ship = game.getPlayers()[player].getShips().get(intinput);
		
		while(!(xshoot < fieldsize && xshoot >= 0 && yshoot < fieldsize && yshoot >= 0)){
			
			readInt("shoot at X");
			xshoot = intinput;
			readInt("shoot at Y");
			yshoot = intinput;
		}
		
		while(true){
			printDirections();
			System.out.println("shoot in Direction (n,o,s,w) type: "+ship.getName());
			readString();
			switch (stringinput) {
			case "n":direction = Direction.NORTH;break;
			case "o":direction = Direction.EAST;break;
			case "s":direction = Direction.SOUTH;break;
			case "w":direction = Direction.WEST;break;						
			default:System.out.println("No Direction (n,o,s,w)");continue;
			}
			break;

		}
		
		ship.shot(xshoot, yshoot, direction, game.getGameField()[shootat]);
	}
	
	private void printDirections(){
		System.out.println("  w");
		System.out.println("n + s");
		System.out.println("  o");
	}
	
	private void printShips(int player){
		
		JBSShip s;
		
		for(int i = 0 ; i < game.getPlayers()[player].getShips().size() ; i++){
			s = game.getPlayers()[player].getShips().get(i);
			System.out.println(i+" "+s.getName()+" "+s.getCooldown()+" "+s.getHealth());
			
		}
	}
	
	public void readString(){
		String end = "";		
		try {
			end = read.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stringinput = end;
	}
	
	public void readInt(String s){
		int end = 0;
		
		while(true){
			try {
				System.out.println(s);
				end = Integer.parseInt(read.readLine());
			} catch (NumberFormatException e) {
				System.out.print(" No Number");
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		intinput = end;
	}
}
