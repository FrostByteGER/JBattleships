/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.GameStatistics;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.FlowLayout;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PostGamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6284937256945221371L;
	
	private JBSGUI parent;
	private JPanel buttonPanel;
	private JPanel centerPanel;
	private JTabbedPane playerStatsPane;
	
	private PlayerStatsPanel[] statsPanels;
	private JBSButton btnContinue;

	/**
	 * Create the panel.
	 */
	public PostGamePanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();
	}
	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		
		add(parent.generateHeader(), BorderLayout.NORTH);
		
		playerStatsPane = new JTabbedPane(JTabbedPane.TOP);
		playerStatsPane.setOpaque(false);
		
		Game game = JBattleships.game.getGameManager().getGame();
		statsPanels = new PlayerStatsPanel[game.getPlayers().length];
		
		// Sets the panels content with that from the last game.
		for(int i = 0; i < game.getPlayers().length; i++){
			JBSPlayer p = game.getPlayers()[i];
			GameStatistics gs = p.getStatistics();
			PlayerStatsPanel psp = new PlayerStatsPanel(parent);
			psp.setFiredShots(gs.getFiredShots());
			psp.setMissedShots(gs.getShotsMissed());
			psp.setShotsHit(gs.getShotsHit());
			psp.setShipsLost(gs.getShipsLost());
			psp.setShipsDestroyed(gs.getShipsDestroyed());
			psp.setNavalMinesHit(gs.getNavalMinesHit());
			psp.setNavalMinesUsed(gs.isNavalMinesUsed());
			psp.setCoastalArtilleryHit(gs.isCoastalArtilleryHit());
			psp.setCoastalArtilleryUsed(gs.isCoastalArtilleryUsed());
			psp.setFlawlessWin(gs.isFlawlessWin());
			statsPanels[i] = psp;
			playerStatsPane.addTab(p.getName(), psp);
			// TODO: Add stats here to active player profile.
		}
		
		buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		buttonPanel.setOpaque(false);
		add(buttonPanel, BorderLayout.SOUTH);
		
		btnContinue = new JBSButton(JBattleships.game.getLocalization("GAME_CONTINUE"));
		btnContinue.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		buttonPanel.add(btnContinue);
		
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_TITLE_POST_GAME"), TitledBorder.CENTER, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));

		centerPanel.add(playerStatsPane);
	}

}
