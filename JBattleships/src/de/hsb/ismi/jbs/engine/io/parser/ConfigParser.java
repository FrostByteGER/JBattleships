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
	
	private final char[] DELIMITERS = {'(',')'};
	private final char ASSIGNMENT = '=';
	private final String COMMENT = ";";

	/**
	 * 
	 */
	public ConfigParser() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<String> parseConfig(String path) throws FileNotFoundException, IOException, JBSParserException{
		
		ArrayList<String> parsedData = new ArrayList<>(0);
		ArrayList<String> data = parseFile(path);
			for(String line : data){
				line = line.replaceAll("\\s", "");
				if(line.startsWith(String.valueOf(DELIMITERS[0])) && line.endsWith(String.valueOf(DELIMITERS[1]))){
					String cat = line.substring(1, line.length()-1);
					parsedData.add(cat);
				}else if(!line.startsWith(COMMENT) && !line.trim().isEmpty()){
					String[] splitted = line.split(String.valueOf(ASSIGNMENT),2);
					if(splitted.length == 1){
						throw new JBSParserException("Parsing settings.cfg failed!");
					}
					parsedData.add(splitted[0]);
					parsedData.add(splitted[1]);					
				}
			}
		return parsedData;
	}

}
