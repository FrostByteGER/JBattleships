/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility.debug;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface DebugListener {

	
	public void addInfo(String info);
	
	public void addWarning(String warning);
	
	public void addError(Exception error);
}
