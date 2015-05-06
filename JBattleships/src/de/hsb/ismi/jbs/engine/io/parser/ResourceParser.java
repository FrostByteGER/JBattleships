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

	public ResourceParser() {
	}
	
	/**
	 * Parses a ResourceTable
	 * @param path The ResourceTable path
	 * @return The ResourceTable
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> parseResourceTable(String path) throws FileNotFoundException, IOException{
		ArrayList<String> parsedData = new ArrayList<String>(0);
		ArrayList<String> data = parseFile(path);
		for(String line : data){
			line = line.trim();
			if(!line.startsWith(COMMENT) && !line.isEmpty()){
				parsedData.add(line);
			}
		}
		return parsedData;
	}
	
	/**
	 * Parses an AudioFile
	 * @param f The AudioFile
	 * @return The parsed audiofile
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public Clip parseAudioFile(File f) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream ais = AudioSystem.getAudioInputStream(f);
		Clip c = AudioSystem.getClip();
		c.open(ais);
		return c;
	}
	
	/**
	 * Parses an AudioFile
	 * @param path The AudioPath
	 * @return The parsed audiofile
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 */
	public Clip parseAudioFile(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
		Clip c = AudioSystem.getClip();
		c.open(ais);
		return c;
	}
	
	/**
	 * Parses an Image
	 * @param f the ImageFile
	 * @return The parsed image
	 * @throws IOException
	 */
	public BufferedImage parseImage(File f) throws IOException{
		BufferedImage bi = ImageIO.read(f);
		return bi;
	}
	
	/**
	 * Parses an Image
	 * @param path The ImagePath
	 * @return The parsed image
	 * @throws IOException
	 */
	public BufferedImage parseImage(String path) throws IOException{
		BufferedImage bi = ImageIO.read(new File(path));
		return bi;
	}

}
