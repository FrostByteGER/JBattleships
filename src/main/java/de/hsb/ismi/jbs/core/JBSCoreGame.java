/**
 * 
 */
package de.hsb.ismi.jbs.core;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamUser;
import com.codedisaster.steamworks.SteamUserCallback;
import com.codedisaster.steamworks.SteamAuth.AuthSessionResponse;
import com.codedisaster.steamworks.SteamFriends.PersonaChange;

import de.hsb.ismi.jbs.engine.game.managers.GameManager;
import de.hsb.ismi.jbs.engine.io.manager.DataManager;
import de.hsb.ismi.jbs.engine.io.manager.OptionsManager;
import de.hsb.ismi.jbs.engine.io.manager.ResourceManager;
import de.hsb.ismi.jbs.engine.players.JBSProfile;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ScreenDeviceManager;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;
import de.hsb.ismi.jbs.engine.utility.SHA256Generator;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.DebugFrame;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * This is the core class with important constants and utility variables.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSCoreGame {

	/** The Checksumgenerator to generate checksums from various objects, Strings or such. */
	public static SHA256Generator shaGenerator = new SHA256Generator();
	/** The ScreenDeviceManager that manages the screen devices a.k.a. monitors and its supported resolutions. */
	public static ScreenDeviceManager screenDeviceManager = new ScreenDeviceManager();
	/** Wether the game uses steam or not. */
	public static boolean USE_STEAM = true;
	/** Wether steam subsystem is currently running or not. */
	public static boolean STEAM_RUNNING = false;
	/** The Path of the Datafolder with all important content. */
	public static final String DATA_PATH = "Data/";
	/** Enables debug functionality and the MessageLogger. */
	public static boolean DEBUG_MODE = false;
	/** Enables Logging debug info to a log file. */
	public static boolean DEBUG_LOG_INFOS = false;
	/** Enables Logging debug warnings to a log file. */
	public static boolean DEBUG_LOG_WARNINGS = false;
	/** Enables Logging debug errors to a log file. */
	public static boolean DEBUG_LOG_ERRORS = true;
	/** Allows to resize the game window. */
	public static final boolean RESIZABLE = false;
	/** Minimum Java version to run the game. */
	public static final double MIN_JAVA_VERSION = 1.8;
	/** Supported operating systems of the game. */
	public static final String[] SUPPORTED_OS = {"Windows 7", "Windows 8", "Windows 8.1", "Windows 10"};
	/** */
	public static final String LOG_PATH = DATA_PATH + "Logs/";
	/** Contains the game's current resolution. */
	private Resolution currentResolution = new Resolution(800, 600);
	/** The game's current screenMode. */
	private ScreenMode screenMode = ScreenMode.MODE_FULLSCREEN;
	/** The game's current master-volume. */
	private int volume = 100;
	/** The game's current backgroundMusic-volume. */
	private int music = 100;
	/** The game's current language. */
	private String language = "German";
	/** The mainGUI of the game. */
	private JBSGUI mainGUI = null;
	/** The gameManager of the game. */
	private GameManager gameManager = null;
	/** The dataManager of the game. */
	private DataManager dataManager = new DataManager();
	/** DebugFrame reference, internal use only!*/
	private DebugFrame debugFrame = null;
	
	/**
	 * 
	 */
	public JBSCoreGame(boolean initGame) {
		
		debugFrame = new DebugFrame(false);
		debugFrame.setLocation(10, 10);
		DebugLog.setDebugFrame(debugFrame);
		
		DebugLog.setLogInfos(true);
		DebugLog.setLogWarnings(true);
		DebugLog.setLogErrors(true);
		
		//Pre Game Checks
		try {
			checkOS();
		} catch (UnsupportedOSException e) {
			DebugLog.logError(e);
			if(!DEBUG_MODE){
				exitGame();
			}
		}
		try {
			checkJavaVersion();
		} catch (IncorrectJavaVersionException e) {
			DebugLog.logError(e);
			if(!DEBUG_MODE){
				exitGame();
			}
		}
		if(USE_STEAM){
			try {
				STEAM_RUNNING = SteamAPI.init();
				if(!STEAM_RUNNING){
					DebugLog.logError("ERROR: SteamAPI could not be initialized, is the Steam Client running?");
					JOptionPane.showMessageDialog(null, "Fatal Error: SteamAPI could not be initialized, is the Steam Client running?","FATAL ERROR!", JOptionPane.ERROR_MESSAGE);
					exitGame();
				}else{
					DebugLog.logInfo("STEAM: SteamAPI successfully initialized!");
				}
			} catch (SteamException e) {
				DebugLog.logError(e);
				exitGame();
			}
		}
		
		if(initGame){
			initGame();
		}
	}
	
	/**
	 * Initializes the game.
	 * @return
	 */
	public boolean initGame(){
		initSteamSubsystem();
		
		initResources();
		boolean settings = initSettings();
		if(!settings){
			dataManager.getOptionsManager().generateDefaultOptions();
		}

		if(DEBUG_MODE){
			DebugLog.setLogInfos(true);
			DebugLog.setLogWarnings(DEBUG_MODE);
			DebugLog.setLogErrors(DEBUG_MODE);
			debugFrame.setVisible(true);
		}else{
			DebugLog.setDebugFrame(null);
			debugFrame = null;
		}
		
		boolean localization = initLocalization();
		boolean configs = initConfigs();
		
		
		if(localization && configs){

			boolean profiles = initProfiles();
			if(!profiles){
				DebugLog.logWarning("Profile " + dataManager.getOptionsManager().getGameData().get("activeProfile") + " not found! Loading default profile...");
				dataManager.getProfileManager().loadDefaultProfile();
			}
			
			
			initGameGUI();
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
					//TODO: Change to something else, to support multiple platforms.
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					mainGUI = new JBSGUI();
					mainGUI.initGUI(currentResolution, screenMode);
				} catch (Exception e) {
					DebugLog.logError(e);
				}
			}
		});
	}
	
	public void initSteamSubsystem(){
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(SteamAPI.isSteamRunning()){
					SteamAPI.runCallbacks();
				}
			}
		}, 0L, 66L);
		
		String steamUsername = new SteamFriends(new SteamFriendsCallback() {
			
			@Override
			public void onPersonaStateChange(SteamID steamID, PersonaChange change) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onGameOverlayActivated(boolean active) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onGameLobbyJoinRequested(SteamID steamIDLobby, SteamID steamIDFriend) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAvatarImageLoaded(SteamID steamID, int image, int width, int height) {
				// TODO Auto-generated method stub
				
			}
		}).getPersonaName();
		DebugLog.logInfo(steamUsername);
	}
	
	/**
	 * Initializes the game-resources.
	 * @return
	 */
	public void initResources(){
		ResourceManager rm = dataManager.getResourceManager();
		int code = rm.loadResourceTable();
		switch(code){
		case ResourceManager.SUCCESS:
			if(!rm.loadResources()){
				JOptionPane.showMessageDialog(null, "Fatal Error loading resources. Exiting game","FATAL ERROR!", JOptionPane.ERROR_MESSAGE);
				exitGame();
			}
			break;
		case ResourceManager.ERROR_RESOURCE_TABLE_NOT_FOUND:
			JOptionPane.showMessageDialog(null, "Fatal Error: Resource Table not found. Exiting game","FATAL ERROR!", JOptionPane.ERROR_MESSAGE);
			exitGame();
		case ResourceManager.ERROR_READING_RESOURCE_TABLE:
			JOptionPane.showMessageDialog(null, "Fatal Error reading Resource Table. Exiting game","FATAL ERROR!", JOptionPane.ERROR_MESSAGE);
			exitGame();
		case ResourceManager.ERROR_RESOURCE_TABLE_MODIFIED:
			JOptionPane.showMessageDialog(null, "Fatal Error: Resource Table modified. Exiting game","FATAL ERROR!", JOptionPane.ERROR_MESSAGE);
			exitGame();
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
					music = Integer.parseInt(sfx.get("backgroundMusic"));
				} catch (NumberFormatException nfe) {
					return false;
				}
			} else {
				return false;
			}
			
			HashMap<String, String> gt = om.getGameData();
			if (gt.size() > 0) {
				try {
					DEBUG_MODE = Boolean.parseBoolean(gt.get("debug-mode"));
					language = gt.get("language");
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
		String name = dataManager.getOptionsManager().getGameData().get("activeProfile");
		JBSProfile[] profiles = dataManager.getProfileManager().loadProfiles();
		boolean success = false;
		for(int i = 0; i < profiles.length; i++){
			JBSProfile p = profiles[i];
			if(p.getName().equalsIgnoreCase(name)){
				DebugLog.logInfo("Profile " + name + " was found!");
				dataManager.getProfileManager().setActiveProfile(i);
				success = true;
				break;
			}
		}
		return success;
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
	 * Checks the current Java version and exits the game if the version is below the minimum major java version.
	 * @see #MIN_JAVA_VERSION
	 */
	public void checkJavaVersion() throws IncorrectJavaVersionException{
		Double version = Double.parseDouble(System.getProperty("java.specification.version"));
		
		if(version < MIN_JAVA_VERSION) {
			JOptionPane.showMessageDialog(null, "Incorrect Java Version:" + " " + version + "\n" + "Version " + " " + MIN_JAVA_VERSION + " " + "or higher is required!", "Incorrect Java Version", JOptionPane.ERROR_MESSAGE);
			throw new IncorrectJavaVersionException("Java Version " + version + "is not supported. " + MIN_JAVA_VERSION + " or higher is required.");
		}
	}
	
	public void checkOS() throws UnsupportedOSException{
		String os = System.getProperty("os.name");
		for(String s : SUPPORTED_OS){
			if(s.equalsIgnoreCase(os)){
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "Unsupported Operating System:" + " " + os, "Unsupported Operating System", JOptionPane.ERROR_MESSAGE);
		throw new UnsupportedOSException(os + " is not supported.");
	}

	/**
	 * @return the mainGUI
	 */
	public final JBSGUI getMainGUI() {
		return mainGUI;
	}
	
	/**
	 * Writes the logs and exits the game.
	 */
	public static void exitGame(){
		if(USE_STEAM && STEAM_RUNNING){
			SteamAPI.shutdown();
		}
		try {
			if(DEBUG_LOG_INFOS){
				DebugLog.writeInfolog(null, true);
			}
			if(DEBUG_LOG_WARNINGS){
				DebugLog.writeWarninglog(null, true);
			}
			if(DEBUG_LOG_ERRORS){
				DebugLog.writeErrorlog(null, true);
			}
		} catch (IOException e) {
			DebugLog.logError(e);
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	/**
	 * Changes the game resolution
	 * @param r The new Resolution
	 */
	public final void changeResolution(Resolution r){
		currentResolution = r;
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
	
	public final String getLocalization(String key){
		return dataManager.getLocalizationManager().getLocalization(key);
	}

	/**
	 * @return the volume
	 */
	public final int getSoundVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public final void setSoundVolume(int volume) {
		this.volume = volume;
	}

	/**
	 * @return the backgroundMusic
	 */
	public final int getMusicVolume() {
		return music;
	}

	/**
	 * @param backgroundMusic the backgroundMusic to set
	 */
	public final void setMusicVolume(int music) {
		this.music = music;
	}

	/**
	 * @return the language
	 */
	public final String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public final void changeLanguage(String language) {
		dataManager.getLocalizationManager().loadLanguage(language);
		this.language = language;
	}
	
	/**
	 * 
	 * @return
	 */
	public final boolean saveOptions(){
		HashMap<String, String[]> data = new HashMap<String, String[]>();
		String[] cats = OptionsManager.CATEGORIES;
		data.put(cats[0], new String[]{"resX", Integer.toString(JBattleships.game.getCurrentResolution().getWidth()), 
									   "resY", Integer.toString(JBattleships.game.getCurrentResolution().getHeight()),
									   "mode", JBattleships.game.getScreenMode().toString()});
		data.put(cats[1], new String[]{"volume",Integer.toString(JBattleships.game.getSoundVolume()),
									   "backgroundMusic",Integer.toString(JBattleships.game.getMusicVolume())});
		data.put(cats[2], new String[]{"language", JBattleships.game.getLanguage(),
									   "activeProfile",JBattleships.game.getDataManager().getProfileManager().getActiveProfile().getName(),
									   "debug-mode", Boolean.toString(JBSCoreGame.DEBUG_MODE)});
		boolean check = JBattleships.game.getDataManager().getOptionsManager().saveOptions(data);
		if(check){
			DebugLog.logInfo("Saving options was successful.");
		}else{
			DebugLog.logWarning("Error saving options.cfg!");
		}
		return check;
	}

}
