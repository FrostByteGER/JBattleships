/**
 * 
 */
package de.hsb.ismi.jbs.core;

import java.awt.EventQueue;

import javax.swing.UIManager;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.gui.JBSGUI;

/**
 * This is the core class with important constants and utility variables.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSCore {

	public static MessageLogger msgLogger;
	public static final String DATA_PATH = "/Data";
	/** Enables debug functionality and the MessageLogger */
	public static final boolean DEBUG = true;
	/** Allows to resize the game window */
	public static final boolean RESIZABLE = true;
	/** Game-Resolutions <br>TODO: Change to Dimension-class */
	public static final int[][] RESOLUTIONS = {{800, 600},{1024, 768},{1280, 720}};
	
	private JBSGUI mainGUI;

	
	/**
	 * 
	 */
	public JBSCore() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Initializes the game.
	 * @return
	 */
	public boolean initGame(){
		initResources();
		initProfiles();
		initConfigs();
		initLocs();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					mainGUI = new JBSGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return true;
	}
	
	/**
	 * Initializes the game-resources.
	 * @return
	 */
	public boolean initResources(){
		return true;
	}
	
	/**
	 * Initializes the game-profiles.
	 * @return
	 */
	public boolean initProfiles(){
		return true;
	}
	
	/**
	 * Initializes the game-configs.
	 * @return
	 */
	public boolean initConfigs(){
		return true;
	}
	
	/**
	 * Initializes the game-localizations.
	 * @return
	 */
	public boolean initLocs(){
		return true;
	}

	/**
	 * @return the mainGUI
	 */
	public final JBSGUI getMainGUI() {
		return mainGUI;
	}

}
