/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.io.JBSParserException;
import de.hsb.ismi.jbs.engine.io.parser.ConfigParser;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;

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
		data = new HashMap<>(0);
	}
	
	/**
	 * 
	 * @return The parsed and sorted data. Null if error encountered.
	 */
	public boolean loadConfig(){
		HashMap<String, HashMap<String, String>> data = new HashMap<>(0);
		try {
			ArrayList<String> raw = parser.parseConfig(JBSCoreGame.DATA_PATH + CONFIG_PATH + CONFIG_NAME);
			ArrayList<String> tempArray = null;
			String category = null;
			for(String s : raw){
				if(s.startsWith(ConfigParser.CATEGORY_DELIMITERS[0]) && s.endsWith(ConfigParser.CATEGORY_DELIMITERS[1])){
					if(category != null && !category.equals(s.substring(1, s.length()-1))){
						HashMap<String, String> temp = new HashMap<String, String>(0);
						for(int i = 0;i<tempArray.size();i+=2){
							temp.put(tempArray.get(i), tempArray.get(i+1));
						}
						data.put(category, temp);
					}
					category = s.substring(1, s.length()-1);
					tempArray = new ArrayList<>(0);
				}else{
					tempArray.add(s);
					if(raw.get(raw.size() - 1).equals(s)){
						HashMap<String, String> temp = new HashMap<String, String>(0);
						for(int i = 0;i<tempArray.size();i+=2){
							temp.put(tempArray.get(i), tempArray.get(i+1));
						}
						data.put(category, temp);
					}
				}
			}
		} catch (IOException | JBSParserException e) {
			DebugLog.logError(e);
			return false;
		}
		this.data = data;
		return true;
	}

	/**
	 * @return the data
	 */
	public final HashMap<String, HashMap<String, String>> getData() {
		return data;
	}

	/**
	 * @return the parser
	 */
	public final ConfigParser getParser() {
		return parser;
	}

}
