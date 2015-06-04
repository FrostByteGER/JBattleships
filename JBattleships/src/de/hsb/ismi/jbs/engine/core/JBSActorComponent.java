/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSActorComponent extends JBSObject {

	private String imagePath = null;
	private JBSActor parent = null;
	private boolean destroyed = false;
	
	/**
	 * 
	 */
	public JBSActorComponent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param replicated
	 */
	public JBSActorComponent(boolean replicated) {
		super(replicated);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param imagePath
	 * @param parent
	 * @param destroyed
	 */
	public JBSActorComponent(String imagePath, JBSActor parent, boolean destroyed) {
		super();
		this.imagePath = imagePath;
		this.parent = parent;
		this.destroyed = destroyed;
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
	 * @return the destroyed
	 */
	public final boolean isDestroyed() {
		return destroyed;
	}

	/**
	 * @param destroyed the destroyed to set
	 */
	public final void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	/**
	 * @return the imagePath
	 */
	public final String getImagePath() {
		return imagePath;
	}

}
