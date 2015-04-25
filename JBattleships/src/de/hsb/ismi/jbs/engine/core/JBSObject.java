/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSObject {
	
	private boolean replicated;

	/**
	 * 
	 */
	public JBSObject() {
		// TODO Auto-generated constructor stub
	}
	
	public JBSObject(boolean replicated){
		this.replicated = replicated;
	}

	/**
	 * @return the replicated
	 */
	public final boolean isReplicated() {
		return replicated;
	}

	/**
	 * @param replicated the replicated to set
	 */
	public final void setReplicated(boolean replicated) {
		this.replicated = replicated;
	}

}
