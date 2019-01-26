/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import de.hsb.ismi.jbs.engine.io.JBSParserException;


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
	
	public String[] parseLanguageTable(String path) throws FileNotFoundException, IOException{
		ArrayList<String> parsedData = new ArrayList<String>(0);
		ArrayList<String> data = parseFile(path);
		for(String line : data){
			line = line.trim();
			if(!line.startsWith(COMMENT) && !line.isEmpty()){
				parsedData.add(line);
			}
		}
		return parsedData.toArray(new String[parsedData.size()]);
	}
	
	public HashMap<String, String> loadLanguage(String fileName) throws FileNotFoundException, IOException, JBSParserException{
		HashMap<String, String> data = new HashMap<>();
		ArrayList<String> lines = parseFile(fileName);
		for (String line : lines) {
			if (!line.startsWith(COMMENT) && line.contains(ASSIGNMENT)) {
				String[] temp = line.split(ASSIGNMENT, 2);
				if (temp.length < 2) {
					throw new JBSParserException("Parsing settings.cfg failed!");
				}
				data.put(temp[0].trim(), temp[1].trim());
			}
		}
		return data;
	}
}
