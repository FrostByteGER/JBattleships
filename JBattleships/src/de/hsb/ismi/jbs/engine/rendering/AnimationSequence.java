/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import de.hsb.ismi.jbs.engine.utility.Utility;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class AnimationSequence{
	
	@Deprecated
	public static final int BORDER_HORIZONTAL_THICK = 1;
	@Deprecated
	public static final int BORDER_VERTICAL_THICK = 1;
	public static final int SPRITE_WIDTH = 64;
	public static final int SPRITE_HEIGHT = 64;
	public static final int BACKGROUND_COLOR = 0xFF000000;
	
	private BufferedImage[] sourceSprites = null;
	private BufferedImage[] resizedSprites = null;

	/**
	 * 
	 */
	public AnimationSequence() {
	}
	
	/**
	 * 
	 * @param sprites
	 */
	public AnimationSequence(BufferedImage[] sprites){
		this.sourceSprites = sprites;
		resizedSprites = sprites;
	}
	
	/**
	 * Resizes the animation to the given width and height with simple Nearest-Neightbor resize-algorithm.
	 * @param width The target width.
	 * @param height The target height.
	 */
	public void resizeAnimation(int width, int height){
		
		Utility.resizeImages(sourceSprites, resizedSprites, width, height, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	}
	
	/**
	 * Resizes the animation to the given width and height.
	 * @see RenderingHints
	 * @param width The target width.
	 * @param height The target height.
	 * @param hint The Resize-Algorithm. 
	 */
	public void resizeAnimation(int width, int height, Object hint){
		Utility.resizeImages(sourceSprites, resizedSprites, width, height, hint);
	}

	/**
	 * @return the sourceSprites
	 */
	public final BufferedImage[] getSourceSprites() {
		return sourceSprites;
	}

	/**
	 * @return the resizedSprites
	 */
	public final BufferedImage[] getResizedSprites() {
		return resizedSprites;
	}

}
