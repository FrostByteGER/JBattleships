/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ResourceManager{

	private HashMap<String, AudioInputStream> audioFiles;
	private HashMap<String, BufferedImage> imageFiles;
	
	private String audioPath;
	private String imagePath;
	
	/**
	 * @param path
	 */
	public ResourceManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the audioFiles
	 */
	public final HashMap<String, AudioInputStream> getAudioFiles() {
		return audioFiles;
	}
	
	/**
	 * @return the audioFile
	 */
	public final AudioInputStream getAudioFile(String key) {
		return audioFiles.get(key);
	}

	/**
	 * @return the audioPath
	 */
	public final String getAudioPath() {
		return audioPath;
	}

	/**
	 * @return the imageFiles
	 */
	public final HashMap<String, BufferedImage> getImageFiles() {
		return imageFiles;
	}
	
	/**
	 * @return the imageFile
	 */
	public final BufferedImage getImageFile(String key) {
		return imageFiles.get(key);
	}

	/**
	 * @return the imagePath
	 */
	public final String getImagePath() {
		return imagePath;
	}

}
