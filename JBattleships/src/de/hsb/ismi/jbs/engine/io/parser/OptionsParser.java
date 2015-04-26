/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.io.JBSParserException;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsParser extends DataParser{

	private final char[] DELIMITERS = {'[',']'};
	private final char ASSIGNMENT = '=';
	private final String COMMENT = "//";
	
	/**
	 * 
	 */
	public OptionsParser() {
	}
	
	/**
	 * 
	 * @param path
	 * @return The Array containing the parsed data. Null if error occured
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws JBSParserException 
	 */
	public ArrayList<String> parseOptions(String path) throws FileNotFoundException, IOException, JBSParserException{
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

	/**
	 * @return the delimiters
	 */
	public final char[] getDelimiters() {
		return DELIMITERS;
	}

	/**
	 * @return the assignmentoperator
	 */
	public final char getAssignmentOperator() {
		return ASSIGNMENT;
	}

	/**
	 * @return the commentoperator
	 */
	public final String getCommentOperator() {
		return COMMENT;
	}

}
