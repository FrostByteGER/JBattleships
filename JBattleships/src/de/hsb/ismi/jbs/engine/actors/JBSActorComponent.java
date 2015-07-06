/**
 * 
 */
package de.hsb.ismi.jbs.engine.actors;

import java.awt.image.BufferedImage;

import de.hsb.ismi.jbs.engine.rendering.AnimationSequence;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSActorComponent {

	/** Contains the animations for this actor. */
	private AnimationSequence[] animations = null;
		
	private String imagePath = "";
	private JBSActor parent = null;
	
	private int imagecount;
	private int activeAnimationIndex = 0;
	private int imageamount;
	private int animationamount;
	
	
	public JBSActorComponent(String[] animationname) {
		
		this.animations = new AnimationSequence[animationname.length];
		this.animationamount = animationname.length;
		
		for(int i = 0 ; i < animationname.length ; i++){
			this.animations[i] = JBattleships.game.getDataManager().getResourceManager().getAnimationSequence(animationname[i]);
		}
		
		if(this.animations.length > 0){
			this.imageamount = this.animations[0].getSourceSprites().length;
		}
	}

	/**
	 * @param imagePath
	 * @param parent
	 */
	public JBSActorComponent(String imagePath, JBSActor parent) {
		super();
		this.imagePath = imagePath;
		this.parent = parent;
	}
	
	@Deprecated
	public void startAnimation(int animationnuber){
		//activeAnimationIndex = animationnuber%animationamount;
	}
	
	public void nextImage(){
		if(imagecount == imageamount-1){
			//TODO: Remove
			//activeAnimationIndex = 0;
			imagecount = 0;
		}else{
			imagecount++;
		}
	}
	
	public BufferedImage getImage(){
		return animations[activeAnimationIndex].getSourceSprites()[imagecount];
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
	 * @return the activeAnimationIndex
	 */
	public int getActivanimation() {
		return activeAnimationIndex;
	}

	/**
	 * @param activeAnimationIndex the activeAnimationIndex to set
	 */
	public void setActiveAnimationIndex(int index){
		if(index >= 0 && index < animationamount){
			this.activeAnimationIndex = index;
		}else{
			throw new IllegalArgumentException("Animationindex must be >= 0 and < animationcount");
		}
		
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
