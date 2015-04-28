/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.io.JBSParserException;
import de.hsb.ismi.jbs.engine.io.parser.ConfigParser;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ConfigManager {
	
	private ConfigParser parser;
	private HashMap<String, HashMap<String, String>> data;
	private static final String CONFIG_PATH = "Config/";
	private static final String CONFIG_NAME = "Config.cfg";

	/**
	 * 
	 */
	public ConfigManager() {
		this.parser = new ConfigParser();
		data = new HashMap<>();
	}
	
	/**
	 * 
	 * @return The parsed and sorted data. Null if error encountered.
	 */
	public HashMap<String, HashMap<String, String>> loadConfig(){
		try {
			ArrayList<String> parsedData = parser.parseConfig(JBSCore.DATA_PATH + CONFIG_PATH + CONFIG_NAME);
			
			for(String s : parsedData){
				//TODO: Add Code
			}
		} catch (IOException | JBSParserException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
