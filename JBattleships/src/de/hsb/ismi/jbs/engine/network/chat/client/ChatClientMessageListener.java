package de.hsb.ismi.jbs.engine.network.chat.client;

public interface ChatClientMessageListener {
	
	public void messageReceived(String message);
	
	public void messageSent(String message);
	
	public void connectionLost(String IP);

}
