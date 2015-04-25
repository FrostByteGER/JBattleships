/**
 * 
 */
package de.hsb.ismi.jbs.core;

import de.frostbyteger.messagelogger.MessageLogger;

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
	public static final boolean RESIZABLE = false;
	/** Game-Resolutions <br>TODO: Change to Dimension-class */
	public static final int[][] resolutions = {{800, 600},{1024, 768},{1280, 720}};

	
	/**
	 * 
	 */
	public JBSCore() {
		// TODO Auto-generated constructor stub
	}

}
