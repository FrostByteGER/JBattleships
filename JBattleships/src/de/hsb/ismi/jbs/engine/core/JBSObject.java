/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.io.Serializable;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSObject implements Serializable{
	
	@Deprecated
	private boolean replicated = false;

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
