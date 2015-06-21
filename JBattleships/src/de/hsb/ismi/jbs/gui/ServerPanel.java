/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;

import java.awt.Insets;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import de.hsb.ismi.jbs.engine.network.game.LobbyInfo;
import de.hsb.ismi.jbs.start.JBattleships;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class ServerPanel extends JPanel {
	
	private JPanel infoPanel = new JPanel();
	private JPanel serverSettingsPanel = new JPanel();
	
	private JLabel lblServerlIPText = new JLabel("Server IP:");
	private JLabel lblPlayers = new JLabel("Players:");
	private JLabel lblClientIPText = new JLabel("Client IP:");
	private JLabel lblTotalShips = new JLabel("Total Ships:");
	private JLabel lblDestroyers = new JLabel("Destroyers:");
	private JLabel lblServerIP = new JLabel("127.0.0.1");
	private JLabel lblPlayerCount = new JLabel("8");
	private JLabel lblClientIP = new JLabel("127.0.0.1");
	private JLabel lblTotalShipCount = new JLabel("24");
	private JLabel lblDestroyerCount = new JLabel("1");
	private JLabel lblFrigates = new JLabel("Frigates:");
	private JLabel lblCorvettes = new JLabel("Corvettes:");
	private JLabel lblSubmarines = new JLabel("Submarines:");
	private JLabel lblFrigateCount = new JLabel("2");
	private JLabel lblCorvetteCount = new JLabel("3");
	private JLabel lblSubmarineCount = new JLabel("4");
	private JCheckBox chckbxNavalMinesUsed = new JCheckBox("Naval Mines");
	private JCheckBox chckbxCoastalArtilleryUsed = new JCheckBox("Coastal Artillery");
	private JLabel lblFieldsize = new JLabel("Player Fieldsize:");
	private JLabel lblFieldSizeNumber = new JLabel("10");
	private JLabel lblDestroyerspinner = new JLabel("Destroyers:");
	private JLabel lblFrigateSpinner = new JLabel("Frigates:");
	private JLabel lblCorvetteSpinner = new JLabel("Corvettes:");
	private JLabel lblSubmarineSpinner = new JLabel("Submarines:");
	private JLabel lblPlayerFieldsize = new JLabel("Player Fieldsize:");
	private JBSSpinner destroyerSpinner = new JBSSpinner();
	private JBSSpinner frigateSpinner = new JBSSpinner();
	private JBSSpinner corvetteSpinner = new JBSSpinner();
	private JBSSpinner submarineSpinner = new JBSSpinner();
	private JBSSpinner sizeSpinner = new JBSSpinner();
	private JCheckBox chckbxUseNavalMines = new JCheckBox("Use Naval Mines");
	private JCheckBox chckbxUseCoastalArtillery = new JCheckBox("Use Coastal Artillery");
	private JSeparator infoSeperator = new JSeparator();
	private JSeparator settingsSeperator = new JSeparator();
	

	private boolean isHost = false;
	
	/**
	 * Create the panel.
	 */
	public ServerPanel(boolean isHost) {
		this.isHost = isHost;
		setBackground(JBSGUI.BACKGROUND_COLOR);
		setLayout(new MigLayout("", "[300px,grow,fill]", "[120px,grow][92px,grow,fill]"));
		infoPanel.setBorder(new TitledBorder(null, "Server Infos:", TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.SERVER_FONT, null));
		infoPanel.setOpaque(false);
		add(infoPanel, "cell 0 0,grow");
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[]{46, 0, 0, 0, 0, 0};
		gbl_infoPanel.rowHeights = new int[]{14, 14, 0, 0, 0, 0};
		gbl_infoPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_infoPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		infoPanel.setLayout(gbl_infoPanel);
		
		GridBagConstraints gbc_lblServerlIPText = new GridBagConstraints();
		gbc_lblServerlIPText.fill = GridBagConstraints.BOTH;
		gbc_lblServerlIPText.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerlIPText.gridx = 0;
		gbc_lblServerlIPText.gridy = 0;
		lblServerlIPText.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblServerlIPText, gbc_lblServerlIPText);
		
		GridBagConstraints gbc_lblServerIP = new GridBagConstraints();
		gbc_lblServerIP.fill = GridBagConstraints.BOTH;
		gbc_lblServerIP.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerIP.gridx = 1;
		gbc_lblServerIP.gridy = 0;
		lblServerIP.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblServerIP, gbc_lblServerIP);
		
		GridBagConstraints gbc_infoSeperator = new GridBagConstraints();
		gbc_infoSeperator.gridheight = 5;
		gbc_infoSeperator.fill = GridBagConstraints.VERTICAL;
		gbc_infoSeperator.insets = new Insets(0, 0, 5, 5);
		gbc_infoSeperator.gridx = 2;
		gbc_infoSeperator.gridy = 0;
		infoSeperator.setOrientation(SwingConstants.VERTICAL);
		infoPanel.add(infoSeperator, gbc_infoSeperator);
		
		GridBagConstraints gbc_lblDestroyers = new GridBagConstraints();
		gbc_lblDestroyers.fill = GridBagConstraints.BOTH;
		gbc_lblDestroyers.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestroyers.gridx = 3;
		gbc_lblDestroyers.gridy = 0;
		lblDestroyers.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblDestroyers, gbc_lblDestroyers);
		
		GridBagConstraints gbc_lblDestroyerCount = new GridBagConstraints();
		gbc_lblDestroyerCount.fill = GridBagConstraints.BOTH;
		gbc_lblDestroyerCount.insets = new Insets(0, 0, 5, 0);
		gbc_lblDestroyerCount.gridx = 4;
		gbc_lblDestroyerCount.gridy = 0;
		lblDestroyerCount.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblDestroyerCount, gbc_lblDestroyerCount);
		
		GridBagConstraints gbc_lblPlayers = new GridBagConstraints();
		gbc_lblPlayers.fill = GridBagConstraints.BOTH;
		gbc_lblPlayers.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayers.gridx = 0;
		gbc_lblPlayers.gridy = 1;
		lblPlayers.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblPlayers, gbc_lblPlayers);
		
		GridBagConstraints gbc_lblPlayerCount = new GridBagConstraints();
		gbc_lblPlayerCount.fill = GridBagConstraints.BOTH;
		gbc_lblPlayerCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerCount.gridx = 1;
		gbc_lblPlayerCount.gridy = 1;
		lblPlayerCount.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblPlayerCount, gbc_lblPlayerCount);
		
		GridBagConstraints gbc_lblFrigates = new GridBagConstraints();
		gbc_lblFrigates.fill = GridBagConstraints.BOTH;
		gbc_lblFrigates.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrigates.gridx = 3;
		gbc_lblFrigates.gridy = 1;
		lblFrigates.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblFrigates, gbc_lblFrigates);
		
		GridBagConstraints gbc_lblFrigateCount = new GridBagConstraints();
		gbc_lblFrigateCount.fill = GridBagConstraints.BOTH;
		gbc_lblFrigateCount.insets = new Insets(0, 0, 5, 0);
		gbc_lblFrigateCount.gridx = 4;
		gbc_lblFrigateCount.gridy = 1;
		lblFrigateCount.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblFrigateCount, gbc_lblFrigateCount);
		
		GridBagConstraints gbc_lblClientIPText = new GridBagConstraints();
		gbc_lblClientIPText.fill = GridBagConstraints.BOTH;
		gbc_lblClientIPText.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientIPText.gridx = 0;
		gbc_lblClientIPText.gridy = 2;
		lblClientIPText.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblClientIPText, gbc_lblClientIPText);
		
		GridBagConstraints gbc_lblClientIP = new GridBagConstraints();
		gbc_lblClientIP.fill = GridBagConstraints.BOTH;
		gbc_lblClientIP.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientIP.gridx = 1;
		gbc_lblClientIP.gridy = 2;
		lblClientIP.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblClientIP, gbc_lblClientIP);
		
		GridBagConstraints gbc_lblCorvettes = new GridBagConstraints();
		gbc_lblCorvettes.fill = GridBagConstraints.BOTH;
		gbc_lblCorvettes.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorvettes.gridx = 3;
		gbc_lblCorvettes.gridy = 2;
		lblCorvettes.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblCorvettes, gbc_lblCorvettes);
		
		GridBagConstraints gbc_lblCorvetteCount = new GridBagConstraints();
		gbc_lblCorvetteCount.fill = GridBagConstraints.BOTH;
		gbc_lblCorvetteCount.insets = new Insets(0, 0, 5, 0);
		gbc_lblCorvetteCount.gridx = 4;
		gbc_lblCorvetteCount.gridy = 2;
		lblCorvetteCount.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblCorvetteCount, gbc_lblCorvetteCount);
		
		GridBagConstraints gbc_lblTotalShips = new GridBagConstraints();
		gbc_lblTotalShips.fill = GridBagConstraints.BOTH;
		gbc_lblTotalShips.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalShips.gridx = 0;
		gbc_lblTotalShips.gridy = 3;
		lblTotalShips.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblTotalShips, gbc_lblTotalShips);
		
		GridBagConstraints gbc_lblTotalShipCount = new GridBagConstraints();
		gbc_lblTotalShipCount.fill = GridBagConstraints.BOTH;
		gbc_lblTotalShipCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalShipCount.gridx = 1;
		gbc_lblTotalShipCount.gridy = 3;
		lblTotalShipCount.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblTotalShipCount, gbc_lblTotalShipCount);
		
		GridBagConstraints gbc_lblSubmarines = new GridBagConstraints();
		gbc_lblSubmarines.fill = GridBagConstraints.BOTH;
		gbc_lblSubmarines.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubmarines.gridx = 3;
		gbc_lblSubmarines.gridy = 3;
		lblSubmarines.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblSubmarines, gbc_lblSubmarines);
		
		GridBagConstraints gbc_lblSubmarineCount = new GridBagConstraints();
		gbc_lblSubmarineCount.fill = GridBagConstraints.BOTH;
		gbc_lblSubmarineCount.insets = new Insets(0, 0, 5, 0);
		gbc_lblSubmarineCount.gridx = 4;
		gbc_lblSubmarineCount.gridy = 3;
		lblSubmarineCount.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblSubmarineCount, gbc_lblSubmarineCount);
		
		GridBagConstraints gbc_lblFieldsize = new GridBagConstraints();
		gbc_lblFieldsize.fill = GridBagConstraints.BOTH;
		gbc_lblFieldsize.insets = new Insets(0, 0, 0, 5);
		gbc_lblFieldsize.gridx = 0;
		gbc_lblFieldsize.gridy = 4;
		lblFieldsize.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblFieldsize, gbc_lblFieldsize);
		
		GridBagConstraints gbc_lblFieldSizeNumber = new GridBagConstraints();
		gbc_lblFieldSizeNumber.fill = GridBagConstraints.BOTH;
		gbc_lblFieldSizeNumber.insets = new Insets(0, 0, 0, 5);
		gbc_lblFieldSizeNumber.gridx = 1;
		gbc_lblFieldSizeNumber.gridy = 4;
		lblFieldSizeNumber.setFont(JBSGUI.SERVER_FONT);
		infoPanel.add(lblFieldSizeNumber, gbc_lblFieldSizeNumber);
		
		GridBagConstraints gbc_chckbxNavalMinesUsed = new GridBagConstraints();
		gbc_chckbxNavalMinesUsed.fill = GridBagConstraints.BOTH;
		gbc_chckbxNavalMinesUsed.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNavalMinesUsed.gridx = 3;
		gbc_chckbxNavalMinesUsed.gridy = 4;
		chckbxNavalMinesUsed.setFont(JBSGUI.SERVER_FONT);
		chckbxNavalMinesUsed.setEnabled(false);
		chckbxNavalMinesUsed.setOpaque(false);
		infoPanel.add(chckbxNavalMinesUsed, gbc_chckbxNavalMinesUsed);
		
		GridBagConstraints gbc_chckbxCoastalArtilleryUsed = new GridBagConstraints();
		gbc_chckbxCoastalArtilleryUsed.fill = GridBagConstraints.BOTH;
		gbc_chckbxCoastalArtilleryUsed.gridx = 4;
		gbc_chckbxCoastalArtilleryUsed.gridy = 4;
		chckbxCoastalArtilleryUsed.setFont(JBSGUI.SERVER_FONT);
		chckbxCoastalArtilleryUsed.setEnabled(false);
		chckbxCoastalArtilleryUsed.setOpaque(false);
		infoPanel.add(chckbxCoastalArtilleryUsed, gbc_chckbxCoastalArtilleryUsed);
		serverSettingsPanel.setBorder(new TitledBorder(null, "Server Settings:", TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.SERVER_FONT, null));
		
		GridBagLayout gbl_serverSettingsPanel = new GridBagLayout();
		gbl_serverSettingsPanel.columnWidths = new int[] {0, 100, 30, 0, 0};
		gbl_serverSettingsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_serverSettingsPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_serverSettingsPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		serverSettingsPanel.setLayout(gbl_serverSettingsPanel);
		
		GridBagConstraints gbc_lblDestroyerspinner = new GridBagConstraints();
		gbc_lblDestroyerspinner.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestroyerspinner.fill = GridBagConstraints.BOTH;
		gbc_lblDestroyerspinner.gridx = 0;
		gbc_lblDestroyerspinner.gridy = 0;
		lblDestroyerspinner.setFont(JBSGUI.SERVER_FONT);
		serverSettingsPanel.add(lblDestroyerspinner, gbc_lblDestroyerspinner);
		
		GridBagConstraints gbc_destroyerSpinner = new GridBagConstraints();
		gbc_destroyerSpinner.fill = GridBagConstraints.BOTH;
		gbc_destroyerSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_destroyerSpinner.gridx = 1;
		gbc_destroyerSpinner.gridy = 0;
		destroyerSpinner.setFont(JBSGUI.SERVER_FONT);
		destroyerSpinner.setModel(new SpinnerNumberModel(1, 0, 32, 1));
		destroyerSpinner.addChangeListener(e -> {
			JBattleships.game.getGameServer().getServerGameManager().getGame().setDestroyerCount(getDestroyerCount());
			lblDestroyerCount.setText(Integer.toString(getDestroyerCount()));
			calculateTotalShips();
		});
		serverSettingsPanel.add(destroyerSpinner, gbc_destroyerSpinner);
		
		GridBagConstraints gbc_chckbxUseNavalMines = new GridBagConstraints();
		gbc_chckbxUseNavalMines.fill = GridBagConstraints.BOTH;
		gbc_chckbxUseNavalMines.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseNavalMines.gridx = 3;
		gbc_chckbxUseNavalMines.gridy = 0;
		chckbxUseNavalMines.setFont(JBSGUI.SERVER_FONT);
		chckbxUseNavalMines.setOpaque(false);
		chckbxUseNavalMines.addItemListener(i -> {
			chckbxNavalMinesUsed.setSelected(chckbxUseNavalMines.isSelected());
		});
		
		GridBagConstraints gbc_settingsSeperator = new GridBagConstraints();
		gbc_settingsSeperator.fill = GridBagConstraints.VERTICAL;
		gbc_settingsSeperator.gridheight = 5;
		gbc_settingsSeperator.insets = new Insets(0, 0, 5, 5);
		gbc_settingsSeperator.gridx = 2;
		gbc_settingsSeperator.gridy = 0;
		settingsSeperator.setOrientation(SwingConstants.VERTICAL);
		serverSettingsPanel.add(settingsSeperator, gbc_settingsSeperator);
		serverSettingsPanel.add(chckbxUseNavalMines, gbc_chckbxUseNavalMines);
		
		GridBagConstraints gbc_lblFrigateSpinner = new GridBagConstraints();
		gbc_lblFrigateSpinner.fill = GridBagConstraints.BOTH;
		gbc_lblFrigateSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrigateSpinner.gridx = 0;
		gbc_lblFrigateSpinner.gridy = 1;
		lblFrigateSpinner.setFont(JBSGUI.SERVER_FONT);
		serverSettingsPanel.add(lblFrigateSpinner, gbc_lblFrigateSpinner);
		
		GridBagConstraints gbc_frigateSpinner = new GridBagConstraints();
		gbc_frigateSpinner.fill = GridBagConstraints.BOTH;
		gbc_frigateSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_frigateSpinner.gridx = 1;
		gbc_frigateSpinner.gridy = 1;
		frigateSpinner.setFont(JBSGUI.SERVER_FONT);
		frigateSpinner.setModel(new SpinnerNumberModel(2, 0, 32, 1));
		frigateSpinner.addChangeListener(e -> {
			JBattleships.game.getGameServer().getServerGameManager().getGame().setFrigateCount(getFrigateCount());
			lblFrigateCount.setText(Integer.toString(getFrigateCount()));
			calculateTotalShips();
		});
		serverSettingsPanel.add(frigateSpinner, gbc_frigateSpinner);
		
		GridBagConstraints gbc_chckbxUseCoastalArtillery = new GridBagConstraints();
		gbc_chckbxUseCoastalArtillery.fill = GridBagConstraints.BOTH;
		gbc_chckbxUseCoastalArtillery.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUseCoastalArtillery.gridx = 3;
		gbc_chckbxUseCoastalArtillery.gridy = 1;
		chckbxUseCoastalArtillery.setFont(JBSGUI.SERVER_FONT);
		chckbxUseCoastalArtillery.setOpaque(false);
		chckbxUseCoastalArtillery.addItemListener(i -> {
			chckbxCoastalArtilleryUsed.setSelected(chckbxUseCoastalArtillery.isSelected());
		});
		serverSettingsPanel.add(chckbxUseCoastalArtillery, gbc_chckbxUseCoastalArtillery);
		
		GridBagConstraints gbc_lblCorvetteSpinner = new GridBagConstraints();
		gbc_lblCorvetteSpinner.fill = GridBagConstraints.BOTH;
		gbc_lblCorvetteSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorvetteSpinner.gridx = 0;
		gbc_lblCorvetteSpinner.gridy = 2;
		lblCorvetteSpinner.setFont(JBSGUI.SERVER_FONT);
		serverSettingsPanel.add(lblCorvetteSpinner, gbc_lblCorvetteSpinner);
		
		GridBagConstraints gbc_corvetteSpinner = new GridBagConstraints();
		gbc_corvetteSpinner.fill = GridBagConstraints.BOTH;
		gbc_corvetteSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_corvetteSpinner.gridx = 1;
		gbc_corvetteSpinner.gridy = 2;
		corvetteSpinner.setFont(JBSGUI.SERVER_FONT);
		corvetteSpinner.setModel(new SpinnerNumberModel(3, 0, 32, 1));
		corvetteSpinner.addChangeListener(e -> {
			JBattleships.game.getGameServer().getServerGameManager().getGame().setCorvetteCount(getCorvetteCount());
			lblCorvetteCount.setText(Integer.toString(getCorvetteCount()));
			calculateTotalShips();
		});
		serverSettingsPanel.add(corvetteSpinner, gbc_corvetteSpinner);
		
		GridBagConstraints gbc_lblSubmarineSpinner = new GridBagConstraints();
		gbc_lblSubmarineSpinner.fill = GridBagConstraints.BOTH;
		gbc_lblSubmarineSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubmarineSpinner.gridx = 0;
		gbc_lblSubmarineSpinner.gridy = 3;
		lblSubmarineSpinner.setFont(JBSGUI.SERVER_FONT);
		serverSettingsPanel.add(lblSubmarineSpinner, gbc_lblSubmarineSpinner);
		
		GridBagConstraints gbc_submarineSpinner = new GridBagConstraints();
		gbc_submarineSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_submarineSpinner.fill = GridBagConstraints.BOTH;
		gbc_submarineSpinner.gridx = 1;
		gbc_submarineSpinner.gridy = 3;
		submarineSpinner.setFont(JBSGUI.SERVER_FONT);
		submarineSpinner.setModel(new SpinnerNumberModel(4, 0, 32, 1));
		submarineSpinner.addChangeListener(e -> {
			JBattleships.game.getGameServer().getServerGameManager().getGame().setSubmarineCount(getSubmarineCount());
			lblSubmarineCount.setText(Integer.toString(getSubmarineCount()));
			calculateTotalShips();
		});
		
		GridBagConstraints gbc_lblPlayerFieldsize = new GridBagConstraints();
		gbc_lblPlayerFieldsize.fill = GridBagConstraints.BOTH;
		gbc_lblPlayerFieldsize.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlayerFieldsize.gridx = 0;
		gbc_lblPlayerFieldsize.gridy = 4;
		lblPlayerFieldsize.setFont(JBSGUI.SERVER_FONT);
		serverSettingsPanel.add(lblPlayerFieldsize, gbc_lblPlayerFieldsize);
		
		GridBagConstraints gbc_sizeSpinner = new GridBagConstraints();
		gbc_sizeSpinner.fill = GridBagConstraints.BOTH;
		gbc_sizeSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_sizeSpinner.gridx = 1;
		gbc_sizeSpinner.gridy = 4;
		sizeSpinner.setFont(JBSGUI.SERVER_FONT);
		sizeSpinner.setModel(new SpinnerNumberModel(10, 0, 20, 1));
		sizeSpinner.addChangeListener(e -> {
			JBattleships.game.getGameServer().getServerGameManager().getGame().setFieldSize(getFieldSize());
			lblFieldSizeNumber.setText(Integer.toString(getFieldSize()));
			calculateTotalShips();
		});
		serverSettingsPanel.add(sizeSpinner, gbc_sizeSpinner);
		serverSettingsPanel.add(submarineSpinner, gbc_submarineSpinner);
		
		serverSettingsPanel.setOpaque(false);

		if(isHost){
			add(serverSettingsPanel, "cell 0 1,grow");
		}
	}

	/**
	 * @return the isHost
	 */
	public final boolean isHost() {
		return isHost;
	}
	
	public final int getDestroyerCount(){
		return ((Number)destroyerSpinner.getValue()).intValue();
	}
	
	public final int getFrigateCount(){
		return ((Number)frigateSpinner.getValue()).intValue();
	}
	
	public final int getCorvetteCount(){
		return ((Number)corvetteSpinner.getValue()).intValue();
	}
	
	public final int getSubmarineCount(){
		return ((Number)submarineSpinner.getValue()).intValue();
	}
	
	public final int getFieldSize(){
		return ((Number)sizeSpinner.getValue()).intValue();
	}
	
	public final boolean useNavalMines(){
		return chckbxUseNavalMines.isSelected();
	}
	
	public final boolean useCoastalArtillery(){
		return chckbxUseCoastalArtillery.isSelected();
	}
	
	/**
	 * 
	 */
	protected void calculateTotalShips(){
		int count = 0;
		if(isHost){
			count = getDestroyerCount() + getFrigateCount() + getCorvetteCount() + getSubmarineCount();
		}else{
			count = Integer.parseInt(lblDestroyerCount.getText()) + Integer.parseInt(lblFrigateCount.getText()) + Integer.parseInt(lblCorvetteCount.getText()) + Integer.parseInt(lblSubmarineCount.getText());
		}
		lblTotalShipCount.setText(Integer.toString(count));
	}
	

	/**
	 * @return the lblPlayerCount
	 */
	public final JLabel getPlayerCountLabel() {
		return lblPlayerCount;
	}

	/**
	 * @return the lblDestroyerCount
	 */
	public final JLabel getDestroyerCountLabel() {
		return lblDestroyerCount;
	}

	/**
	 * @return the lblFrigateCount
	 */
	public final JLabel getFrigateCountLabel() {
		return lblFrigateCount;
	}

	/**
	 * @return the lblCorvetteCount
	 */
	public final JLabel getCorvetteCountLabel() {
		return lblCorvetteCount;
	}

	/**
	 * @return the lblSubmarineCount
	 */
	public final JLabel getSubmarineCountLabel() {
		return lblSubmarineCount;
	}

	/**
	 * @return the chckbxNavalMinesUsed
	 */
	public final JCheckBox getNavalMinesCheckbox() {
		return chckbxNavalMinesUsed;
	}

	/**
	 * @return the chckbxCoastalArtilleryUsed
	 */
	public final JCheckBox getCoastalArtilleryCheckbox() {
		return chckbxCoastalArtilleryUsed;
	}

	/**
	 * @return the lblFieldSizeNumber
	 */
	public final JLabel getFieldSizeLabel() {
		return lblFieldSizeNumber;
	}


	/**
	 * 
	 * @author Kevin Kuegler
	 * @version 1.00
	 */
	private class JBSSpinner extends JSpinner{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4624225435363924879L;

		/**
		 * 
		 */
		public JBSSpinner() {
		    JComponent comp = this.getEditor();
		    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		}
		
	}
}
