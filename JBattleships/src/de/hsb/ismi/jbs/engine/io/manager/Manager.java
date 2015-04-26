/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;


/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public abstract class Manager {
	
	private String path;

	/**
	 * 
	 */
	public Manager(String path) {
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public final String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public final void setPath(String path) {
		this.path = path;
	}

}
