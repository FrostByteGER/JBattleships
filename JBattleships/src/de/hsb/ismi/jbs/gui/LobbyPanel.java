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
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class LobbyPanel extends JPanel {

	private JBSGUI parent;
	
	private JPanel header;
	
	private JPanel centerPanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	
	/** The GameType of this Panel */
	private JBSGameType gameType;
	private ArrayList<JBSPlayer> players = new ArrayList<>(0);
	private ServerPanel serverPanel = new ServerPanel(true);
	
	private JBSButtonGroup btnGroup;
	
	private final PreGamePlayerPanel[] playerPanels = new PreGamePlayerPanel[8];
	
	
	/**
	 * Create the panel.
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
		
		playerPanel.setOpaque(false);
		playerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		playerPanel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 22), null));
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		serverPanel = new ServerPanel(isHost);
		centerPanel.add(new AlphaContainer(serverPanel));
		centerPanel.add(new AlphaContainer(playerPanel));
		playerPanel.setLayout(new GridLayout(8, 1, 0, 0));
		
		for(int i  = 0; i < 8; i++){
			PreGamePlayerPanel p = new PreGamePlayerPanel(isHost , type, "Player #" + (i + 1));
			
			if(i == 0){
				p.setName(JBattleships.game.getDataManager().getProfileManager().getActiveProfile().getName());
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
			playerPanel.add(p);
			playerPanels[i] = p;
		}
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
			btnStart.setEnabled(false);
			btnStart.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);

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