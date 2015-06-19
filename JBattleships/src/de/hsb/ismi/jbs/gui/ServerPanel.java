/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
	private JLabel lblDestroyerspinner = new JLabel("Destroyers:");
	private JLabel lblFrigateSpinner = new JLabel("Frigates:");
	private JLabel lblCorvetteSpinner = new JLabel("Corvettes:");
	private JLabel lblSubmarineSpinner = new JLabel("Submarines:");
	private JSpinner destroyerSpinner = new JSpinner();
	private JSpinner frigateSpinner = new JSpinner();
	private JSpinner corvetteSpinner = new JSpinner();
	private JSpinner submarineSpinner = new JSpinner();
	private JCheckBox chckbxUseNavalMines = new JCheckBox("Use Naval Mines");
	private JCheckBox chckbxUseCoastalArtillery = new JCheckBox("Use Coastal Artillery");
	
	

	/**
	 * Create the panel.
	 */
	public ServerPanel(boolean isHost) {
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
		gbl_serverSettingsPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_serverSettingsPanel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_serverSettingsPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		serverSettingsPanel.add(corvetteSpinner, gbc_corvetteSpinner);
		
		GridBagConstraints gbc_lblSubmarineSpinner = new GridBagConstraints();
		gbc_lblSubmarineSpinner.fill = GridBagConstraints.BOTH;
		gbc_lblSubmarineSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_lblSubmarineSpinner.gridx = 0;
		gbc_lblSubmarineSpinner.gridy = 3;
		lblSubmarineSpinner.setFont(JBSGUI.SERVER_FONT);
		serverSettingsPanel.add(lblSubmarineSpinner, gbc_lblSubmarineSpinner);
		
		GridBagConstraints gbc_submarineSpinner = new GridBagConstraints();
		gbc_submarineSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_submarineSpinner.fill = GridBagConstraints.BOTH;
		gbc_submarineSpinner.gridx = 1;
		gbc_submarineSpinner.gridy = 3;
		submarineSpinner.setFont(JBSGUI.SERVER_FONT);
		submarineSpinner.setModel(new SpinnerNumberModel(4, 0, 32, 1));
		serverSettingsPanel.add(submarineSpinner, gbc_submarineSpinner);
		serverSettingsPanel.setOpaque(false);
		//if(isHost){
			add(serverSettingsPanel, "cell 0 1,grow");
		//}

	}

}