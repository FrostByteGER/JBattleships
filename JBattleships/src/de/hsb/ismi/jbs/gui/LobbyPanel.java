/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.network.game.LobbyInfo;
import de.hsb.ismi.jbs.engine.network.game.client.GameClient;
import de.hsb.ismi.jbs.engine.network.game.client.GameClientListener;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class LobbyPanel extends JPanel {

	private JBSGUI parent;
	
	private JPanel header;
	
	private JPanel centerPanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private ServerPanel serverPanel = new ServerPanel(true);
	private ChatPanel chatPanel = new ChatPanel();
	private JBSButtonGroup btnGroup= new JBSButtonGroup();
	private final LobbyPlayerSlotPanel[] playerPanels = new LobbyPlayerSlotPanel[8];
	
	private GameClient client = null;


	private JBSGameType gameType;
	private ArrayList<JBSPlayer> players = new ArrayList<>(0);
	

	private Timer updateTimer = new Timer(true);
	
	private boolean isHost = false;
	
	private boolean isReady = false;
	
	
	/**
	 * Create the chatPanel.
	 */
	public LobbyPanel(JBSGUI parent, JBSGameType type, boolean isHost) {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		this.parent = parent;
		header = parent.generateHeader();
		
		this.isHost = isHost;

		gameType = type;
		
		client = JBattleships.game.getGameClient();
		
		add(header, BorderLayout.NORTH);

		centerPanel.setOpaque(true);
		centerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		add(new AlphaContainer(centerPanel), BorderLayout.CENTER);
		
		if(isHost){
			add(new HostButtonPanel(), BorderLayout.SOUTH);
		}else{
			add(new ClientButtonPanel(), BorderLayout.SOUTH);
		}
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		serverPanel = new ServerPanel(isHost);
		centerPanel.add(new AlphaContainer(serverPanel));
		
		rightPanel.setOpaque(false);
		centerPanel.add(rightPanel);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[] {0};
		gbl_rightPanel.rowHeights = new int[] {0, 124};
		gbl_rightPanel.columnWeights = new double[]{1.0};
		gbl_rightPanel.rowWeights = new double[]{1.0, 1.0};
		rightPanel.setLayout(gbl_rightPanel);
		
		playerPanel.setOpaque(false);
		playerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		playerPanel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 22), null));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 0.7;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		rightPanel.add(new AlphaContainer(playerPanel), gbc);
		playerPanel.setLayout(new GridLayout(8, 1, 0, 0));
		
		GridBagConstraints gbc_chatPanel = new GridBagConstraints();
		gbc_chatPanel.weighty = 0.3;
		gbc_chatPanel.fill = GridBagConstraints.BOTH;
		gbc_chatPanel.gridx = 0;
		gbc_chatPanel.gridy = 1;
		chatPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		rightPanel.add(new AlphaContainer(chatPanel), gbc_chatPanel);
		
		for(int i  = 0; i < 8; i++){
			LobbyPlayerSlotPanel p = new LobbyPlayerSlotPanel(isHost , type);
			
			if(i == 0){
				p.setActiveSelected(true);
				p.setAISelected(false);
				p.getBtnKick().setEnabled(false);
				p.getCheckboxAI().setEnabled(false);
				p.getCheckboxActive().setEnabled(false);
			}
			p.setOpaque(false);
			btnGroup.add(p.getCheckboxActive());
			p.setActiveSelected(true);
			p.getNameField().setEditable(false);
			p.setPosition(i + 1);
			if(isHost){
				JBattleships.game.getGameServer().addConnectionListener((cst, position) -> {
					if(position == p.getPosition()){
						p.setName(cst.getUsername());
					}
					
				});
			}
			playerPanel.add(p);
			playerPanels[i] = p;
		}
		
		client.addMessageListener(new GameClientListener() {
			
			@Override
			public void messageSent(String message) {
				// Unused
			}
			
			@Override
			public void messageReceived(String message) {
				if(message.equals("/kick")){
					stopUpdateTimer();
					OnlineConnectionPanel p = new OnlineConnectionPanel(parent, LobbyPanel.this, 
							                  "You have been kicked.", 
							                  true, false);
					parent.swapContainer(p);
					p.setTimer(parent::restoreRootContainer, Boolean.TRUE, 4000L);
				}

			}
			
			@Override
			public void connectionLost(String IP) {
				stopUpdateTimer();
				OnlineConnectionPanel p = new OnlineConnectionPanel(parent, LobbyPanel.this, 
						                  "Connection to server " + IP + " lost.", 
						                  true, false);
				parent.swapContainer(p);
				p.setTimer(parent::restoreRootContainer, Boolean.TRUE, 4000L);
			}
		});
		
	}
	
	/**
	 * 
	 * @param time
	 * @param interval
	 */
	public void setUpdateTimer(long time, long interval){
		updateTimer = new Timer(true);
		updateTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					int index = 1;
					if(!isHost){
						index--;
					}
					LobbyInfo info = JBattleships.game.getGameClient().getGameServerListener().getLobbyData();
					
					// Server Panel
					serverPanel.getDestroyerCountLabel().setText(Integer.toString(info.getDestroyers()));
					serverPanel.getFrigateCountLabel().setText(Integer.toString(info.getFrigates()));
					serverPanel.getCorvetteCountLabel().setText(Integer.toString(info.getCorvettes()));
					serverPanel.getSubmarineCountLabel().setText(Integer.toString(info.getSubmarines()));
					serverPanel.getFieldSizeLabel().setText(Integer.toString(info.getFieldSize()));
					serverPanel.getPlayerCountLabel().setText(Integer.toString(info.getConnectedPlayers().length));
					serverPanel.getNavalMinesCheckbox().setSelected(info.useNavalMines());
					serverPanel.getCoastalArtilleryCheckbox().setSelected(info.useCoastalArtillery());
					serverPanel.calculateTotalShips();
					
					String[] names = new String[8];
					String[] playernames = info.getConnectedPlayers();
					// Transfer into a bigger array so unfilled values are null.
					for(int i = 0; i < playernames.length; i++){
						names[i] = playernames[i];
					}
					
					//Player Panel
					for(int j = index; j < playerPanels.length; j++){
						String s = names[j];
						// Null means, this slot is unoccupied by a player. So we set its name to nothing. The null is used to distinct.
						if(s == null && !playerPanels[j].isAISelected()){
							playerPanels[j].setName("");
							playerPanels[j].getBtnKick().setEnabled(false);
						}else{
							if(playerPanels[j].isActiveSelected() && !playerPanels[j].isAISelected()){
								playerPanels[j].setName(s);
								playerPanels[j].getBtnKick().setEnabled(true);
							}

						}
						
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}, time, interval);
	}
	
	/**
	 * Stops the timer and removes all pending tasks.
	 */
	public void stopUpdateTimer(){
		updateTimer.purge();
		updateTimer.cancel();
	}
	
	private class HostButtonPanel extends JPanel{
		
		private JButton btnCancel = new JButton();
		private JButton btnStart = new JButton();
		
		public HostButtonPanel(){
			initPanel();
		}
		
		private void initPanel(){
			setLayout(new FlowLayout(FlowLayout.CENTER));
			setOpaque(false);
			
			btnCancel.setText("Close Lobby");
			btnCancel.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				JBattleships.game.getGameServer().closeServer();
				client.closeClient();
				LobbyPanel.this.parent.restoreRootContainer(true);
			});
			add(btnCancel);
			
			btnStart.setText("Start Game");
			btnStart.setEnabled(true);
			btnStart.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				GameClient client = JBattleships.game.getGameClient();
				try {
					System.err.println(client.getGameServerListener().getLobbyData().toString());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				//client.getGameListener().createGame(gameType, players, fieldSize, shipCount);
			});
			add(btnStart);
		}
	}
	
	private class ClientButtonPanel extends JPanel{
		
		private JButton btnCancel = new JButton();
		private JButton btnReady = new JButton();
		
		public ClientButtonPanel(){
			initPanel();
		}
		
		private void initPanel(){
			setLayout(new FlowLayout(FlowLayout.CENTER));
			setOpaque(false);
			
			btnCancel.setText("Leave Lobby");
			btnCancel.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				JBattleships.game.getGameClient().closeClient();
				LobbyPanel.this.parent.restorePrevContainer();
			});
			add(btnCancel);
			
			btnReady.setText("Ready");
			btnReady.setEnabled(true);
			btnReady.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				
				try {
					client.getGameServerListener().setReady(client.getUsername(), !isReady);
					isReady = !isReady;
					if(!isReady){
						btnReady.setText("Ready");
					}else{
						btnReady.setText("Not Ready");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			add(btnReady);
		}
	}

}
