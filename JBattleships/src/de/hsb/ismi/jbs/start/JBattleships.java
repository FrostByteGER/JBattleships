/**
 * 
 */
package de.hsb.ismi.jbs.start;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.gui.utility.DebugFrame;

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
		// Sets the Position so the Debuglogger is a bit better visible
		DebugFrame d = new DebugFrame(true);
		d.setLocation(10, 10);
		DebugLog.setDebugFrame(d);
		
		game = new JBSCoreGame(true);
		game.initGameGUI();
	}
}

