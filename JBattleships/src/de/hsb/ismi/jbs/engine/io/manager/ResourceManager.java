/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.io.parser.ResourceParser;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ResourceManager{

	private HashMap<String, Clip> audioFiles;
	private HashMap<String, BufferedImage> textureFiles;
	
	private String audioPath;
	private String texturePath;
	
	private ResourceParser parser;
	
	private String[] resourceTable;
	private final String RESOURCE_TABLE_PATH = "Data/Resources.cfg";
	private final String RESOURCE_TABLE_SHA = "AAAAA";
	
	/**
	 * @param path
	 */
	public ResourceManager() {
		parser = new ResourceParser();
		resourceTable = null;
		audioPath = "Data/Sfx";
		texturePath = "Data/Textures";
		audioFiles = new HashMap<String, Clip>(0);
		textureFiles = new HashMap<String, BufferedImage>(0);
	}
	
	/**
	 * Initiates the ResourceTable
	 * @return
	 */
	public boolean initResourceTable(){
		File f = new File(RESOURCE_TABLE_PATH);
		if(f.exists()){
			//JBSCore.shaGenerator.generateSHA256(f).equals(RESOURCE_TABLE_SHA);
			if(true){
				try {
					ArrayList<String> table = parser.parseResourceTable(RESOURCE_TABLE_PATH);
					resourceTable = new String[table.size()];
					for(int i = 0;i < table.size();i++){
						resourceTable[i] = table.get(i);
					}
					return true;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
	
	/**
	 * Loads all Resources specified by the ResourceTable
	 */
	public boolean loadResources(){
		boolean success = false;
		for(String s : resourceTable){
			File f = new File(s);
			if(s.contains(texturePath)){
				try {
					BufferedImage bi = parser.parseImage(f);
					textureFiles.put(f.getName(), bi);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}else if(s.contains(audioPath)){
				try {
					Clip c = parser.parseAudioFile(f);
					audioFiles.put(f.getName(), c);
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
					success = false;
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				} catch (LineUnavailableException e) {
					e.printStackTrace();
					success = false;
				}
			}else{
				System.out.println("Ignored File: " + s);
			}
		}
		return true;
	}

	/**
	 * @return the audioFiles
	 */
	public final HashMap<String, Clip> getAudioFiles() {
		return audioFiles;
	}
	
	/**
	 * @return the audioFile
	 */
	public final Clip getAudioFile(String key) {
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
	public final HashMap<String, BufferedImage> getTextures() {
		return textureFiles;
	}
	
	/**
	 * @return the imageFile
	 */
	public final BufferedImage getTexture(String key) {
		return textureFiles.get(key);
	}

	/**
	 * @return the imagePath
	 */
	public final String getTexturePath() {
		return texturePath;
	}

}
