/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.awt.EventQueue;

import javax.swing.UIManager;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.gui.JBSGUI;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					new JBSGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

