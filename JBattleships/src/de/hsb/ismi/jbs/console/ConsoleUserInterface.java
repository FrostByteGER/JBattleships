package de.hsb.ismi.jbs.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import de.hsb.ismi.jbs.engine.core.Game;

public class ConsoleUserInterface {
	
	private BufferedReader read;
	private Game game;
	private int input;
	
	public ConsoleUserInterface() {
		
		read = new BufferedReader( new InputStreamReader(System.in));

	}
	
	public void startGame(){
		
		System.out.println("Player amount");
		readInt("Player amount 2-6");
		
	}
	
	public String readString(){
		String end = "";		
		try {
			end = read.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return end;
	}
	
	public int readInt(String s){
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
		return end;
	}
	
	
}
