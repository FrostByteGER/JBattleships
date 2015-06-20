/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.network.game.client.GameClient;
import de.hsb.ismi.jbs.engine.network.game.server.GameServer;
import de.hsb.ismi.jbs.start.JBattleships;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class LobbyPanel extends JPanel {

	private JBSGUI parent;
	
	private JPanel header;
	
	private JPanel centerPanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	private ServerPanel serverPanel = new ServerPanel(true);
	
	private JBSGameType gameType;
	private ArrayList<JBSPlayer> players = new ArrayList<>(0);
	
	
	private JBSButtonGroup btnGroup;
	
	private final PreGamePlayerPanel[] playerPanels = new PreGamePlayerPanel[8];
	private JPanel rightPanel;
	
	private ChatPanel chatPanel = new ChatPanel();
	
	
	/**
	 * Create the chatPanel.
	 */
	public LobbyPanel(JBSGUI parent, JBSGameType type, boolean isHost) {
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		this.parent = parent;
		header = parent.generateHeader();

		gameType = type;
		
		add(header, BorderLayout.NORTH);
		
		btnGroup = new JBSButtonGroup();
		
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
		
		rightPanel = new JPanel();
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
			PreGamePlayerPanel p = new PreGamePlayerPanel(isHost , type);
			
			if(i == 0){
				//p.setName(JBattleships.game.getDataManager().getProfileManager().getActiveProfile().getName());
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
		
		
	}
	
	public void setUpdateTimer(long time, long interval){
		serverPanel.setUpdateTimer(time, interval);
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
			
			btnCancel.setText("Cancel");
			btnCancel.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				LobbyPanel.this.parent.restorePrevContainer();
			});
			add(btnCancel);
			
			btnStart.setText("Start Game");
			btnStart.setEnabled(true);
			btnStart.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				GameClient client = JBattleships.game.getGameClient();
				try {
					System.out.println(client.getGameServerListener().getLobbyData().toString());
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
			
			btnCancel.setText("Cancel");
			btnCancel.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				LobbyPanel.this.parent.restorePrevContainer();
			});
			add(btnCancel);
			
			btnReady.setText("Start Game");
			btnReady.setEnabled(false);
			btnReady.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);

			});
			add(btnReady);
		}
	}

}
