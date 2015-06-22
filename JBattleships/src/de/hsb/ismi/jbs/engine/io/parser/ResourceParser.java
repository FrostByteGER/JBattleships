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
import de.hsb.ismi.jbs.engine.rendering.AnimationSequence;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ResourceParser extends DataParser{
	
	private static final String COMMENT = ";";
	
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
	
	/**
	 * Parses a whole AnimationSequence
	 * @param path the AnimationPath
	 * @return The parsed Animation
	 * @throws IOException 
	 */
	public AnimationSequence parseAnimation(String path) throws IOException{
		
		int width = AnimationSequence.SPRITE_WIDTH;
		int height = AnimationSequence.SPRITE_HEIGHT;
		BufferedImage sourceSprite = parseImage(path);
		int imageWidth = sourceSprite.getWidth();
		int imageHeight = sourceSprite.getHeight();
		int columns = 0;
		int rows = 0;
		int lastRow = 0;
		columns = imageWidth / width;
		rows = imageHeight / height;
		
		// Checks last column for empty images to get last index.
		for(int i = 0; i < columns; i++){
			BufferedImage sub = sourceSprite.getSubimage(i * width, (rows - 1) * height, width, height);
			int[] pixels = new int[sub.getWidth() * sub.getHeight()];
			sub.getRGB(0, 0, sub.getWidth(), sub.getHeight(), pixels, 0, sub.getWidth());
			boolean alpha = true;
			for (int pixel : pixels){
				if ((pixel & AnimationSequence.BACKGROUND_COLOR) != 0){
					alpha = false;
					break;
				}
			}
			if(alpha){
				break;
			}
			lastRow = i;
		}
	
		ArrayList<BufferedImage> sprites = new ArrayList<>(columns * (rows - 1) + lastRow);
		
		// Adds the BufferedImages to the spriteArray
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if((i == rows && j <= lastRow) || i < rows){
					sprites.add(sourceSprite.getSubimage(j * width, i * height, width, height));
				}else{
					break;
				}
			}
		}
		return new AnimationSequence(sprites.toArray(new BufferedImage[sprites.size()]));
	}

		
	/**
	 * Parses a whole AnimationSequence
	 * @param path the AnimationPath
	 * @return The parsed Animation
	 * @throws IOException 
	 */
	public AnimationSequence parseAnimation(File f) throws IOException{
		int width = AnimationSequence.SPRITE_WIDTH;
		int height = AnimationSequence.SPRITE_HEIGHT;
		BufferedImage sourceSprite = parseImage(f);
		int imageWidth = sourceSprite.getWidth();
		int imageHeight = sourceSprite.getHeight();
		int columns = 0;
		int rows = 0;
		int lastRow = 0;
		columns = imageWidth / width;
		rows = imageHeight / height;
		
		// Checks last column for empty images to get last index.
		for(int i = 0; i < columns; i++){
			BufferedImage sub = sourceSprite.getSubimage(i * width, (rows - 1) * height, width, height);
			int[] pixels = new int[sub.getWidth() * sub.getHeight()];
			sub.getRGB(0, 0, sub.getWidth(), sub.getHeight(), pixels, 0, sub.getWidth());
			boolean alpha = true;
			for (int pixel : pixels){
				if ((pixel & AnimationSequence.BACKGROUND_COLOR) != 0){
					alpha = false;
					break;
				}
			}
			if(alpha){
				break;
			}
			lastRow = i;
		}
	
	ArrayList<BufferedImage> sprites = new ArrayList<>(columns * (rows - 1) + lastRow);
	
	// Adds the BufferedImages to the spriteArray
	for(int i = 0; i < rows; i++){
		for(int j = 0; j < columns; j++){
			if((i == rows && j <= lastRow) || i < rows){
				sprites.add(sourceSprite.getSubimage(j * width, i * height, width, height));
			}else{
				break;
			}
		}
	}
	return new AnimationSequence(sprites.toArray(new BufferedImage[sprites.size()]));
}

}
