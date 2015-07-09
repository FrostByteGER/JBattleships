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
 * The Resource Manager class. Holds all important assets.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ResourceManager{

	/** The resource-parser. */
	private ResourceParser parser = new ResourceParser();
	/** Content/Filenames of the resource-table   */
	private String[] resourceTable = null;
		
	private HashMap<String, Clip> audioFiles                  = new HashMap<String, Clip>(0);
	private HashMap<String, BufferedImage> textureFiles       = new HashMap<String, BufferedImage>(0);
	private HashMap<String, AnimationSequence> animationFiles = new HashMap<String, AnimationSequence>(0);
	
	/** Path of the audio-files. */
	private static final String AUDIO_PATH          = "Sfx";
	/** Path of the texture-files. */
	private static final String TEXTURE_PATH        = "Textures";
	/** Path of the animation-files. */
	private static final String ANIMATION_PATH      = "Animations";
	/** Path of the resource-table. */
	private static final String RESOURCE_TABLE_PATH = "Resources.cfg";
	/** The SHA256 value of the resource-table file. Prevents injection of unwanted files. */
	private static final String RESOURCE_TABLE_SHA  = "9f4c5334aa386736c00a1c2706ef483be007601b7e46519b66612aa3944989c3";
	
	/** Indicates no error. */
	public static final int SUCCESS                         = 0;
	/** Indicates that the resource-table was not found. */
	public static final int ERROR_RESOURCE_TABLE_NOT_FOUND  = 1;
	/** Indicates that the resource-table could not be loaded. */
	public static final int ERROR_READING_RESOURCE_TABLE    = 2;
	/** Indicates that the resource-table was modified by someone. */
	public static final int ERROR_RESOURCE_TABLE_MODIFIED   = 3;
	/** Indicates that the resource-table was not loaded. */
	public static final int ERROR_RESOURCE_TABLE_NOT_LOADED = 4;
	/** Indicates that the animationsequence could not be loaded. */
	public static final int ERROR_READING_ANIMATION         = 5;
	/** Indicates that the texture-file could not be loaded. */
	public static final int ERROR_READING_TEXTURE           = 6;
	/** Indicates that the audio-file could not be loaded. */
	public static final int ERROR_READING_AUDIO             = 7;
	/** Indicates that the loaded audio-file is not supported by the parser. */
	public static final int ERROR_UNSUPPORTED_AUDIO_TYPE    = 8;
	/** Indicates that the audio-line is not available. */
	public static final int ERROR_AUDIO_LINE_UNAVAILABLE    = 9;
	
	/**
	 * @param path
	 */
	public ResourceManager() {

	}
	
	/**
	 * Loads the resource-table.
	 * @return 0 if no error encountered. Greater 0 if an error occured.
	 */
	public int loadResourceTable(){
		File f = new File(JBSCoreGame.DATA_PATH + RESOURCE_TABLE_PATH);
		if(JBSCoreGame.shaGenerator.generateSHA256(f).equals(RESOURCE_TABLE_SHA) || JBSCoreGame.DEBUG_MODE){
			try {
				ArrayList<String> table = parser.parseResourceTable(JBSCoreGame.DATA_PATH + RESOURCE_TABLE_PATH);
				resourceTable = new String[table.size()];
				for(int i = 0;i < table.size();i++){
					resourceTable[i] = table.get(i);
				}
				return SUCCESS;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return ERROR_RESOURCE_TABLE_NOT_FOUND;
			} catch (IOException e) {
				e.printStackTrace();
				return ERROR_READING_RESOURCE_TABLE;
			}
		}else{
			return ERROR_RESOURCE_TABLE_MODIFIED;
		}
		

		
	}
	
	/**
	 * Loads all Resources specified by the ResourceTable.
	 * @return 0 if no error encountered. Greater 0 if an error occured.
	 */
	public boolean loadResources(){
		if(resourceTable.length == 0){
			return false;
		}
		boolean success = true;
		for(String s : resourceTable){
			File f = new File(s);
			if(s.contains(JBSCoreGame.DATA_PATH + TEXTURE_PATH + "/" + ANIMATION_PATH)){
				try {
					AnimationSequence as = parser.parseAnimation(f);
					animationFiles.put(f.getName(), as);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}else if(s.contains(JBSCoreGame.DATA_PATH + TEXTURE_PATH)){
								
				try {
					BufferedImage bi = parser.parseImage(f);
					textureFiles.put(f.getName(), bi);
				} catch (IOException e) {
					e.printStackTrace();
					success = false;
				}
			}else if(s.contains(JBSCoreGame.DATA_PATH + AUDIO_PATH)){
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
			}
		}
		
		return success;
	}
	
	/**
	 * Resizes all animations to the specified width and height. Uses bicubic interpolation.
	 * <br>Be careful, performance hungy depending on image-size and count!
	 * @param width New width of the animations.
	 * @param height New height of the animations.
	 */
	public void resizeAllAnimations(int width, int height){
		for(AnimationSequence animation : animationFiles.values()){
			animation.resizeAnimation(width, height, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		}
	}
	
	/**
	 * Resizes all animations to the specified width and height with a given interpolation-method.
	 * <br>Be careful, performance hungy depending on image-size, count and interpolation-method!
	 * @param width New width of the animations.
	 * @param height New height of the animations.
	 * @param method Interpolation method.
	 */
	public void resizeAllAnimations(int width, int height, RenderingHints method){
		for(AnimationSequence animation : animationFiles.values()){
			animation.resizeAnimation(width, height, method);
		}
	}
	
	
	/**
	 * @return the AnimationPath
	 */
	public final String getAnimationPath() {
		return ANIMATION_PATH;
	}
	
	/**
	 * 
	 * @return
	 */
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
