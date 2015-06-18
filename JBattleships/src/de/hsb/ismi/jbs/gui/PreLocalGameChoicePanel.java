/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.io.manager.GamePersistenceManager;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreLocalGameChoicePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3302745837063989866L;

	private JBSGUI parent;
	
	private JPanel centerPanel;
	private JButton btnNewGame;
	private JButton btnLoadGame;
	private JButton btnCancel;
	
	/**
	 * Create the panel.
	 */
	public PreLocalGameChoicePanel(JBSGUI parent) {
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
		
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(e -> {
			parent.swapContainer(new PreGamePanel(parent, JBSGameType.GAME_LOCAL));
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridx = 0;
		gbc_btnNewGame.gridy = 0;
		centerPanel.add(btnNewGame, gbc_btnNewGame);
		
		btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(e -> {
			JBattleships.game.getDataManager().getPersistenceManager().loadGame(GamePersistenceManager.GAME_SAVE_PATH + "game_001.xml");
			//Dont create this after starting the game, causes the fireStartedGame Event NOT to be called!
			MainGamePanel gfc = new MainGamePanel(this.parent);
			JBattleships.game.getGameManager().startGame();
			parent.swapContainer(gfc);
		});
		GridBagConstraints gbc_btnLoadGame = new GridBagConstraints();
		gbc_btnLoadGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoadGame.gridx = 0;
		gbc_btnLoadGame.gridy = 1;
		centerPanel.add(btnLoadGame, gbc_btnLoadGame);
		
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
