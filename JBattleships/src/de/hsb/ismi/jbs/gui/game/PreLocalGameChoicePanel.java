/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.game.JBSGameType;
import de.hsb.ismi.jbs.engine.io.manager.GamePersistenceManager;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
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
	private JBSButton btnNewGame;
	private JBSButton btnLoadGame;
	private JBSButton btnCancel;
	
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
		
		btnNewGame = new JBSButton(JBattleships.game.getLocalization("GAME_NEW"));
		btnNewGame.addActionListener(e -> {
			parent.swapContainer(new PreGamePanel(parent, JBSGameType.GAME_LOCAL));
		});
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridx = 0;
		gbc_btnNewGame.gridy = 0;
		centerPanel.add(new AlphaContainer(btnNewGame) , gbc_btnNewGame);
		
		btnLoadGame = new JBSButton(JBattleships.game.getLocalization("GAME_LOAD"));
		btnLoadGame.addActionListener(e -> {
			parent.swapContainer(new LoadGamePanel(parent));
		});
		GridBagConstraints gbc_btnLoadGame = new GridBagConstraints();
		gbc_btnLoadGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadGame.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoadGame.gridx = 0;
		gbc_btnLoadGame.gridy = 1;
		centerPanel.add(new AlphaContainer(btnLoadGame) , gbc_btnLoadGame);
		
		btnCancel = new JBSButton(JBattleships.game.getLocalization("GAME_CANCEL"));
		btnCancel.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 2;
		centerPanel.add(new AlphaContainer(btnCancel) , gbc_btnCancel);
	}
}
