/**
 * 
 */
package de.hsb.ismi.jbs.core;

import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.UIManager;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.engine.core.IOListener;
import de.hsb.ismi.jbs.engine.core.JBSIOQueue;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.engine.io.manager.DataManager;
import de.hsb.ismi.jbs.engine.io.manager.OptionsManager;
import de.hsb.ismi.jbs.engine.io.manager.ResourceManager;
import de.hsb.ismi.jbs.engine.network.server.chat.ChatServer;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ScreenDeviceManager;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;
import de.hsb.ismi.jbs.engine.utility.SHA256Generator;
import de.hsb.ismi.jbs.gui.JBSGUI;

/**
 * This is the core class with important constants and utility variables.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSCoreGame {

	/** The MessageLogger to log errors, exceptions and other stuff. */
	public static MessageLogger msgLogger = new MessageLogger(JBSCoreGame.DEBUG_MODE);
	/** The Checksumgenerator to generate checksums from various objects, Strings or such. */
	public static SHA256Generator shaGenerator = new SHA256Generator();
	/** The ScreenDeviceManager that manages the screen devices a.k.a. monitors and its supported resolutions. */
	public static ScreenDeviceManager screenDeviceManager = new ScreenDeviceManager();
	/** The IO-Queue for any IO events. */
	public static JBSIOQueue<String> ioQueue = new JBSIOQueue<String>();
	/** The Path of the Datafolder with all important content. */
	public static final String DATA_PATH = "Data/";
	/** TODO: JAVADOC */
	public static final String MSG_LOGGER_KEY = "Logger";
	/** Enables debug functionality and the MessageLogger. */
	public static final boolean DEBUG_MODE = true;
	/** Allows to resize the game window. */
	public static final boolean RESIZABLE = false;
	
	/** Contains the game's current resolution. */
	private Resolution currentResolution = new Resolution(800, 600);
	/** The game's current screenMode. */
	private ScreenMode screenMode = ScreenMode.MODE_FULLSCREEN;
	/** The game's current master-volume. */
	private int volume = 100;
	/** The game's current music-volume. */
	private int music = 100;
	/** The game's current IP-address. */
	private String ip = "0.0.0.0";
	/** The game's current gamePort. */
	private int gamePort = 15750;
	/** The game's current language. */
	private String language = "English";
	/** The ChatServers curent gamePort. */
	private int chatPort = 15751;
	
	/** The mainGUI of the game. */
	private JBSGUI mainGUI = null;
	/** The gameManager of the game. */
	private GameManager gameManager = null;
	/** The dataManager of the game. */
	private DataManager dataManager = new DataManager();
	
	/** The ChatServer of the game. */
	private ChatServer chatServer = null;
	
	/**
	 * 
	 */
	public JBSCoreGame(boolean initGame) {
		ioQueue.addIOListener("Logger", new IOListener<String>() {
			
			@Override
			public void outputReceived(String output, String notifierType) {	
				if(DEBUG_MODE){
					
				}
			}
			
			@Override
			public void inputReceived(String input, String notifierType) {
				if(DEBUG_MODE){
					msgLogger.addMessage(input);
				}
				
			}
		});
		if(initGame){
			initGame();
		}
	}
	
	/**
	 * Initializes the game.
	 * @return
	 */
	public boolean initGame(){
		if(initResources() && initSettings() && initLocalization() && initConfigs() && initProfiles()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Initializes the game-GUI.
	 */
	public void initGameGUI(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					mainGUI = new JBSGUI();
					mainGUI.initGUI(currentResolution, screenMode);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
					if (m.equalsIgnoreCase(ScreenMode.MODE_FULLSCREEN.toString())) {
						screenMode = ScreenMode.MODE_FULLSCREEN;
					} else if (m.equalsIgnoreCase(ScreenMode.MODE_BORDERLESS.toString())) {
						screenMode = ScreenMode.MODE_BORDERLESS;
					} else if (m.equalsIgnoreCase(ScreenMode.MODE_WINDOWED.toString())) {
						screenMode = ScreenMode.MODE_WINDOWED;
					} else {
						screenMode = ScreenMode.MODE_WINDOWED;
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
					gamePort = Integer.parseInt(nt.get("gamePort"));
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
		dataManager.getLocalizationManager().loadLanguageTable();
		return dataManager.getLocalizationManager().loadLanguage(language);
	}

	/**
	 * @return the mainGUI
	 */
	public final JBSGUI getMainGUI() {
		return mainGUI;
	}
	
	/**
	 * Changes the game resolution
	 * @param r The new Resolution
	 */
	public final void changeResolution(Resolution r){
		mainGUI.changeFrameSize(r);
	}
	
	/**
	 * Changes the screenMode of the game.
	 * @param sm The new ScreenMode
	 */
	public final void changeScreenMode(ScreenMode sm){
		mainGUI.changeScreenMode(sm);
		screenMode = sm;
	}

	/**
	 * @return the currentResolution
	 */
	public final Resolution getCurrentResolution() {
		return currentResolution;
	}

	/**
	 * @return the screenMode
	 */
	public final ScreenMode getScreenMode() {
		return screenMode;
	}

	/**
	 * @return the dataManager
	 */
	public final DataManager getDataManager() {
		return dataManager;
	}

	/**
	 * @return the gameManager
	 */
	public final GameManager getGameManager() {
		return gameManager;
	}

	/**
	 * Generates a new GameManager
	 */
	public final GameManager generateGame() {
		this.gameManager = new GameManager();
		return this.gameManager;
	}

	/**
	 * @return the chatServer
	 */
	public final ChatServer getChatServer() {
		return chatServer;
	}

	/**
	 * @param chatServer the chatServer to set
	 */
	public final ChatServer generateChatServer() {
		this.chatServer = new ChatServer(chatPort);
		return this.chatServer;
	}

}
