/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.io.parser.OptionsParser;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsManager extends Manager {
	
	private OptionsParser parser;
	private final String[] CATEGORIES = {"Graphics","Audio","Network"};

	/**
	 * @param path
	 */
	public OptionsManager(String path) {
		super(path);
		parser = new OptionsParser();
	}
	
	public HashMap<String, String> loadOptions(){
		ArrayList<String> raw;
		try {
			raw = parser.parseOptions(this.getPath());
			for(String s : raw){
				//TODO: Add code
			}
		} catch (FileNotFoundException e) {
			JBSCore.msgLogger.addException(e);
			e.printStackTrace();
		} catch (IOException e) {
			JBSCore.msgLogger.addException(e);
			e.printStackTrace();
		}

		return null;
	}

}
