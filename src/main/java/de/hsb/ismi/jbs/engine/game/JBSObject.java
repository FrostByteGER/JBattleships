/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

/**
 * @author Kevin Kuegler
 * @version 1.00
 * @deprecated Useless as of 06.07.2015.
 */
@Deprecated
public class JBSObject{
	
	@Deprecated
	private boolean replicated = false;

	/**
	 * 
	 */
	public JBSObject() {
		// TODO Auto-generated constructor stub
	}
	
	@Deprecated
	public JBSObject(boolean replicated){
		this.replicated = replicated;
	}

	/**
	 * @return the replicated
	 */
	@Deprecated
	public final boolean isReplicated() {
		return replicated;
	}

	/**
	 * @param replicated the replicated to set
	 */
	@Deprecated
	public final void setReplicated(boolean replicated) {
		this.replicated = replicated;
	}

}
