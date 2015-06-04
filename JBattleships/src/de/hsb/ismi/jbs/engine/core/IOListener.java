/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public interface IOListener<T> {

	/**
	 * 
	 * @param input
	 */
	public void InputReceived(T input);
	
	/**
	 * 
	 * @param output
	 */
	public void OutputReceived(T output);
	
	//public void InputRequested(T input);
	
	//public void OutputRequested(T output);
	
}
