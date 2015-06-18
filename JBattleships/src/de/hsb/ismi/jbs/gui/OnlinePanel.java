/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;





import java.awt.BorderLayout;





import javax.swing.JButton;





import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.network.game.client.GameClient;
import de.hsb.ismi.jbs.engine.network.game.server.GameServer;
import de.hsb.ismi.jbs.start.JBattleships;





import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlinePanel extends JPanel {
	
	private JBSGUI parent;
	
	private JPanel centerPanel;
	private JButton btnJoin;
	private JButton btnHost;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public OnlinePanel(JBSGUI parent) {
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
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
		centerPanel.setLayout(gbl_centerPanel);
		
		btnJoin = new JButton("Join Game");
		btnJoin.addActionListener(e -> {
			parent.swapContainer(new OnlineJoinPanel(parent));
		});
		GridBagConstraints gbc_btnJoin = new GridBagConstraints();
		gbc_btnJoin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnJoin.insets = new Insets(0, 0, 5, 0);
		gbc_btnJoin.gridx = 0;
		gbc_btnJoin.gridy = 0;
		centerPanel.add(btnJoin, gbc_btnJoin);
		
		btnHost = new JButton("Host Game");
		btnHost.addActionListener(e -> {
			JBSCoreGame game = JBattleships.game;
			GameServer server = game.generateGameServer();
			game.generateGame();
			server.startServer();
			try {
				//game.setGameClient(new GameClient("localhost", game.getGamePort(), game.getDataManager().getProfileManager().getActiveProfile().getName()));
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			}
			parent.swapContainer(new LobbyPanel(parent, JBSGameType.GAME_ONLINE, true));
		});
		GridBagConstraints gbc_btnHost = new GridBagConstraints();
		gbc_btnHost.insets = new Insets(0, 0, 5, 0);
		gbc_btnHost.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHost.gridx = 0;
		gbc_btnHost.gridy = 1;
		centerPanel.add(btnHost, gbc_btnHost);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		centerPanel.add(btnCancel, gbc_btnCancel);

	}

}
