/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class DataManager {
	
	private OptionsManager optionsM;
	private ConfigManager configM;
	private LocalizationManager localizationM;
	private ResourceManager resourceM;
	private ProfileManager profileM;
	private GamePersistenceManager persistenceM = new GamePersistenceManager();
	
	/**
	 * 
	 */
	public DataManager() {
		resourceM = new ResourceManager();
		optionsM = new OptionsManager();//TODO need Language Data
		configM = new ConfigManager();
		localizationM = new LocalizationManager();
		profileM = new ProfileManager();
	}
	
	/**
	 * Initializes the managers. Returns false if something went wrong.
	 * @return
	 */
	@Deprecated
	public boolean initManagers(){
		localizationM.loadLanguage("english"); //TODO: Maybe use "default" and add String-check inside loadLanguage function
		return true;
	}
	
	/**
	 * Resets the managers safely. Might not work, returns false if so.
	 * @return 
	 */
	public boolean resetManagers(){
		return true;
	}

	/**
	 * @return the optionsM
	 */
	public final OptionsManager getOptionsManager() {
		return optionsM;
	}

	/**
	 * @return the configM
	 */
	public final ConfigManager getConfigManager() {
		return configM;
	}

	/**
	 * @return the localizationM
	 */
	public final LocalizationManager getLocalizationManager() {
		return localizationM;
	}

	/**
	 * @return the resourceM
	 */
	public final ResourceManager getResourceManager() {
		return resourceM;
	}


	/**
	 * @return the profileM
	 */
	public final ProfileManager getProfileManager() {
		return profileM;
	}

	/**
	 * @return the persistenceM
	 */
	public final GamePersistenceManager getPersistenceManager() {
		return persistenceM;
	}

	/**
	 * @param persistenceManager the persistenceManager to set
	 */
	public final void setPersistenceManager(GamePersistenceManager persistenceManager) {
		this.persistenceM = persistenceManager;
	}

}
