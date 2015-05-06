/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.parser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ResourceParser extends DataParser{
	
	private static final String COMMENT = "//";

	/**
	 * 
	 */
	public ResourceParser() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * Loads and returns the ResourceTable
	 * @param path
	 * @return The ResourceTable
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> parseResourceTable(String path) throws FileNotFoundException, IOException{
		ArrayList<String> parsedData = new ArrayList<String>(0);
		ArrayList<String> data = parseFile(path);
		for(String line : data){
			if(!line.startsWith(COMMENT) && !line.trim().isEmpty()){
				line = line.trim();
				parsedData.add(line);
			}
		}
		return parsedData;
	}
	
	public Clip parseAudioFile(File f) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream ais = AudioSystem.getAudioInputStream(f);
		Clip c = AudioSystem.getClip();
		c.open(ais);
		return c;
	}
	
	public BufferedImage parseImage(File f) throws IOException{
		BufferedImage bi = ImageIO.read(f);
		return bi;
	}

}
