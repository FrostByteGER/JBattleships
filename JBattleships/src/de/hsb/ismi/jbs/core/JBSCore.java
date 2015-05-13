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
import de.hsb.ismi.jbs.engine.io.manager.ResourceManager;
import de.hsb.ismi.jbs.engine.rendering.ResolutionMode;
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
	
	private Resolution currentResolution;
	private ResolutionMode resMode;
	private int volume;
	private int music;
	private String ip;
	private int port;
	private String language;
	
	private JBSGUI mainGUI;
	private DataManager dataManager;

	
	/**
	 * 
	 */
	public JBSCore() {
		shaGenerator = new SHA256Generator();
		dataManager = new DataManager();
		currentResolution = null;
		resMode = null;
		volume = 0;
		music = 0;
		ip = "0.0.0.0";
		port = 0;
		language = "english";
	}
	
	/**
	 * Initializes the game.
	 * @return
	 */
	public boolean initGame(){
		if(!initResources()){
			return false;
		}
		System.out.println(initProfiles());
		System.out.println(initSettings());
		System.out.println(initConfigs());
		System.out.println(initLocalization());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					mainGUI = new JBSGUI(currentResolution, resMode);
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
		ResourceManager rm = dataManager.getResourceManager();
		if(rm.initResourceTable()){
			if(rm.loadResources()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * Initializes the game-settings.
	 * @return
	 * @throws SettingsInvalidException 
	 */
	public boolean initSettings(){
		OptionsManager om = dataManager.getOptionsManager();
		if(!om.loadOptions()){
			return false;
		}else{
			HashMap<String, String> gfx = om.getGraphicsData();
			if(gfx.size() > 0){
				try {
					int x = Integer.parseInt(gfx.get("resX"));
					int y = Integer.parseInt(gfx.get("resY"));
					currentResolution = new Resolution(x, y);
					String m = gfx.get("mode");
					if (m.toLowerCase().equals(
							ResolutionMode.MODE_FULLSCREEN.toString().toLowerCase())) {
						resMode = ResolutionMode.MODE_FULLSCREEN;
					} else if (m.toLowerCase().equals(
							ResolutionMode.MODE_BORDERLESS.toString().toLowerCase())) {
						resMode = ResolutionMode.MODE_BORDERLESS;
					} else if (m.toLowerCase().equals(
							ResolutionMode.MODE_WINDOWED.toString().toLowerCase())) {
						resMode = ResolutionMode.MODE_WINDOWED;
					} else {
						resMode = ResolutionMode.MODE_WINDOWED;
					}

				} catch (NumberFormatException nfe) {
					return false;
				}
			} else {
				return false;
			}

			HashMap<String, String> sfx = om.getAudioData();
			if (sfx.size() > 0) {
				try {
					volume = Integer.parseInt(sfx.get("volume"));
					music = Integer.parseInt(sfx.get("music"));
				} catch (NumberFormatException nfe) {
					return false;
				}
			} else {
				return false;
			}

			HashMap<String, String> nt = om.getNetworkData();
			if (nt.size() > 0) {
				try {
					ip = nt.get("ip");
					port = Integer.parseInt(nt.get("port"));
				} catch (NumberFormatException nfe) {
					return false;
				}
			} else {
				return false;
			}
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
		return dataManager.getConfigManager().loadConfig();
	}
	
	/**
	 * Initializes the game-localizations.
	 * @return
	 */
	public boolean initLocalization(){
		return dataManager.getLocalizationManager().loadLanguage(language);
	}

	/**
	 * @return the mainGUI
	 */
	public final JBSGUI getMainGUI() {
		return mainGUI;
	}

}
