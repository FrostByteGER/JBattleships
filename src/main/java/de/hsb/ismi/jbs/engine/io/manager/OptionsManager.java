/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.io.JBSParserException;
import de.hsb.ismi.jbs.engine.io.parser.OptionsParser;
import de.hsb.ismi.jbs.engine.io.writer.OptionsWriter;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsManager {
	
	private OptionsParser parser;
	private OptionsWriter writer;
	private HashMap<String, HashMap<String, String>> data;
	public static final String[] CATEGORIES = {"Graphics","Audio","Game"};
	private static final String SETTINGS_PATH = "Config/";
	private static final String SETTINGS_NAME = "Settings.cfg";

	/**
	 * @param path
	 */
	public OptionsManager() {
		parser = new OptionsParser();
		writer = new OptionsWriter();
		data = new HashMap<>();
	}
	
	/**
	 * 
	 * @return The parsed and sorted data. Null if error encountered.
	 */
	public boolean loadOptions(){
		ArrayList<String> raw = new ArrayList<>(0);
		ArrayList<String> currentData = new ArrayList<>(0);
		HashMap<String, HashMap<String, String>> data = new HashMap<>(0);
		String currentCategory = "";
		try {
			raw = parser.parseOptions(JBSCoreGame.DATA_PATH + SETTINGS_PATH + SETTINGS_NAME);
			for(String s : raw){
				if(s.equals(CATEGORIES[0]) || s.equals(CATEGORIES[1]) || s.equals(CATEGORIES[2])){
					if(!currentCategory.equals(s) && !currentCategory.isEmpty()){
						HashMap<String, String> temp = new HashMap<String, String>(0);
						for(int i = 0;i<currentData.size();i+=2){
							temp.put(currentData.get(i), currentData.get(i+1));
						}
						data.put(currentCategory, temp);
						currentData = new ArrayList<>(0);
					}
					currentCategory = s;
				}else{
					currentData.add(s);
					if(raw.get(raw.size() - 1).equals(s)){
						HashMap<String, String> temp = new HashMap<String, String>(0);
						for(int i = 0;i<currentData.size();i+=2){
							temp.put(currentData.get(i), currentData.get(i+1));
						}
						data.put(currentCategory, temp);
					}
				}
			}
			//TODO: Combine catches?
		} catch (FileNotFoundException fnfe) {
			//JBSCoreGame.msgLogger.addException(e);
			DebugLog.logError(fnfe);
			return false;
		} catch (IOException ioe) {
			//JBSCoreGame.msgLogger.addException(e);
			DebugLog.logError(ioe);
			return false;
		} catch(JBSParserException jpe){
			DebugLog.logError(jpe);
			return false;
		}
		this.data = data;
		return true;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean saveOptions(HashMap<String,String[]> data){
		return writer.writeOptions(JBSCoreGame.DATA_PATH + SETTINGS_PATH + SETTINGS_NAME, data, CATEGORIES);
	}
	
	public void generateDefaultOptions(){
		//TODO:
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, String> getGraphicsData(){
		return data.get(CATEGORIES[0]);
	}
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, String> getAudioData(){
		return data.get(CATEGORIES[1]);
	}
	
	public HashMap<String, String> getGameData(){
		return data.get(CATEGORIES[2]);
	}

	/**
	 * @return the data
	 */
	public final HashMap<String, HashMap<String, String>> getData() {
		return data;
	}

}
