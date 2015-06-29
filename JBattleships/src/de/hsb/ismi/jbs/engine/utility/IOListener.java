/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

/**
 * @author Kevin Kuegler
 * @version 1.00
 * @deprecated Deprecated as of 25.06.2015
 */
@Deprecated
public interface IOListener<T> {

	/**
	 * 
	 * @param input
	 */
	@Deprecated
	public void inputReceived(T input, String notifierType);
	
	/**
	 * 
	 * @param output
	 */
	@Deprecated
	public void outputReceived(T output, String notifierType);
	
	//public void InputRequested(T input);
	
	//public void OutputRequested(T output);
	
}
