/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.io.JBSParserException;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ConfigParser extends DataParser {
	
	public static final String[] CATEGORY_DELIMITERS = {"(",")"};
	public static final String[] DELIMITERS = {"{","}"};
	private static final String ASSIGNMENT = "=";
	private static final String COMMENT = ";";

	/**
	 * 
	 */
	public ConfigParser() {
	}
	
	public ArrayList<String> parseConfig(String path) throws FileNotFoundException, IOException, JBSParserException{
		ArrayList<String> parsedData = new ArrayList<>(0);
		ArrayList<String> data = parseFile(path);
		for(String line : data){
			line = line.replaceAll("\\s", "");
			line = line.trim();
			if(!line.startsWith(COMMENT) && !line.isEmpty()){
				if(line.startsWith(CATEGORY_DELIMITERS[0]) && line.endsWith(CATEGORY_DELIMITERS[1])){
					parsedData.add(line);
				}else if(!line.startsWith(DELIMITERS[0]) && !line.startsWith(DELIMITERS[1])){
					String[] splitted = line.split(ASSIGNMENT,2);
					if(splitted.length == 1){
						throw new JBSParserException("Parsing Config.cfg failed!");
					}
					parsedData.add(splitted[0]);
					parsedData.add(splitted[1]);					
				}
			}
		}
		return parsedData;
	}
}
