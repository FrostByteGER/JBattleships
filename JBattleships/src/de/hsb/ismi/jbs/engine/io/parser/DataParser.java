/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Abstract DataParser class for parsing files.
 * @author Kevin Kuegler
 * @version 1.00
 */
public abstract class DataParser {
	
	/**
	 * Default Constructor
	 */
	public DataParser(){
	}
	
	/**
	 * Parses a specified file and returns the content
	 * @param path The Filepath
	 * @return The read lines of the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> parseFile(String path) throws FileNotFoundException, IOException{
		ArrayList<String> lines = new ArrayList<String>(0);
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			String temp;
			
			while((temp = reader.readLine()) != null ){
				lines.add(temp);
			}
		}
		return lines;
	}
	
	/**
	 * Parses a specified file and returns the content as a character-array
	 * @param path The Filepath
	 * @return The read characters of the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<Character> parseFileByCharacters(String path) throws FileNotFoundException, IOException{
		ArrayList<Character> chars = new ArrayList<Character>(0);
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			int temp;
			
			while((temp = reader.read()) != -1 ){
				chars.add((char)temp);
			}
		}
		return chars;
	}
	
	/**
	 * Converts a Character-ArrayList to a primitive char-array
	 * @param c The Character-ArrayList
	 * @return The char-array
	 */
	public char[] CharacterArrayToCharArray(ArrayList<Character> c){
		char[] buffer = new char[c.size()];
		for(int i = 0; i < buffer.length;i++){
			buffer[i] = c.get(i);
		}
		return buffer;
	}
}
