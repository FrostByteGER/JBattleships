package de.hsb.ismi.jbs.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.io.manager.*;
import de.hsb.ismi.jbs.engine.core.*;
import de.hsb.ismi.jbs.engine.core.manager.PreGameManager;

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
		JBSPlayer winner;
		
		intinput = 0;
		
		do {
		cls();
		readIntMinMax("Player amount 2-6", 2, 6);
		
		playeramount = intinput;
		
		readIntMinMax("Fieldsize 8-16", 8, 16);
		
		fieldsize = intinput;
		
		cls();
		System.out.println("Fieldsze "+fieldsize);
		System.out.println("Playeramount "+playeramount);
		} while (!readStringYN("Are the information correct? y/n"));
		
		
		
		for(int i = 0 ; i < playeramount ; i++){
			pm.addPlayer(new JBSPlayer());
		}
		
		do {
			cls();
			System.out.println("Set Shipamount");
			System.out.println();
			readIntMinMax("Corvetteamount 0-3",0,3);
			pm.setCorvetteCount(intinput);
			readIntMinMax("Destrayeramount 0-3",0,3);
			pm.setDestroyerCount(intinput);
			readIntMinMax("Frigateamount 0-3",0,3);
			pm.setFrigateCount(intinput);
			readIntMinMax("Submarineamount 0-3",0,3);
			pm.setSubmarineCount(intinput);
			
			cls();
			
			System.out.println("Corvetteamount "+pm.getCorvetteCount());
			System.out.println("Destrayeramount "+pm.getDestroyerCount());
			System.out.println("Frigateamount "+pm.getFrigateCount());
			System.out.println("Submarineamount "+pm.getSubmarineCount());
			
		} while (!readStringYN("Are the information correct? y/n"));
		
		game = pm.createGame(JBSGameType.GAME_LOCAL, fieldsize);
		for(int i = 0 ; i < game.getPlayers().length ;i++){
			placeShips(i);
		}
		
		
		while(!game.isGameOver()){
			for(int i = 0 ; i < game.getPlayers().length && !game.isGameOver() ; i++){
				round(i);
			}
		}
				
		for(int i = 0 ; i < playeramount ; i++){
			if(game.getPlayers()[i].isAlive()){
				winner = game.getPlayers()[i];
				System.out.println("Player "+i+" Won!!!");
			}
		}
		
	}
	
	private void placeShips(int playernumber){
								
			JBSPlayer player = game.getPlayers()[playernumber];
			
			int x = 0;
			int y = 0;
			
			do {
			game.getGameField()[playernumber].resetField();
			for(JBSShip ship : player.getShips()){
				do {
					cls();
					System.out.println("Player "+playernumber);
					game.getGameField()[playernumber].printField(true);
					System.out.println("Shiptype: "+ship.getName());
					readIntMinMax("Ship x 0-"+(game.getGameField()[playernumber].getSize()-1),0,game.getGameField()[playernumber].getSize());
					x = intinput;
					readIntMinMax("Ship y 0-"+(game.getGameField()[playernumber].getSize()-1),0,game.getGameField()[playernumber].getSize());
					y = intinput;
					
					while(true){
						printDirections();
						System.out.println("Ship Direction (n,e,s,w) type: "+ship.getName());
						readString();
						switch (stringinput) {
						case "n":ship.setDirection(Direction.NORTH);break;
						case "e":ship.setDirection(Direction.EAST);break;
						case "s":ship.setDirection(Direction.SOUTH);break;
						case "w":ship.setDirection(Direction.WEST);break;						
						default:System.out.println("No Direction (n,e,s,w)");continue;
						}
						break;

					}
					
					ship.setPositon(x,y,ship.getDirection());
										
				} while (!game.getGameField()[playernumber].shipCanBePlaced(ship));
				game.getGameField()[playernumber].setShip(ship);
			}
			game.getGameField()[playernumber].printField(true);
			} while (!readStringYN("Are the information correct? y/n"));
		
	}
	
	private void round(int player){
		
		printShips(player);
		
		int shootat = 0;
		int xshoot = -1;
		int yshoot = -1;
		boolean candosomething = false;
		
		JBSShip ship;
		
		Direction direction;
		stringinput = "";
		
		for(JBSShip s : game.getPlayers()[player].getShips()){
			if(s.canShot()){
				candosomething = true;
			}else if(s.getCooldown() > 0){
				s.setCooldown(s.getCooldown()-1);
			}
		}
		
		if(!candosomething){
			cls();
			System.out.println("You can´t do anything in this Round Player "+player);
			readStringYN("continue y/n");
			return;
		}
		
		do{
			do {
				
				cls();
				
				System.out.println(player+" Players turn");
				
				for(int i = 0 ; i < game.getPlayers().length ; i++){
					if(game.getPlayers()[i].isAlive()&& i != player){
						System.out.println(i+" Player is Alive "+game.getPlayers()[i].isAlive());
					}
				}
				readIntMinMax("Shoot at", 0, game.getPlayers().length-1);
				
			} while (!game.getPlayers()[intinput].isAlive() || (intinput == player));
			
			if(!candosomething){
				break;
			}
			
			shootat = intinput;
			
			game.getGameField()[shootat].printField(false);
			
		}while(!readStringYN("Do you want to shoot him? y/n"));
		
		do{
		do {
			cls();
			printShips(player);
			readIntMinMax("Shoot with", 0, game.getPlayers()[player].getShips().size()-1);
		} while (!game.getPlayers()[player].getShips().get(intinput).isAlife() &&
				game.getPlayers()[player].getShips().get(intinput).getCooldown() > 0);
		
		ship = game.getPlayers()[player].getShips().get(intinput);
		
		cls();
		
		game.getGameField()[shootat].printField(false);
		readIntMinMax("shoot at X 0-"+(game.getGameField()[player].getSize()-1), 0, game.getGameField()[player].getSize()-1);
		xshoot = intinput;
		readIntMinMax("shoot at Y 0-"+(game.getGameField()[player].getSize()-1), 0, game.getGameField()[player].getSize()-1);
		yshoot = intinput;
		
		while(true){
			printDirections();
			System.out.println("Shoot in Direction (n,e,s,w) type: "+ship.getName());
			readString();
			switch (stringinput) {
			case "n":direction = Direction.NORTH;break;
			case "e":direction = Direction.EAST;break;
			case "s":direction = Direction.SOUTH;break;
			case "w":direction = Direction.WEST;break;						
			default:System.out.println("No Direction (n,e,s,w)");continue;
			}
			break;
		}
		cls();
		
		game.getGameField()[shootat].printField(false);
		printDirections();
		System.out.println(ship.getName());
		System.out.println("Health "+ship.getHealth()+"/"+ship.getLength());
		System.out.println("Maxcooldown "+ship.getCooldownLimit());
		System.out.println("Shoot at x:"+xshoot+" y:"+yshoot);
		System.out.print("Direction ");
		if(direction == Direction.NORTH){
			System.out.println("NORTH");
		}else if(direction == Direction.EAST){
			System.out.println("EAST");
		}else if(direction == Direction.SOUTH){
			System.out.println("SOUTH");
		}else if(direction == Direction.WEST){
			System.out.println("WEST");
		}
		
		}while(!readStringYN("Are the information correct? y/n"));
		ship.shot(xshoot, yshoot, direction, game.getGameField()[shootat]);
		ship.setCooldown(ship.getCooldownLimit());
	}
	
	private void printDirections(){
		System.out.println("  w");
		System.out.println("n + s");
		System.out.println("  e");
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
	
	private boolean readStringYN(String s){
		while(true){
			if(s.length() > 0){
				System.out.println(s);
			}
			readString();
			if(stringinput.equals("y")){
				return true;
			}else if(stringinput.equals("n")){
				return false;
			}
		}
	}
	
	private void cls(){
		for(int i = 0 ; i < 25 ; i++){
			System.out.println();
		}
	}
	
	private void readIntMinMax(String s, int min, int max){
		int end = min-1;
					
		do{
			try {
				System.out.println(s);
				end = Integer.parseInt(read.readLine());
			} catch (NumberFormatException e) {
				System.out.println("No Number");
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while ((end < min || end > max));
		intinput = end;
	}
	
	private void readInt(String s){
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
