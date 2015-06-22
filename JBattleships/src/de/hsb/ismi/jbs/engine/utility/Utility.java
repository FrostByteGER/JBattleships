/**
 * 
 */
package de.hsb.ismi.jbs.engine.utility;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

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
	
	/**
	 * Counts all free indices of the specified array and returns the count. An index is counted as free if it is null.
	 * @param list
	 * @return
	 */
	public static <T> int countFreeIndices(List<T> list){
		int count = 0;
		for(T o : list){
			if(o == null){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Counts all free indices of the specified array and returns the count. An index is counted as free if it is null.
	 * @param list
	 * @return
	 */
	public static <T> int countOccupiedIndices(List<T> list){
		int count = 0;
		for(T o : list){
			if(o != null){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Counts and returns all free indices of the specified array. An index is counted as free if it is null.
	 * @param list
	 * @return
	 */
	public static <T> int[] getFreeIndices(List<T> list){
		int[] count = new int[countFreeIndices(list)];
		if(count.length > 0){
			int j = 0;
			for(int i = 0; i  < list.size(); i++){
				if(list.get(i) == null){
					count[j] = i;
					j++;
				}
			}
			return count;
		}else{
			return new int[0];
		}
	}
	
	/**
	 * 
	 * @param list
	 * @param object
	 * @return
	 */
	public static <T> boolean addToFirstFreeIndex(List<T> list, T object){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i) == null){
				list.set(i, object);
				return true;
			}
		}
		return false;
	}
	
	public static <T> List<T> fillList(List<T> list, T object, int size){
		for(int i = 0; i < size; i++){
			list.add(object);
		}
		return list;
	}
	
	public static <T> ArrayList<T> getNonNullElements(List<T> list){
		ArrayList<T> array = new ArrayList<>();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i) != null){
				array.add(list.get(i));
			}
		}
		return array;
	}


}
