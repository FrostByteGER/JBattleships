/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.io.IOException;

import de.hsb.ismi.jbs.engine.network.client.chat.ChatClient;


/**
 * This Class may be used to test anything you like.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class TestingClassJan {

	/**
	 * 
	 */
	public TestingClassJan() {
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		try {
			ChatClient client = new ChatClient("192.168.43.75",36322, "Kevin");
			//client.sendMessage("rofl");
			
			//ChatClient client2 = new ChatClient("localhost",36322, "Kevin");
			//ChatClient client3 = new ChatClient("localhost",36322, "Jan");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
