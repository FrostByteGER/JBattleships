/**
 * 
 */
package de.hsb.ismi.jbs.core;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class IncorrectJavaVersionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6564761338372335121L;

	/**
	 * 
	 */
	public IncorrectJavaVersionException() {
	}

	/**
	 * @param message
	 */
	public IncorrectJavaVersionException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public IncorrectJavaVersionException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IncorrectJavaVersionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public IncorrectJavaVersionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
