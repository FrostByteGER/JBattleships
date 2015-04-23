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

	private String[] ignoredSymbols;
	
	/**
	 * Default Constructor
	 */
	public DataParser(){
		ignoredSymbols = new String[0];
	}
	
	/**
	 * 
	 */
	public DataParser(String[] ignoredSymbols) {
		this.ignoredSymbols = ignoredSymbols;
	}
	
	/**
	 * Parses a specified file and returns the content
	 * TODO: Add ignoredSymbols to parsing
	 * @param path The Filepath
	 * @return The read lines of the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String[] parseFile(String path) throws FileNotFoundException, IOException{
		ArrayList<String> lines = new ArrayList<String>(0);
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			lines.add(reader.readLine());
		}
		return (String[]) lines.toArray();
	}
	
	/**
	 * TODO: Add ignoredSymbols to parsing
	 * Parses a specified file and returns the content as a character-array
	 * @param path The Filepath
	 * @return The read characters of the file
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Character[] parseFileByCharacters(String path) throws FileNotFoundException, IOException{
		ArrayList<Character> chars = new ArrayList<Character>(0);
		try(BufferedReader reader = new BufferedReader(new FileReader(path))){
			chars.add((char)reader.read());
		}
		return (Character[]) chars.toArray();
	}
	
	/**
	 * TODO: Add ignoredSymbols to parsing
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

	/**
	 * @return the ignoredSymbols
	 */
	public final String[] getIgnoredSymbols() {
		return ignoredSymbols;
	}

	/**
	 * @param ignoredSymbols the ignoredSymbols to set
	 */
	public final void setIgnoredSymbols(String[] ignoredSymbols) {
		this.ignoredSymbols = ignoredSymbols;
	}

}
