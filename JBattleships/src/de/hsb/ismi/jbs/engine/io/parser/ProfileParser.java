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
public class ProfileParser extends DataParser{

	/**
	 * 
	 */
	public ProfileParser() {
	}
	
	public void loadProfile(String fileName ,HashMap<String, Float> map){
		
		try {
			ArrayList<String> lines = parseFile(fileName);
			for(String line : lines){
				String[] temp = line.split("=");
				map.put(temp[0], Float.valueOf(temp[1]));
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
