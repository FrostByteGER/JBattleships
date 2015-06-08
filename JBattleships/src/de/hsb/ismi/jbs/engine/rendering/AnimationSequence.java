/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class AnimationSequence {
	
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
	
	public AnimationSequence(BufferedImage[] sprites){
		this.sourceSprites = sprites;
		resizedSprites = new BufferedImage[sprites.length];
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 */
	public void resizeAnimation(int width, int height){
		for(int i = 0; i < sourceSprites.length; i++){
			resizedSprites[i] = (BufferedImage) sourceSprites[i].getScaledInstance(width, height, Image.SCALE_SMOOTH);
		}
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
