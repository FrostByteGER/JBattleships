/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PlayerStatsPanel extends JPanel {
	
	private JBSGUI parent;
	private JLabel lblFiredShotsDesc;
	private JLabel lblMissedShotsDesc;
	private JLabel lblShotsHitDesc;
	private JLabel lblShipsLostDesc;
	private JLabel lblShipsDestroyedDesc;
	private JLabel lblFiredShots;
	private JLabel lblMissedShots;
	private JLabel lblShotsHit;
	private JLabel lblShipsLost;
	private JLabel lblShipsDestroyed;
	private JLabel lblNavalMinesHitDesc;
	private JLabel lblNavalMinesHit;
	private JLabel lblNavalMinesUsed;
	private JLabel lblCoastalArtilleryHit;
	private JLabel lblCoastalArtilleryUsed;
	private JLabel lblFlawlessWin;
	private JCheckBox chkbxCoastalArtilleryHit;
	private JCheckBox chkbxNavalMinesUsed;
	private JCheckBox chkbxFlawless;
	private JCheckBox chkbxCoastalArtilleryUsed;
	private JSeparator midSeparator;

	/**
	 * Create the panel.
	 */
	public PlayerStatsPanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();
	}
	private void initPanel() {
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 50, 0, 50, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblFiredShotsDesc = new JLabel(JBattleships.game.getLocalization("GAME_STATS_FIRED_SHOTS"));
		lblFiredShotsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFiredShotsDesc = new GridBagConstraints();
		gbc_lblFiredShotsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblFiredShotsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiredShotsDesc.gridx = 1;
		gbc_lblFiredShotsDesc.gridy = 1;
		add(lblFiredShotsDesc, gbc_lblFiredShotsDesc);
		
		lblFiredShots = new JLabel("0");
		lblFiredShots.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFiredShots = new GridBagConstraints();
		gbc_lblFiredShots.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFiredShots.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiredShots.gridx = 2;
		gbc_lblFiredShots.gridy = 1;
		add(lblFiredShots, gbc_lblFiredShots);
		
		midSeparator = new JSeparator();
		midSeparator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_midSeparator = new GridBagConstraints();
		gbc_midSeparator.fill = GridBagConstraints.BOTH;
		gbc_midSeparator.gridheight = 9;
		gbc_midSeparator.insets = new Insets(0, 0, 5, 5);
		gbc_midSeparator.gridx = 4;
		gbc_midSeparator.gridy = 1;
		add(midSeparator, gbc_midSeparator);
		
		lblNavalMinesHitDesc = new JLabel(JBattleships.game.getLocalization("GAME_STATS_NAVAL_MINES_HIT"));
		lblNavalMinesHitDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNavalMinesHitDesc = new GridBagConstraints();
		gbc_lblNavalMinesHitDesc.fill = GridBagConstraints.BOTH;
		gbc_lblNavalMinesHitDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavalMinesHitDesc.gridx = 6;
		gbc_lblNavalMinesHitDesc.gridy = 1;
		add(lblNavalMinesHitDesc, gbc_lblNavalMinesHitDesc);
		
		lblNavalMinesHit = new JLabel("0");
		lblNavalMinesHit.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNavalMinesHit = new GridBagConstraints();
		gbc_lblNavalMinesHit.fill = GridBagConstraints.BOTH;
		gbc_lblNavalMinesHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavalMinesHit.gridx = 7;
		gbc_lblNavalMinesHit.gridy = 1;
		add(lblNavalMinesHit, gbc_lblNavalMinesHit);
		
		lblMissedShotsDesc = new JLabel(JBattleships.game.getLocalization("GAME_STATS_MISSED_SHOTS"));
		lblMissedShotsDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMissedShotsDesc = new GridBagConstraints();
		gbc_lblMissedShotsDesc.fill = GridBagConstraints.BOTH;
		gbc_lblMissedShotsDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblMissedShotsDesc.gridx = 1;
		gbc_lblMissedShotsDesc.gridy = 3;
		add(lblMissedShotsDesc, gbc_lblMissedShotsDesc);
		
		lblMissedShots = new JLabel("0");
		lblMissedShots.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMissedShots = new GridBagConstraints();
		gbc_lblMissedShots.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMissedShots.insets = new Insets(0, 0, 5, 5);
		gbc_lblMissedShots.gridx = 2;
		gbc_lblMissedShots.gridy = 3;
		add(lblMissedShots, gbc_lblMissedShots);
		
		lblNavalMinesUsed = new JLabel(JBattleships.game.getLocalization("GAME_STATS_NAVAL_MINES USED"));
		lblNavalMinesUsed.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblNavalMinesUsed = new GridBagConstraints();
		gbc_lblNavalMinesUsed.fill = GridBagConstraints.BOTH;
		gbc_lblNavalMinesUsed.insets = new Insets(0, 0, 5, 5);
		gbc_lblNavalMinesUsed.gridx = 6;
		gbc_lblNavalMinesUsed.gridy = 3;
		add(lblNavalMinesUsed, gbc_lblNavalMinesUsed);
		
		chkbxNavalMinesUsed = new JCheckBox("");
		chkbxNavalMinesUsed.setFont(JBSGUI.MAIN_FONT);
		chkbxNavalMinesUsed.setOpaque(false);
		chkbxNavalMinesUsed.setEnabled(false);
		GridBagConstraints gbc_chkbxNavalMinesUsed = new GridBagConstraints();
		gbc_chkbxNavalMinesUsed.fill = GridBagConstraints.BOTH;
		gbc_chkbxNavalMinesUsed.insets = new Insets(0, 0, 5, 5);
		gbc_chkbxNavalMinesUsed.gridx = 7;
		gbc_chkbxNavalMinesUsed.gridy = 3;
		add(chkbxNavalMinesUsed, gbc_chkbxNavalMinesUsed);
		
		lblShotsHitDesc = new JLabel(JBattleships.game.getLocalization("GAME_STATS_HIT_SHOTS"));
		lblShotsHitDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShotsHitDesc = new GridBagConstraints();
		gbc_lblShotsHitDesc.fill = GridBagConstraints.BOTH;
		gbc_lblShotsHitDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblShotsHitDesc.gridx = 1;
		gbc_lblShotsHitDesc.gridy = 5;
		add(lblShotsHitDesc, gbc_lblShotsHitDesc);
		
		lblShotsHit = new JLabel("0");
		lblShotsHit.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShotsHit = new GridBagConstraints();
		gbc_lblShotsHit.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblShotsHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblShotsHit.gridx = 2;
		gbc_lblShotsHit.gridy = 5;
		add(lblShotsHit, gbc_lblShotsHit);
		
		lblCoastalArtilleryHit = new JLabel(JBattleships.game.getLocalization("GAME_STATS_COASTAL_ARTILLERY_HIT"));
		lblCoastalArtilleryHit.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblCoastalArtilleryHit = new GridBagConstraints();
		gbc_lblCoastalArtilleryHit.fill = GridBagConstraints.BOTH;
		gbc_lblCoastalArtilleryHit.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoastalArtilleryHit.gridx = 6;
		gbc_lblCoastalArtilleryHit.gridy = 5;
		add(lblCoastalArtilleryHit, gbc_lblCoastalArtilleryHit);
		
		chkbxCoastalArtilleryHit = new JCheckBox("");
		chkbxCoastalArtilleryHit.setFont(JBSGUI.MAIN_FONT);
		chkbxCoastalArtilleryHit.setOpaque(false);
		chkbxCoastalArtilleryHit.setEnabled(false);
		GridBagConstraints gbc_chkbxCoastalArtilleryHit = new GridBagConstraints();
		gbc_chkbxCoastalArtilleryHit.fill = GridBagConstraints.BOTH;
		gbc_chkbxCoastalArtilleryHit.insets = new Insets(0, 0, 5, 5);
		gbc_chkbxCoastalArtilleryHit.gridx = 7;
		gbc_chkbxCoastalArtilleryHit.gridy = 5;
		add(chkbxCoastalArtilleryHit, gbc_chkbxCoastalArtilleryHit);
		
		lblShipsLostDesc = new JLabel(JBattleships.game.getLocalization("GAME_STATS_SHIPS_LOST"));
		lblShipsLostDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsLostDesc = new GridBagConstraints();
		gbc_lblShipsLostDesc.fill = GridBagConstraints.BOTH;
		gbc_lblShipsLostDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsLostDesc.gridx = 1;
		gbc_lblShipsLostDesc.gridy = 7;
		add(lblShipsLostDesc, gbc_lblShipsLostDesc);
		
		lblShipsLost = new JLabel("0");
		lblShipsLost.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsLost = new GridBagConstraints();
		gbc_lblShipsLost.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblShipsLost.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsLost.gridx = 2;
		gbc_lblShipsLost.gridy = 7;
		add(lblShipsLost, gbc_lblShipsLost);
		
		lblCoastalArtilleryUsed = new JLabel(JBattleships.game.getLocalization("GAME_STATS_COASTAL_ARTILLERY_USED"));
		lblCoastalArtilleryUsed.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblCoastalArtilleryUsed = new GridBagConstraints();
		gbc_lblCoastalArtilleryUsed.fill = GridBagConstraints.BOTH;
		gbc_lblCoastalArtilleryUsed.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoastalArtilleryUsed.gridx = 6;
		gbc_lblCoastalArtilleryUsed.gridy = 7;
		add(lblCoastalArtilleryUsed, gbc_lblCoastalArtilleryUsed);
		
		chkbxCoastalArtilleryUsed = new JCheckBox("");
		chkbxCoastalArtilleryUsed.setFont(JBSGUI.MAIN_FONT);
		chkbxCoastalArtilleryUsed.setOpaque(false);
		chkbxCoastalArtilleryUsed.setEnabled(false);
		GridBagConstraints gbc_chkbxCoastalArtilleryUsed = new GridBagConstraints();
		gbc_chkbxCoastalArtilleryUsed.fill = GridBagConstraints.BOTH;
		gbc_chkbxCoastalArtilleryUsed.insets = new Insets(0, 0, 5, 5);
		gbc_chkbxCoastalArtilleryUsed.gridx = 7;
		gbc_chkbxCoastalArtilleryUsed.gridy = 7;
		add(chkbxCoastalArtilleryUsed, gbc_chkbxCoastalArtilleryUsed);
		
		lblShipsDestroyedDesc = new JLabel(JBattleships.game.getLocalization("GAME_STATS_SHIPS_DESTROYED"));
		lblShipsDestroyedDesc.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsDestroyedDesc = new GridBagConstraints();
		gbc_lblShipsDestroyedDesc.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsDestroyedDesc.fill = GridBagConstraints.BOTH;
		gbc_lblShipsDestroyedDesc.gridx = 1;
		gbc_lblShipsDestroyedDesc.gridy = 9;
		add(lblShipsDestroyedDesc, gbc_lblShipsDestroyedDesc);
		
		lblShipsDestroyed = new JLabel("0");
		lblShipsDestroyed.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblShipsDestroyed = new GridBagConstraints();
		gbc_lblShipsDestroyed.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblShipsDestroyed.insets = new Insets(0, 0, 5, 5);
		gbc_lblShipsDestroyed.gridx = 2;
		gbc_lblShipsDestroyed.gridy = 9;
		add(lblShipsDestroyed, gbc_lblShipsDestroyed);
		
		lblFlawlessWin = new JLabel(JBattleships.game.getLocalization("GAME_STATS_FLAWLESS_WIN"));
		lblFlawlessWin.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblFlawlessWin = new GridBagConstraints();
		gbc_lblFlawlessWin.fill = GridBagConstraints.BOTH;
		gbc_lblFlawlessWin.insets = new Insets(0, 0, 5, 5);
		gbc_lblFlawlessWin.gridx = 6;
		gbc_lblFlawlessWin.gridy = 9;
		add(lblFlawlessWin, gbc_lblFlawlessWin);
		
		chkbxFlawless = new JCheckBox("");
		chkbxFlawless.setFont(JBSGUI.MAIN_FONT);
		chkbxFlawless.setOpaque(false);
		chkbxFlawless.setEnabled(false);
		GridBagConstraints gbc_chkbxFlawless = new GridBagConstraints();
		gbc_chkbxFlawless.insets = new Insets(0, 0, 5, 5);
		gbc_chkbxFlawless.fill = GridBagConstraints.BOTH;
		gbc_chkbxFlawless.gridx = 7;
		gbc_chkbxFlawless.gridy = 9;
		add(chkbxFlawless, gbc_chkbxFlawless);
	}

	public final int getFiredShots() {
		return Integer.parseInt(lblFiredShots.getText());
	}

	public final void setFiredShots(int count) {
		this.lblFiredShots.setText(Integer.toString(count));
	}

	public final int getMissedShots() {
		return Integer.parseInt(lblMissedShots.getText());
	}

	public final void setMissedShots(int count) {
		this.lblMissedShots.setText(Integer.toString(count));
	}

	public final int getShotsHit() {
		return Integer.parseInt(lblShotsHit.getText());
	}

	public final void setShotsHit(int count) {
		this.lblShotsHit.setText(Integer.toString(count));
	}

	public final int getShipsLost() {
		return Integer.parseInt(lblShipsLost.getText());
	}

	public final void setShipsLost(int count) {
		this.lblShipsLost.setText(Integer.toString(count));
	}

	public final int getShipsDestroyed() {
		return Integer.parseInt(lblShipsDestroyed.getText());
	}

	public final void setShipsDestroyed(int count) {
		this.lblShipsDestroyed.setText(Integer.toString(count));
	}

	public final int getNavalMinesHit() {
		return Integer.parseInt(lblNavalMinesHit.getText());
	}

	public final void setNavalMinesHit(int count) {
		this.lblNavalMinesHit.setText(Integer.toString(count));
	}

	public final boolean isNavalMinesUsed() {
		return chkbxNavalMinesUsed.isSelected();
	}

	public final void setNavalMinesUsed(boolean selected) {
		this.chkbxNavalMinesUsed.setSelected(selected);;
	}

	public final boolean isCoastalArtilleryHit() {
		return chkbxCoastalArtilleryHit.isSelected();
	}

	public final void setCoastalArtilleryHit(boolean selected) {
		this.chkbxCoastalArtilleryHit.setSelected(selected);;
	}

	public final boolean isFlawlessWin() {
		return chkbxFlawless.isSelected();
	}

	public final void setFlawlessWin(boolean selected) {
		this.chkbxFlawless.setSelected(selected);;
	}

	public final boolean isCoastalArtilleryUsed() {
		return chkbxCoastalArtilleryUsed.isSelected();
	}

	public final void setCoastalArtilleryUsed(boolean selected) {
		this.chkbxCoastalArtilleryUsed.setSelected(selected);
	}

}
