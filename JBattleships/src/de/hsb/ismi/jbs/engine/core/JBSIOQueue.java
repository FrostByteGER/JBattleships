/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Thread-safe UI-independant IO-Queue class.
 * @author Kevin Kuegler
 * @version 1.00
 * @param <T> Type of the IO-queue
 */
public class JBSIOQueue<T> {

	private Queue<T> inputQueue = new ConcurrentLinkedQueue<T>();
	private Queue<T> outputQueue = new ConcurrentLinkedQueue<T>();
	
	private ArrayList<IOListener<T>> listeners = new ArrayList<>(0);
	
	public JBSIOQueue(){
	}
	
	/**
	 * 
	 * @param iol
	 */
	public JBSIOQueue(IOListener<T> iol){
		listeners.add(iol);
	}
	
	/**
	 * 
	 * @param iol
	 */
	public synchronized void addIOListener(IOListener<T> iol){
		listeners.add(iol);
	}
	
	/**
	 * Gets the first element in the inputqueue.
	 * @return The first String in the inputqueue.
	 */
	public T getInput(){
		return inputQueue.poll();
	}
	
	/**
	 * Gets the first element in the outputqueue.
	 * @return The first String in the outputqueue.
	 */
	public T getOutput(){
		return outputQueue.poll();
	}
	
	/**
	 * TODO: Check for synchronized, without, could lead to problems!
	 * Adds the given String to the inputqueue.
	 * @param s String to add to the inputqueue.
	 * @return True if element was added, false if something went wrong.
	 */
	public boolean insertInput(T input){
		boolean b = inputQueue.offer(input);
		notifyInputListeners(input);
		return b;
	}
	
	/**
	 * TODO: Check for synchronized, without, could lead to problems!
	 * Adds the given String to the outputqueue.
	 * @param s String to add to the outputqueue.
	 * @return True if element was added, false if something went wrong.
	 */
	public boolean insertOutput(T output){
		boolean b = outputQueue.offer(output);
		notifyOutputListeners(output);
		return b;
	}
	
	private void notifyInputListeners(T input){
		for(IOListener<T> iol : listeners){
			iol.InputReceived(input);
		}
	}
	
	private void notifyOutputListeners(T output){
		for(IOListener<T> iol : listeners){
			iol.OutputReceived(output);
		}
	}

	
}
