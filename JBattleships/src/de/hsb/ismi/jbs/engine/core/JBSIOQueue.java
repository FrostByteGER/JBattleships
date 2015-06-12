/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Thread-safe UI-independant IO-Queue class.
 * @author Kevin Kuegler
 * @version 1.00
 * @param <T> Type of the IO-queue.
 * @see IOListener
 * @see ConcurrentLinkedQueue
 */
public class JBSIOQueue<T> {

	/** The input-queue. */
	private Queue<T> inputQueue = new ConcurrentLinkedQueue<T>();
	/** The output-queue. */
	private Queue<T> outputQueue = new ConcurrentLinkedQueue<T>();
	/** The {@link IOListener IOListener} list with their associated keyword.  */
	private Map<String, List<IOListener<T>>> listeners = new HashMap<String, List<IOListener<T>>>();
	
	/**
	 * Constructs and empty IO-queue.
	 */
	public JBSIOQueue(){
	}
	
	/**
	 * Constructs an IO-queue with a notifiertype and an array of listeners.
	 * @param notifierType The keyword the IOListeners are going to listen to.
	 * @param listeners The IOListener.
	 */
	public JBSIOQueue(String notifierType, ArrayList<IOListener<T>> listeners){
		this.listeners.put(notifierType, listeners);
	}
	
	/**
	 * Constructs an IO-queue with a notifiertype and a IOListener.
	 * @param notifierType The keyword the IOListeners are going to listen to.
	 * @param listener The IOListener.
	 */
	public JBSIOQueue(String notifierType, IOListener<T> listener){
		ArrayList<IOListener<T>> l = new ArrayList<>(0);
		l.add(listener);
		this.listeners.put(notifierType, l);
	}
	
	/**
	 * Adds the given IOListener with its key to the list.
	 * @param notifierType The keyword the IOListeners are going to listen to.
	 * @param listener The IOListener.
	 */
	public synchronized void addIOListener(String notifierType, IOListener<T> listener){
		if(listeners.containsKey(notifierType)){
			listeners.get(notifierType).add(listener);
		}else{
			ArrayList<IOListener<T>> l = new ArrayList<>(0);
			l.add(listener);
			listeners.put(notifierType, l);
		}
	}
	
	/**
	 * Adds the given IOListeners with its key to the list.
	 * @param notifierType The keyword the IOListeners are going to listen to.
	 * @param listeners The array of IOListeners.
	 */
	public synchronized void addIOListeners(String notifierType, ArrayList<IOListener<T>> listeners){
		if(this.listeners.containsKey(notifierType)){
			List<IOListener<T>> l = this.listeners.get(notifierType);
			for(IOListener<T> i : listeners){
				l.add(i);
			}
		}else{

			this.listeners.put(notifierType, listeners);
		}
	}
	
	/**
	 * Gets the first element in the inputqueue.
	 * @return <T> The first element in the inputqueue.
	 */
	public synchronized T getInput(){
		return inputQueue.poll();
	}
	
	/**
	 * Gets the first element in the outputqueue.
	 * @return <T> The first element in the outputqueue.
	 */
	public synchronized T getOutput(){
		return outputQueue.poll();
	}
	
	/**
	 * Adds the given Object to the inputqueue.
	 * @param <T> input to add to the inputqueue.
	 * @param notifierType The keyword the IOListeners will trigger to. Only Listeners with the associated keyword will trigger.
	 * @return True if element was added, false if something went wrong.
	 */
	public synchronized boolean insertInput(T input, String notifierType){
		boolean b = inputQueue.offer(input);
		notifyInputListeners(input, notifierType);
		return b;
	}
	
	/**
	 * Adds the given Object to the outputqueue.
	 * @param <T> input to add to the inputqueue.
	 * @param notifierType The keyword the IOListeners will trigger to. Only Listeners with the associated keyword will trigger.
	 * @return True if element was added, false if something went wrong.
	 */
	public synchronized boolean insertOutput(T output, String notifierType){
		boolean b = outputQueue.offer(output);
		notifyOutputListeners(output, notifierType);
		return b;
	}
	
	/**
	 * 
	 * @param <T> input
	 * @param notifierType
	 */
	private void notifyInputListeners(T input, String notifierType){
		List<IOListener<T>> l = this.listeners.get(notifierType);
		if (l != null) {
			for(IOListener<T> iol : l){
				iol.inputReceived(input, notifierType);
			}
		}
	}
	
	/**
	 * 
	 * @param <T> output
	 * @param notifierType
	 */
	private void notifyOutputListeners(T output, String notifierType){
		List<IOListener<T>> l = this.listeners.get(notifierType);
		if (l != null) {
			for(IOListener<T> iol : l){
				iol.outputReceived(output, notifierType);
			}
		}
	}

	
}
