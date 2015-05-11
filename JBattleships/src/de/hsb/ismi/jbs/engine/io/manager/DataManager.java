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
	 * @param optionsM the optionsM to set
	 */
	public final void setOptionsManager(OptionsManager optionsManager) {
		this.optionsM = optionsManager;
	}

	/**
	 * @return the configM
	 */
	public final ConfigManager getConfigManager() {
		return configM;
	}

	/**
	 * @param configM the configM to set
	 */
	public final void setConfigManager(ConfigManager configManager) {
		this.configM = configManager;
	}

	/**
	 * @return the localizationM
	 */
	public final LocalizationManager getLocalizationManager() {
		return localizationM;
	}

	/**
	 * @param localizationM the localizationM to set
	 */
	public final void setLocalizationManager(LocalizationManager localizationManager) {
		this.localizationM = localizationManager;
	}

	/**
	 * @return the resourceM
	 */
	public final ResourceManager getResourceManager() {
		return resourceM;
	}

	/**
	 * @param resourceM the resourceM to set
	 */
	public final void setResourceManager(ResourceManager resourceManager) {
		resourceM = resourceManager;
	}

	/**
	 * @return the profileM
	 */
	public final ProfileManager getProfileManager() {
		return profileM;
	}

	/**
	 * @param profileM the profileM to set
	 */
	public final void setProfileManager(ProfileManager profileManager) {
		this.profileM = profileManager;
	}
	
}
