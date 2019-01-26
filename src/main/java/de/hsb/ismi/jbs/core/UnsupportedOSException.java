/**
 * 
 */
package de.hsb.ismi.jbs.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class UnsupportedOSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8447533186042528573L;

	/**
	 * 
	 */
	public UnsupportedOSException() {
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UnsupportedOSException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnsupportedOSException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UnsupportedOSException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnsupportedOSException(Throwable cause) {
		super(cause);
	}

}
