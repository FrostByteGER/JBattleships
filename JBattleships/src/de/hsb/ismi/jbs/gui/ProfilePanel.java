/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.engine.players.JBSProfile;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ProfilePanel extends JPanel {
	
	private JBSGUI parent;
	
	private JPanel centerPanel;
	private JPanel dataPanel;
	private JPanel buttonPanel;
	private JLabel lblNameDesc;
	private JLabel lblMatchesWonDesc;
	private JLabel lblMatchesLostDesc;
	private JLabel lblFlawlessWinsDesc;
	private JLabel lblFiredShotsDesc;
	private JLabel lblMissedShotsDesc;
	private JLabel lblShotsHitDesc;
	private JLabel lblShipsLostDesc;
	private JLabel lblShipsDestroyedDesc;
	private JLabel lblNavalMinesHitsDesc;
	private JLabel lblCoastalArtilleryHitsDesc;
	private JLabel lblName;
	private JLabel lblMatchesWon;
	private JLabel lblMatchesLost;
	private JLabel lblFlawlessWins;
	private JLabel lblNFiredShots;
	private JLabel lblMissedShots;
	private JLabel lblShotsHit;
	private JLabel lblShipsLost;
	private JLabel lblShipsDestroyed;
	private JLabel lblNavalMinesHit;
	private JLabel lblCoastalArtilleryHits;
	private JBSButton btnBack;
	private JBSButton btnDeleteProfile;
	private JBSButton btnNewProfile;
	private JBSButton btnSelectProfile;
	private JList<JBSProfile> list;

	/**
	 * Create the panel.
	 */
	public ProfilePanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();
	}
	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		add(parent.generateHeader(), BorderLayout.NORTH);
		
		setOpaque(false);
		centerPanel = new JPanel();
		centerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		centerPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_TITLE_PROFILES"), TitledBorder.CENTER, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		//centerPanel.setOpaque(false);
		add(new AlphaContainer(centerPanel), BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		dataPanel = new JPanel();
		dataPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_TITLE_PROFILE_INFORMATION"), TitledBorder.CENTER, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		dataPanel.setOpaque(false);
		centerPanel.add(dataPanel);
		GridBagLayout gbl_dataPanel = new GridBagLayout();
		gbl_dataPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_dataPanel.rowHeights = new int[]{0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_dataPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_dataPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		dataPanel.setLayout(gbl_dataPanel);
		
		lblNameDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILES_NAME"));
		lblNameDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNameDesc = new GridBagConstraints();
		gbc_lblNameDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameDesc.fill = GridBagConstraints.BOTH;
		gbc_lblNameDesc.gridx = 1;
		gbc_lblNameDesc.gridy = 1;
		dataPanel.add(lblNameDesc, gbc_lblNameDesc);
		
		lblName = new JLabel(JBattleships.game.getDataManager().getProfileManager().getActiveProfile().getName());
		lblName.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 1;
		dataPanel.add(lblName, gbc_lblName);
		
		lblMatchesWonDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_MATCHES_WON"));
		lblMatchesWonDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMatchesWonDesc = new GridBagConstraints();
		gbc_lblMatchesWonDesc.fill = GridBagConstraints.BOTH;
		gbc_lblMatchesWonDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchesWonDesc.gridx = 1;
		gbc_lblMatchesWonDesc.gridy = 3;
		dataPanel.add(lblMatchesWonDesc, gbc_lblMatchesWonDesc);
		
		lblMatchesWon = new JLabel("0");
		lblMatchesWon.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMatchesWon = new GridBagConstraints();
		gbc_lblMatchesWon.fill = GridBagConstraints.BOTH;
		gbc_lblMatchesWon.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchesWon.gridx = 2;
		gbc_lblMatchesWon.gridy = 3;
		dataPanel.add(lblMatchesWon, gbc_lblMatchesWon);
		
		lblMatchesLostDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_MATCHES_LOST"));
		lblMatchesLostDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMatchesLostDesc = new GridBagConstraints();
		gbc_lblMatchesLostDesc.fill = GridBagConstraints.BOTH;
		gbc_lblMatchesLostDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchesLostDesc.gridx = 1;
		gbc_lblMatchesLostDesc.gridy = 4;
		dataPanel.add(lblMatchesLostDesc, gbc_lblMatchesLostDesc);
		
		lblMatchesLost = new JLabel("0");
		lblMatchesLost.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMatchesLost = new GridBagConstraints();
		gbc_lblMatchesLost.fill = GridBagConstraints.BOTH;
		gbc_lblMatchesLost.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatchesLost.gridx = 2;
		gbc_lblMatchesLost.gridy = 4;
		dataPanel.add(lblMatchesLost, gbc_lblMatchesLost);
		
		lblFlawlessWinsDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_MATCHES_FLAWLESS"));
		lblFlawlessWinsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFlawlessWinsDesc = new GridBagConstraints();
		gbc_lblFlawlessWinsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblFlawlessWinsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlawlessWinsDesc.gridx = 1;
		gbc_lblFlawlessWinsDesc.gridy = 5;
		dataPanel.add(lblFlawlessWinsDesc, gbc_lblFlawlessWinsDesc);
		
		lblFlawlessWins = new JLabel("0");
		lblFlawlessWins.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFlawlessWins = new GridBagConstraints();
		gbc_lblFlawlessWins.fill = GridBagConstraints.BOTH;
		gbc_lblFlawlessWins.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlawlessWins.gridx = 2;
		gbc_lblFlawlessWins.gridy = 5;
		dataPanel.add(lblFlawlessWins, gbc_lblFlawlessWins);
		
		lblFiredShotsDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_FIRED_SHOTS"));
		lblFiredShotsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFiredShotsDesc = new GridBagConstraints();
		gbc_lblFiredShotsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblFiredShotsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiredShotsDesc.gridx = 1;
		gbc_lblFiredShotsDesc.gridy = 6;
		dataPanel.add(lblFiredShotsDesc, gbc_lblFiredShotsDesc);
		
		lblNFiredShots = new JLabel("0");
		lblNFiredShots.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNFiredShots = new GridBagConstraints();
		gbc_lblNFiredShots.fill = GridBagConstraints.BOTH;
		gbc_lblNFiredShots.insets = new Insets(0, 0, 5, 5);
		gbc_lblNFiredShots.gridx = 2;
		gbc_lblNFiredShots.gridy = 6;
		dataPanel.add(lblNFiredShots, gbc_lblNFiredShots);
		
		lblMissedShotsDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_MISSED_SHOTS"));
		lblMissedShotsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMissedShotsDesc = new GridBagConstraints();
		gbc_lblMissedShotsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblMissedShotsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblMissedShotsDesc.gridx = 1;
		gbc_lblMissedShotsDesc.gridy = 7;
		dataPanel.add(lblMissedShotsDesc, gbc_lblMissedShotsDesc);
		
		lblMissedShots = new JLabel("0");
		lblMissedShots.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMissedShots = new GridBagConstraints();
		gbc_lblMissedShots.fill = GridBagConstraints.BOTH;
		gbc_lblMissedShots.insets = new Insets(0, 0, 5, 5);
		gbc_lblMissedShots.gridx = 2;
		gbc_lblMissedShots.gridy = 7;
		dataPanel.add(lblMissedShots, gbc_lblMissedShots);
		
		lblShotsHitDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_SHOTS_HIT"));
		lblShotsHitDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShotsHitDesc = new GridBagConstraints();
		gbc_lblShotsHitDesc.fill = GridBagConstraints.BOTH;
		gbc_lblShotsHitDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblShotsHitDesc.gridx = 1;
		gbc_lblShotsHitDesc.gridy = 8;
		dataPanel.add(lblShotsHitDesc, gbc_lblShotsHitDesc);
		
		lblShotsHit = new JLabel("0");
		lblShotsHit.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShotsHit = new GridBagConstraints();
		gbc_lblShotsHit.fill = GridBagConstraints.BOTH;
		gbc_lblShotsHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblShotsHit.gridx = 2;
		gbc_lblShotsHit.gridy = 8;
		dataPanel.add(lblShotsHit, gbc_lblShotsHit);
		
		lblShipsLostDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_SHIPS_LOST"));
		lblShipsLostDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsLostDesc = new GridBagConstraints();
		gbc_lblShipsLostDesc.fill = GridBagConstraints.BOTH;
		gbc_lblShipsLostDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsLostDesc.gridx = 1;
		gbc_lblShipsLostDesc.gridy = 9;
		dataPanel.add(lblShipsLostDesc, gbc_lblShipsLostDesc);
		
		lblShipsLost = new JLabel("0");
		lblShipsLost.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsLost = new GridBagConstraints();
		gbc_lblShipsLost.fill = GridBagConstraints.BOTH;
		gbc_lblShipsLost.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsLost.gridx = 2;
		gbc_lblShipsLost.gridy = 9;
		dataPanel.add(lblShipsLost, gbc_lblShipsLost);
		
		lblShipsDestroyedDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_SHIPS_DESTROYED"));
		lblShipsDestroyedDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsDestroyedDesc = new GridBagConstraints();
		gbc_lblShipsDestroyedDesc.fill = GridBagConstraints.BOTH;
		gbc_lblShipsDestroyedDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsDestroyedDesc.gridx = 1;
		gbc_lblShipsDestroyedDesc.gridy = 10;
		dataPanel.add(lblShipsDestroyedDesc, gbc_lblShipsDestroyedDesc);
		
		lblShipsDestroyed = new JLabel("0");
		lblShipsDestroyed.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsDestroyed = new GridBagConstraints();
		gbc_lblShipsDestroyed.fill = GridBagConstraints.BOTH;
		gbc_lblShipsDestroyed.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsDestroyed.gridx = 2;
		gbc_lblShipsDestroyed.gridy = 10;
		dataPanel.add(lblShipsDestroyed, gbc_lblShipsDestroyed);
		
		lblNavalMinesHitsDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_NAVAL_MINES_HITS"));
		lblNavalMinesHitsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNavalMinesHitsDesc = new GridBagConstraints();
		gbc_lblNavalMinesHitsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblNavalMinesHitsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavalMinesHitsDesc.gridx = 1;
		gbc_lblNavalMinesHitsDesc.gridy = 11;
		dataPanel.add(lblNavalMinesHitsDesc, gbc_lblNavalMinesHitsDesc);
		
		lblNavalMinesHit = new JLabel("0");
		lblNavalMinesHit.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNavalMinesHit = new GridBagConstraints();
		gbc_lblNavalMinesHit.fill = GridBagConstraints.BOTH;
		gbc_lblNavalMinesHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavalMinesHit.gridx = 2;
		gbc_lblNavalMinesHit.gridy = 11;
		dataPanel.add(lblNavalMinesHit, gbc_lblNavalMinesHit);
		
		lblCoastalArtilleryHitsDesc = new JLabel(JBattleships.game.getLocalization("GAME_PROFILE_STATS_COASTAL_ARTILLERY_HITS"));
		lblCoastalArtilleryHitsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblCoastalArtilleryHitsDesc = new GridBagConstraints();
		gbc_lblCoastalArtilleryHitsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoastalArtilleryHitsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblCoastalArtilleryHitsDesc.gridx = 1;
		gbc_lblCoastalArtilleryHitsDesc.gridy = 12;
		dataPanel.add(lblCoastalArtilleryHitsDesc, gbc_lblCoastalArtilleryHitsDesc);
		
		lblCoastalArtilleryHits = new JLabel("0");
		lblCoastalArtilleryHits.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblCoastalArtilleryHits = new GridBagConstraints();
		gbc_lblCoastalArtilleryHits.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoastalArtilleryHits.fill = GridBagConstraints.BOTH;
		gbc_lblCoastalArtilleryHits.gridx = 2;
		gbc_lblCoastalArtilleryHits.gridy = 12;
		dataPanel.add(lblCoastalArtilleryHits, gbc_lblCoastalArtilleryHits);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		centerPanel.add(buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		btnSelectProfile = new JBSButton(JBattleships.game.getLocalization("GAME_PROFILES_SELECT"));
		btnSelectProfile.addActionListener(e -> {
			
		});
		
		list = new JList<JBSProfile>();
		list.setFont(JBSGUI.MAIN_FONT);
		list.setOpaque(false);
		list.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), JBattleships.game.getLocalization("GAME_TITLE_AVAILABLE_PROFILES"), TitledBorder.CENTER, TitledBorder.TOP, JBSGUI.MAIN_FONT, new Color(0, 0, 0)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 5;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		buttonPanel.add(scrollPane, gbc_list);
		GridBagConstraints gbc_btnSelectProfile = new GridBagConstraints();
		gbc_btnSelectProfile.fill = GridBagConstraints.BOTH;
		gbc_btnSelectProfile.gridx = 0;
		gbc_btnSelectProfile.gridy = 5;
		buttonPanel.add(new AlphaContainer(btnSelectProfile), gbc_btnSelectProfile);
		
		btnNewProfile = new JBSButton(JBattleships.game.getLocalization("GAME_PROFILES_NEW"));
		btnNewProfile.addActionListener(e -> {
		});
		GridBagConstraints gbc_btnNewProfile = new GridBagConstraints();
		gbc_btnNewProfile.fill = GridBagConstraints.BOTH;
		gbc_btnNewProfile.gridx = 0;
		gbc_btnNewProfile.gridy = 6;
		buttonPanel.add(new AlphaContainer(btnNewProfile), gbc_btnNewProfile);
		
		btnDeleteProfile = new JBSButton(JBattleships.game.getLocalization("GAME_PROFILES_DELETE"));
		btnDeleteProfile.addActionListener(e -> {
		});
		GridBagConstraints gbc_btnDeleteProfile = new GridBagConstraints();
		gbc_btnDeleteProfile.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteProfile.gridx = 0;
		gbc_btnDeleteProfile.gridy = 7;
		buttonPanel.add(new AlphaContainer(btnDeleteProfile), gbc_btnDeleteProfile);
		
		btnBack = new JBSButton(JBattleships.game.getLocalization("GAME_BACK"));
		btnBack.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 8;
		buttonPanel.add(new AlphaContainer(btnBack), gbc_btnBack);
	}

}
