/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import de.hsb.ismi.jbs.core.JBSCore;

/**
 * Writes given Data to a file.
 * @author Kevin Kuegler
 * @version 1.00
 */
public abstract class DataWriter {
	
	/**
	 * Writes the given data-array to a specified filepath
	 * @param path The filepath with name and extension.
	 * @param data The data that will be written.
	 * @return True if writing was successful, false if an error occured.
	 */
	public boolean writeDataToFile(String path, String[] data){
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			for(String s : data){
				writer.write(s);
			}
		} catch (IOException e) {
			JBSCore.msgLogger.addException(e);
			return false;
		}
		return true;
	}
	
	/**
	 * Writes the given data-ArrayList to a specified filepath
	 * @param path The filepath with name and extension.
	 * @param data The data that will be written.
	 * @return True if writing was successful, false if an error occured.
	 */
	public boolean writeDataToFile(String path, ArrayList<String> data){
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			for(String s : data){
				writer.write(s);
			}
		} catch (IOException e) {
			JBSCore.msgLogger.addException(e);
			return false;
		}
		return true;
	}

}
