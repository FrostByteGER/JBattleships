/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

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
	
	public AnimationSequence(BufferedImage[] sprites){
		this.sourceSprites = sprites;
		resizedSprites = sprites;
	}
	
	/**
	 * Resizes the animation to the given width and height.
	 * @param width The target width.
	 * @param height The target height.
	 */
	public void resizeAnimation(int width, int height){
		
		Graphics2D g;
		
		for(int i = 0; i < sourceSprites.length; i++){
			
			//resizedSprites[i] = (BufferedImage) sourceSprites[i].getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			resizedSprites[i] = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
			
			g = resizedSprites[i].createGraphics();
				
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
			g.drawImage(sourceSprites[i], 0, 0, width, height, null);
			g.dispose();
			
			
			/*
			resizeimages[i][j] = new BufferedImage(size, size, BufferedImage.TRANSLUCENT);
			
			g = resizeimages[i][j].createGraphics();
			
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
			g.drawImage(images[i][j], 0, 0, size, size, null);
			g.dispose();
			(*/
			
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
