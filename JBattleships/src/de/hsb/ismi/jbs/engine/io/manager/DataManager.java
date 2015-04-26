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

}
