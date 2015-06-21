/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.network.chat.client.ChatClient;
import de.hsb.ismi.jbs.engine.network.game.client.GameClient;
import de.hsb.ismi.jbs.engine.network.game.client.GameClientListener;
import de.hsb.ismi.jbs.start.JBattleships;

import javax.swing.JLabel;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlineJoinPanel extends JPanel {

	private JBSGUI parent;
	
	private JPanel centerPanel;
	
	private JButton btnCancel;
	
	private JTextField ipField;
	private JLabel lblUsername;
	private JButton btnJoin;
	private JTextField usernameField;
	private JLabel lblServerIp;
	
	
	/**
	 * Create the panel.
	 */
	public OnlineJoinPanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();
	}
	
	private void initPanel() {
		
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		add(parent.generateHeader(), BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] {200};
		gbl_centerPanel.columnWeights = new double[]{0.0};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		centerPanel.setLayout(gbl_centerPanel);
						
		lblServerIp = new JLabel("Server IP:");
		GridBagConstraints gbc_lblServerIp = new GridBagConstraints();
		gbc_lblServerIp.insets = new Insets(0, 0, 5, 0);
		gbc_lblServerIp.gridx = 0;
		gbc_lblServerIp.gridy = 0;
		centerPanel.add(lblServerIp, gbc_lblServerIp);
		
		ipField = new JTextField("localhost");
		ipField.setHorizontalAlignment(JTextField.CENTER);
		GridBagConstraints gbc_ipField = new GridBagConstraints();
		gbc_ipField.insets = new Insets(0, 0, 5, 0);
		gbc_ipField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipField.gridx = 0;
		gbc_ipField.gridy = 1;
		centerPanel.add(ipField, gbc_ipField);
		ipField.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		centerPanel.add(lblUsername, gbc_lblUsername);

		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(JTextField.CENTER);
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 3;
		centerPanel.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);

		btnJoin = new JButton("Join");
		btnJoin.addActionListener(e -> {

			// IP
			String ipString = ipField.getText();
			if(ipString.isEmpty()){
				ipField.setBackground(Color.PINK);
				return;
			}
			InetAddress ip = null;
			try {
				 ip = InetAddress.getByName(ipString);
			} catch (Exception e1) {
				e1.printStackTrace();
				ipField.setBackground(Color.PINK);
				return;
			}
			ipField.setBackground(Color.WHITE);
			
			// Username
			String username = usernameField.getText();
			if(username.isEmpty()){
				usernameField.setBackground(Color.PINK);
				return;
			}
			usernameField.setBackground(Color.WHITE);
			
			// Get Connection Data
			int chatPort = JBattleships.game.getChatPort();
			int gamePort = JBattleships.game.getGamePort();
			int rlPort = JBattleships.game.getRoundListenerPort();
			int glPort = JBattleships.game.getGameListenerPort();
			int gslPort = JBattleships.game.getGameServerListenerPort();
			
			// Connect
			try {
				GameClient client = new GameClient(ip, username, gamePort, rlPort, glPort, gslPort);
				ChatClient chat = new ChatClient(ip, chatPort, username);
				JBattleships.game.setChatClient(chat);
				JBattleships.game.setGameClient(client);
				client.addMessageListener(new GameClientListener() {
					
					@Override
					public void messageSent(String message) {
						// Unused
					}
					@Override
					public void messageReceived(String message) {
						if(message.equals("/success")){
							LobbyPanel p = new LobbyPanel(parent, JBSGameType.GAME_ONLINE, false);
							p.setUpdateTimer(100L, 1000L);
							parent.swapContainer(p);
						}else if(message.equals("/duplicateusername")){
							new OnlineConnectionPanel(parent, centerPanel, "Duplicate Username!", true, true).setTimer(parent::restorePrevContainer, Boolean.TRUE, 2000L);
						}else if(message.equals("/ban")){
							new OnlineConnectionPanel(parent, centerPanel, "You have been banned!", true, true).setTimer(parent::restorePrevContainer, Boolean.TRUE, 2000L);
						}else if(message.equals("/end")){
							new OnlineConnectionPanel(parent, centerPanel, "Host has closed the lobby.", true, true).setTimer(parent::restorePrevContainer, Boolean.TRUE, 2000L);
						}else if(message.equals("/full")){
							new OnlineConnectionPanel(parent, centerPanel, "Lobby already full.", true, true).setTimer(parent::restorePrevContainer, Boolean.TRUE, 2000L);
						}
					}
					@Override
					public void connectionLost(String IP) {
						//new ConnectionPanel("Connection to server " + client.getServerIP().getHostAddress() +" lost.", true).setTimer(2000L);
					}
				});
				client.startClient();


			} catch (IOException ioe) {
				new OnlineConnectionPanel(parent, centerPanel, "Error joing Server", true, true).setTimer(parent::restorePrevContainer, Boolean.TRUE, 2000L);
			}
		});
		GridBagConstraints gbc_btnJoin = new GridBagConstraints();
		gbc_btnJoin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnJoin.insets = new Insets(0, 0, 5, 0);
		gbc_btnJoin.gridx = 0;
		gbc_btnJoin.gridy = 4;
		centerPanel.add(btnJoin, gbc_btnJoin);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 5;
		centerPanel.add(btnCancel, gbc_btnCancel);
	}

}
