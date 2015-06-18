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
	public void inputReceived(T input, String notifierType);
	
	/**
	 * 
	 * @param output
	 */
	public void outputReceived(T output, String notifierType);
	
	//public void InputRequested(T input);
	
	//public void OutputRequested(T output);
	
}
