/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.io.manager.OptionsManager;
import de.hsb.ismi.jbs.engine.io.parser.OptionsParser;

/**
 * This Class may be used to test anything you like.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class TestingClass {

	/**
	 * 
	 */
	public TestingClass() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		OptionsManager om = new OptionsManager("Data");
		
		/*
		OptionsParser op = new OptionsParser();
		ArrayList<String> data;
		try {
			data = op.parseOptions("Data/Config/settings.cfg");
			for(String s : data){
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
