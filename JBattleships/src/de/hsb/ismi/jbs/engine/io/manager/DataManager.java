/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.sql.ResultSetMetaData;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class DataManager {
	
	private OptionsManager optionsM;
	private ConfigManager configM;
	private LocalizationManager localizationM;
	private ResourceManager ResourceM;
	
	/**
	 * 
	 */
	public DataManager() {
		optionsM = new OptionsManager();//TODO need Language Data
		configM = new ConfigManager();
		
		localizationM = new LocalizationManager("default"); // TODO
		ResourceM = new ResourceManager();
		
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
	
	public final LocalizationManager getLocalizationManager(){
		return localizationM;
	}
	
}
