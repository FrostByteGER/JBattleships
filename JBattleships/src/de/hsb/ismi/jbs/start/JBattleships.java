/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.awt.EventQueue;

import javax.swing.UIManager;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					//new UOIGUI();
					System.out.println("TEST OUTPUT"); // A Simple Testcomment
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

