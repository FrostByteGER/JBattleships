package de.hsb.ismi.jbs.engine.network.game.client;

public interface GameClientListener {
	
	public void messageReceived(String message);
	
	public void messageSent(String message);
	
	public void connectionLost(String IP);

}
