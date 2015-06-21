/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.io.parser.ResourceParser;
import de.hsb.ismi.jbs.engine.rendering.AnimationSequence;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ResourceManager{

	private HashMap<String, Clip> audioFiles = new HashMap<String, Clip>(0);
	private HashMap<String, BufferedImage> textureFiles = new HashMap<String, BufferedImage>(0);
	private HashMap<String, AnimationSequence> animationFiles = new HashMap<String, AnimationSequence>(0);
	
	private final String AUDIO_PATH = "Data/Sfx";
	private final String TEXTURE_PATH = "Data/Textures";
	private final String ANIMATION_PATH = "Data/Textures/Animations";
	
	private ResourceParser parser = new ResourceParser();
	
	private String[] resourceTable = null;
	private static final String RESOURCE_TABLE_PATH = "Data/Resources.cfg";
	private static final String RESOURCE_TABLE_SHA = "PLACEHOLDER";
	
	/**
	 * @param path
	 */
	public ResourceManager() {

	}
	
	/**
	 * Initiates the ResourceTable
	 * @return
	 */
	public boolean initResourceTable(){
		File f = new File(RESOURCE_TABLE_PATH);
		if(f.exists()){
			//TODO: Remove True
			if(JBSCoreGame.shaGenerator.generateSHA256(f).equals(RESOURCE_TABLE_SHA) || true){
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
		boolean success = true;
		for(String s : resourceTable){
			File f = new File(s);
			if(s.contains(ANIMATION_PATH)){
				try {
					AnimationSequence as = parser.parseAnimation(f);
					animationFiles.put(f.getName(), as);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}else if(s.contains(TEXTURE_PATH)){
								
				try {
					BufferedImage bi = parser.parseImage(f);
					textureFiles.put(f.getName(), bi);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}else if(s.contains(AUDIO_PATH)){
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
				System.err.println("Ignored File: " + s);
			}
		}
		
		return success;
	}
	
	public void resizeAllAnimation(int width, int height){
		for(AnimationSequence animation : animationFiles.values()){
			animation.resizeAnimation(width, height, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		}
	}
	
	
	/**
	 * @return the AnimationPath
	 */
	public final String getAnimationPath() {
		return ANIMATION_PATH;
	}
	
	public final HashMap<String, AnimationSequence> getAnimationSequences(){
		return animationFiles;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public final AnimationSequence getAnimationSequence(String key){
		return animationFiles.get(key);
	}

	/**
	 * @return the resourceTable
	 */
	public final String[] getResourceTable() {
		return resourceTable;
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
		return AUDIO_PATH;
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
		return TEXTURE_PATH;
	}

	/**
	 * @return the parser
	 */
	public final ResourceParser getParser() {
		return parser;
	}

}
