/**
 * 
 */
package de.hsb.ismi.jbs.start;

import de.hsb.ismi.jbs.core.JBSCore;

/**
 * @author Kevin Kuegler
 * @version 1.00
 * 
 * Main entry point for the program.
 */
public class JBattleships {
	
	public static JBSCore game = new JBSCore(true);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Sets the Position so the MessageLogger is a bit better visible
		JBSCore.msgLogger.getFrame().setLocation(10, 10);
		game.initGUI();
	}
}

