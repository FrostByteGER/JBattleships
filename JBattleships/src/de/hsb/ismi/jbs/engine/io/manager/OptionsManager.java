/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.io.JBSParserException;
import de.hsb.ismi.jbs.engine.io.parser.OptionsParser;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsManager {
	
	private OptionsParser parser;
	private final String[] CATEGORIES = {"Graphics","Audio","Network"};
	private HashMap<String, String[]> data;
	private static final String SETTINGS_PATH = "Config/";
	private static final String SETTINGS_NAME = "Settings.cfg";

	/**
	 * @param path
	 */
	public OptionsManager() {
		parser = new OptionsParser();
		data = new HashMap<>();
	}
	
	/**
	 * 
	 * @return The parsed and sorted data. Null if error encountered.
	 */
	public HashMap<String, String[]> loadOptions(){
		ArrayList<String> raw = new ArrayList<>(0);
		ArrayList<String> currentData = new ArrayList<>(0);
		HashMap<String, String[]> data = new HashMap<>();
		String currentCategory = "";
		try {
			raw = parser.parseOptions(JBSCore.DATA_PATH + SETTINGS_PATH + SETTINGS_NAME);
			for(String s : raw){
				if(s.equals(CATEGORIES[0]) || s.equals(CATEGORIES[1]) || s.equals(CATEGORIES[2])){
					if(!currentCategory.equals(s) && !currentCategory.isEmpty()){
						data.put(currentCategory, currentData.toArray(new String[currentData.size()]));
						currentData = new ArrayList<>(0);
					}
					currentCategory = s;
				}else if(raw.get(raw.size() - 1).equals(s)){
					currentData.add(s);
					data.put(currentCategory, currentData.toArray(new String[currentData.size()]));
				}else{
					currentData.add(s);
				}
			}
		} catch (FileNotFoundException fnfe) {
			//JBSCore.msgLogger.addException(e);
			fnfe.printStackTrace();
			return null;
		} catch (IOException ioe) {
			//JBSCore.msgLogger.addException(e);
			ioe.printStackTrace();
			return null;
		} catch(JBSParserException jpe){
			
			jpe.printStackTrace();
			return null;
		}
		System.out.println("Parsing successfull!");
		this.data = data;
		return data;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean saveOptions(HashMap<String,String[]> data){
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getGraphicsData(){
		return data.get(CATEGORIES[0]);
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getAudioData(){
		return data.get(CATEGORIES[1]);
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getNetworkData(){
		return data.get(CATEGORIES[2]);
	}

}
