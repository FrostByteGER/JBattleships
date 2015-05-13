/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@Deprecated
public class Utility {

	private Utility() {
	}
	
	/**
	 * Taken from: http://stackoverflow.com/a/17967402
	 * @param arr
	 * @return
	 */
	public static <T> Object[] removeDuplicates(T[] arr) {
	   return new LinkedHashSet<T>(Arrays.asList(arr)).toArray();
	}

}
