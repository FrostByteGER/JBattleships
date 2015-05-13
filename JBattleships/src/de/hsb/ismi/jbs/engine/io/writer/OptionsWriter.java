/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.writer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsWriter extends DataWriter {
	
	private static final String[] DELIMITERS = {"[","]"};
	private static final String ASSIGNMENT = "=";
	@Deprecated
	private static final String COMMENT = "//";
	@Deprecated
	private static final String[] CATEGORY_COMMENTS = {"Audio","Game"};

	/**
	 * 
	 */
	public OptionsWriter() {
	}
	
	/**
	 * TODO: Test this function!
	 * @param path
	 * @param data
	 * @param categories TODO: Change this to static access or something other
	 * @return
	 */
	public boolean writeOptions(String path, HashMap<String, String[]> data, String[] categories){
		ArrayList<String> raw = new ArrayList<>(0);
		try{
		for(String cat : categories){
			String[] catdata = data.get(cat);
			raw.add(DELIMITERS[0] + cat + DELIMITERS[1]);
			for(int i = 0; i < catdata.length; i+=2){
				raw.add(catdata[i] + " " + ASSIGNMENT + " " + catdata[i+1]);
			}
			raw.add(System.lineSeparator());
		}
		}catch(NullPointerException npe){
			npe.printStackTrace();
			return false;
		}
		return writeDataToFile(path, raw);
	}

}
