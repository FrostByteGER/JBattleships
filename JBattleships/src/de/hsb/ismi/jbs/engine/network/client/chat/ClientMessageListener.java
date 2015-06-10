package de.hsb.ismi.jbs.engine.network.client.chat;

public interface ClientMessageListener {
	
	public void messageReceived(String message);
	
	public void connectionLost(String IP);

}
