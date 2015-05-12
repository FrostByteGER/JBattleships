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
	
	//TODO: Add Game Category with Language Data and Debug Bool
	private OptionsParser parser;
	private HashMap<String, HashMap<String, String>> data;
	private static final String[] CATEGORIES = {"Graphics","Audio","Network","Game"};
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
	public boolean loadOptions(){
		ArrayList<String> raw = new ArrayList<>(0);
		ArrayList<String> currentData = new ArrayList<>(0);
		HashMap<String, HashMap<String, String>> data = new HashMap<>(0);
		String currentCategory = "";
		try {
			raw = parser.parseOptions(JBSCore.DATA_PATH + SETTINGS_PATH + SETTINGS_NAME);
			for(String s : raw){
				if(s.equals(CATEGORIES[0]) || s.equals(CATEGORIES[1]) || s.equals(CATEGORIES[2]) || s.equals(CATEGORIES[3])){
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
			//JBSCore.msgLogger.addException(e);
			fnfe.printStackTrace();
			return false;
		} catch (IOException ioe) {
			//JBSCore.msgLogger.addException(e);
			ioe.printStackTrace();
			return false;
		} catch(JBSParserException jpe){
			jpe.printStackTrace();
			return false;
		}
		System.out.println("Parsing successfull!");
		this.data = data;
		return true;
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
	
	/**
	 * 
	 * @return
	 */
	public HashMap<String, String> getNetworkData(){
		return data.get(CATEGORIES[2]);
	}

	/**
	 * @return the data
	 */
	public final HashMap<String, HashMap<String, String>> getData() {
		return data;
	}

}
