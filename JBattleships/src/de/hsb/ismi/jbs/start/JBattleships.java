/**
 * 
 */
package de.hsb.ismi.jbs.start;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.core.JBSCore;

/**
 * @author Kevin Kuegler
 * @version 1.00
 * 
 * Main entry point for the program.
 */
public class JBattleships {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JBSCore.msgLogger = new MessageLogger(JBSCore.DEBUG);
		// Sets the Position so the MessageLogger is a bit better visible
		JBSCore.msgLogger.getFrame().setLocation(10, 10);
		JBSCore game = new JBSCore();
		game.initGame();
	}
}

