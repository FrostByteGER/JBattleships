/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSActorComponent extends JBSObject {


	private String imagePath;
	private JBSActor parent;
	private BufferedImage imagesorce;
	private BufferedImage[][]images;
	private BufferedImage[][] resizeimages;
	
	private int imagecount;
	private int activanimation;
	private int imageamount;
	private int animationamount;
	
	private int size = 64;

	
	
	/**
	 * 
	 */
	public JBSActorComponent(BufferedImage imagesorce) {
		this.imagesorce  = imagesorce;
		this.animationamount = imagesorce.getHeight()/size;
		this.imageamount = imagesorce.getWidth()/size;
		
		//imagesorce.getSubimage(imagecount*size,activanimation*size , size, size)
		
		images = new BufferedImage[animationamount][imageamount];
		resizeimages = new BufferedImage[animationamount][imageamount];
		
		for(int i = 0 ; i < animationamount ; i++){
			for(int j = 0 ; j < imageamount ; j++){
				images[i][j] = imagesorce.getSubimage(j*size,i*size , size, size);
				resizeimages[i][j] = imagesorce.getSubimage(j*size,i*size , size, size);
			}
		}		
	}

	/**
	 * @param replicated
	 */
	public JBSActorComponent(boolean replicated) {
		super(replicated);
		
	}

	/**
	 * @param imagePath
	 * @param parent
	 * @param destroyed
	 */
	public JBSActorComponent(String imagePath, JBSActor parent) {
		super();
		this.imagePath = imagePath;
		this.parent = parent;
	}
	
	public void startAnimation(int animationnuber){
		activanimation = animationnuber%animationamount;
	}
	
	public void resize(int size){
		
		Graphics2D g = null; 
		
		for(int i = 0 ; i < animationamount ; i++){
			for(int j = 0 ; j < imageamount ; j++){
				//resizeimages[i][j] = images[i][j];
				
				resizeimages[i][j] = new BufferedImage(size, size, BufferedImage.TRANSLUCENT);
				
				g = resizeimages[i][j].createGraphics();
				
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
				g.drawImage(images[i][j], 0, 0, size, size, null);
				g.dispose();
			}
		}
	}
	
	public void nextImage(){
		if(imagecount == imageamount-1){
			activanimation = 0;
			imagecount = 0;
		}else{
			imagecount++;
		}
	}
	
	public BufferedImage getImage(){
		
		return resizeimages[ activanimation][imagecount];
		
		//return imagesorce.getSubimage(imagecount*size,activanimation*size , size, size);
	}
	
	/**
	 * @return the parent
	 */
	public final JBSActor getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public final void setParent(JBSActor parent) {
		this.parent = parent;
	}

	/**
	 * @return the imagePath
	 */
	public final String getImagePath() {
		return imagePath;
	}

	/**
	 * @return the imagecount
	 */
	public int getImagecount() {
		return imagecount;
	}

	/**
	 * @param imagecount the imagecount to set
	 */
	public void setImagecount(int imagecount) {
		this.imagecount = imagecount%imageamount;
	}

	/**
	 * @return the activanimation
	 */
	public int getActivanimation() {
		return activanimation;
	}

	/**
	 * @param activanimation the activanimation to set
	 */
	public void setActivanimation(int activanimation) {
		this.activanimation = activanimation;
	}

	/**
	 * @return the imageamount
	 */
	public int getImageamount() {
		return imageamount;
	}

	/**
	 * @param imageamount the imageamount to set
	 */
	public void setImageamount(int imageamount) {
		this.imageamount = imageamount;
	}

	/**
	 * @return the animationamount
	 */
	public int getAnimationamount() {
		return animationamount;
	}

	/**
	 * @param animationamount the animationamount to set
	 */
	public void setAnimationamount(int animationamount) {
		this.animationamount = animationamount;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
