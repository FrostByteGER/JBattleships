/**
 * 
 */
package de.hsb.ismi.jbs.core;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.UIManager;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.engine.io.manager.DataManager;
import de.hsb.ismi.jbs.engine.io.manager.OptionsManager;
import de.hsb.ismi.jbs.engine.utility.Resolution;
import de.hsb.ismi.jbs.engine.utility.SHA256Generator;
import de.hsb.ismi.jbs.gui.JBSGUI;

/**
 * This is the core class with important constants and utility variables.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSCore {

	
	public static MessageLogger msgLogger;
	
	public static SHA256Generator shaGenerator;
	
	public static final String DATA_PATH = "Data/";
	/** Enables debug functionality and the MessageLogger */
	public static final boolean DEBUG = true;
	/** Allows to resize the game window */
	public static final boolean RESIZABLE = true;
	/** Game-Resolutions <br>TODO: Change to Dimension-class */
	public static final Resolution[] RESOLUTIONS = {new Resolution(800, 600),new Resolution(1024, 768),new Resolution(1280, 768)};
	
	private JBSGUI mainGUI;
	private DataManager dataManager;

	
	/**
	 * 
	 */
	public JBSCore() {
		shaGenerator = new SHA256Generator();
	}
	
	/**
	 * Initializes the game.
	 * @return
	 */
	public boolean initGame(){
		dataManager = new DataManager();
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
	 * Initializes the game-settings.
	 * @return
	 */
	public boolean initSettings(){
		OptionsManager om = dataManager.getOptionsManager();
		if(om.loadOptions() == null){
			return false;
		}
		HashMap<String, String> gfx = om.getGraphicsData();
		if(gfx.size() > 0){
			
		}else{
			return false;
		}
		
		HashMap<String, String> sfx = om.getAudioData();
		if(sfx.size() > 0){
			
		}else{
			return false;
		}
		
		HashMap<String, String> nt = om.getNetworkData();
		if(nt.size() > 0){
			
		}else{
			return false;
		}
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
