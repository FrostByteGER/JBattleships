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

	/**
	 * 
	 */
	public DataManager() {
		optionsM = new OptionsManager();
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

}
