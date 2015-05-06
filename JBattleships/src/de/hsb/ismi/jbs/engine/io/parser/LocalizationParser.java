/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class LocalizationParser extends DataParser{

	private static final String ASSIGNMENT = "=";
	private static final String COMMENT = ";";
	
	/**
	 * 
	 */
	public LocalizationParser() {
	}
	
	public void loadLanguage(String fileName ,HashMap<String, String> map){
		
		try {
			ArrayList<String> lines = parseFile(fileName);
			for(String line : lines){
				String[] temp = line.split(ASSIGNMENT);
				map.put(temp[0], temp[1]);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
