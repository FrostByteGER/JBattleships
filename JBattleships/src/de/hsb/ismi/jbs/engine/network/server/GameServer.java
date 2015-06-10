/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.server;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameServer extends Thread {
	
	private int port = -1;

	public GameServer(){
		super("Game-Server");
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
}
