/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class Utility {

	private Utility() {
	}
	
	/**
	 * Taken from: http://stackoverflow.com/a/17967402
	 * @param arr
	 * @return
	 */
	@Deprecated
	public static <T> Object[] removeDuplicates(T[] arr) {
	   return new LinkedHashSet<T>(Arrays.asList(arr)).toArray();
	}
	
	public static String stackTraceToString(Exception e){
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors, true));
		return errors.toString();
	}

}
