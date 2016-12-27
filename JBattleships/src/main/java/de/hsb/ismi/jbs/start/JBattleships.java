/**
 * 
 */
package de.hsb.ismi.jbs.start;

import de.hsb.ismi.jbs.core.JBSCoreGame;

/**
 * Main entry point for the game.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBattleships {
	
	public static JBSCoreGame game;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initGame();
	}
	
	private static void initGame(){
		game = new JBSCoreGame(true);
	}
}

