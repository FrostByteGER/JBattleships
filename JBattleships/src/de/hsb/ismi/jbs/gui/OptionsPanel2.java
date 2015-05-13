/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Component;

import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JRadioButton;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JSlider;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ResolutionManager;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;
import de.hsb.ismi.jbs.start.JBattleships;

import javax.swing.JTextField;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsPanel2 extends JPanel{
	
	private JBSGUI parent;
	private JPanel centerPanel;
	private JPanel gfxPanel;
	private JPanel sfxPanel;
	private JPanel otherPanel;
	private JPanel buttonPanel;
	private JButton resetButton;
	private JButton saveButton;
	private JButton backButton;
	private JLabel lblRes;
	private JComboBox<String> resBox;
	private JLabel lblMode;
	private JRadioButton rdbtnFull;
	private JRadioButton rdbtnWin;
	private JRadioButton rdbtnLess;
	private final ButtonGroup buttonGroup;
	private JLabel lblVolume;
	private JSlider sliderVolume;
	private JSlider sliderMusic;
	private JLabel lblMusic;
	private JPanel networkPanel;
	private JLabel lblIP;
	private JComboBox<Object> ipBox;
	private JLabel lblPort;
	private JTextField portField;
	
	/**
	 * 
	 */
	public OptionsPanel2(JBSGUI parent) {
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		add(parent.generateHeader(), BorderLayout.NORTH);
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		buttonGroup = new ButtonGroup();
		gfxPanel = new JPanel();
		centerPanel.add(gfxPanel);
		gfxPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graphics", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_gfxPanel = new GridBagLayout();
		gbl_gfxPanel.columnWidths = new int[]{0, 0};
		gbl_gfxPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_gfxPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_gfxPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gfxPanel.setLayout(gbl_gfxPanel);
		
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 0;
		gfxPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea);
		
		lblRes = new JLabel("Resolution:");
		GridBagConstraints gbc_lblRes = new GridBagConstraints();
		gbc_lblRes.insets = new Insets(0, 0, 5, 0);
		gbc_lblRes.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRes.gridx = 0;
		gbc_lblRes.gridy = 1;
		gfxPanel.add(lblRes, gbc_lblRes);
		
		
		
		String[] modes = ResolutionManager.DisplayModesToString(JBSCore.resolutionManager.getSupportedDisplayModes(new int[]{60}), false);
		int index = ResolutionManager.findResolutionInDisplayModes(JBattleships.game.getCurrentResolution(), modes);
		
		resBox = new JComboBox<String>(modes);
		resBox.setSelectedIndex(index);
		GridBagConstraints gbc_resBox = new GridBagConstraints();
		gbc_resBox.insets = new Insets(0, 0, 5, 0);
		gbc_resBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_resBox.gridx = 0;
		gbc_resBox.gridy = 2;
		gfxPanel.add(resBox, gbc_resBox);
		
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_1.gridx = 0;
		gbc_rigidArea_1.gridy = 3;
		gfxPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea_1);
		
		lblMode = new JLabel("Mode:");
		GridBagConstraints gbc_lblMode = new GridBagConstraints();
		gbc_lblMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMode.insets = new Insets(0, 0, 5, 0);
		gbc_lblMode.gridx = 0;
		gbc_lblMode.gridy = 4;
		gfxPanel.add(lblMode, gbc_lblMode);
		
		rdbtnFull = new JRadioButton("Fullscreen");
		buttonGroup.add(rdbtnFull);
		GridBagConstraints gbc_rdbtnFull = new GridBagConstraints();
		gbc_rdbtnFull.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnFull.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFull.gridx = 0;
		gbc_rdbtnFull.gridy = 5;
		gfxPanel.add(rdbtnFull, gbc_rdbtnFull);
		
		rdbtnWin = new JRadioButton("Windowed");
		buttonGroup.add(rdbtnWin);
		GridBagConstraints gbc_rdbtnWin = new GridBagConstraints();
		gbc_rdbtnWin.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnWin.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnWin.gridx = 0;
		gbc_rdbtnWin.gridy = 6;
		gfxPanel.add(rdbtnWin, gbc_rdbtnWin);
		
		rdbtnLess = new JRadioButton("Borderless");
		buttonGroup.add(rdbtnLess);
		GridBagConstraints gbc_rdbtnLess = new GridBagConstraints();
		gbc_rdbtnLess.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnLess.gridx = 0;
		gbc_rdbtnLess.gridy = 7;
		gfxPanel.add(rdbtnLess, gbc_rdbtnLess);
		
		sfxPanel = new JPanel();
		sfxPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Audio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(sfxPanel);
		GridBagLayout gbl_sfxPanel = new GridBagLayout();
		gbl_sfxPanel.columnWidths = new int[] {0};
		gbl_sfxPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_sfxPanel.columnWeights = new double[]{1.0};
		gbl_sfxPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		sfxPanel.setLayout(gbl_sfxPanel);
		
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_rigidArea_2.gridx = 0;
		gbc_rigidArea_2.gridy = 0;
		sfxPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea_2);
		
		lblVolume = new JLabel("Volume:");
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.insets = new Insets(0, 0, 5, 0);
		gbc_lblVolume.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVolume.gridx = 0;
		gbc_lblVolume.gridy = 1;
		sfxPanel.add(lblVolume, gbc_lblVolume);
		
		sliderVolume = new JSlider();
		sliderVolume.setPaintTicks(true);
		sliderVolume.setLabelTable(sliderVolume.createStandardLabels(50));
		sliderVolume.setPaintLabels(true);
		GridBagConstraints gbc_sliderVolume = new GridBagConstraints();
		gbc_sliderVolume.insets = new Insets(0, 0, 5, 0);
		gbc_sliderVolume.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderVolume.gridx = 0;
		gbc_sliderVolume.gridy = 2;
		sfxPanel.add(sliderVolume, gbc_sliderVolume);
		
		GridBagConstraints gbc_rigidArea_3 = new GridBagConstraints();
		gbc_rigidArea_3.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_3.fill = GridBagConstraints.BOTH;
		gbc_rigidArea_3.gridx = 0;
		gbc_rigidArea_3.gridy = 3;
		sfxPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea_3);
		
		lblMusic = new JLabel("Music:");
		GridBagConstraints gbc_lblMusic = new GridBagConstraints();
		gbc_lblMusic.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMusic.insets = new Insets(0, 0, 5, 0);
		gbc_lblMusic.gridx = 0;
		gbc_lblMusic.gridy = 4;
		sfxPanel.add(lblMusic, gbc_lblMusic);
		
		sliderMusic = new JSlider();
		sliderMusic.setPaintTicks(true);
		sliderMusic.setPaintLabels(true);
		sliderMusic.setLabelTable(sliderMusic.createStandardLabels(50));
		GridBagConstraints gbc_sliderMusic = new GridBagConstraints();
		gbc_sliderMusic.fill = GridBagConstraints.BOTH;
		gbc_sliderMusic.gridx = 0;
		gbc_sliderMusic.gridy = 5;
		sfxPanel.add(sliderMusic, gbc_sliderMusic);
		
		otherPanel = new JPanel();
		otherPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Network", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(otherPanel);
		otherPanel.setLayout(new BorderLayout(0, 0));
		
		networkPanel = new JPanel();
		otherPanel.add(networkPanel, BorderLayout.CENTER);
		GridBagLayout gbl_networkPanel = new GridBagLayout();
		gbl_networkPanel.columnWidths = new int[] {0};
		gbl_networkPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		gbl_networkPanel.columnWeights = new double[]{1.0};
		gbl_networkPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		networkPanel.setLayout(gbl_networkPanel);

		GridBagConstraints gbc_rigidArea_4 = new GridBagConstraints();
		gbc_rigidArea_4.fill = GridBagConstraints.BOTH;
		gbc_rigidArea_4.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_4.gridx = 0;
		gbc_rigidArea_4.gridy = 0;
		networkPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea_4);
		
		lblIP = new JLabel("IP:");
		GridBagConstraints gbc_lblIP = new GridBagConstraints();
		gbc_lblIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIP.insets = new Insets(0, 0, 5, 0);
		gbc_lblIP.gridx = 0;
		gbc_lblIP.gridy = 1;
		networkPanel.add(lblIP, gbc_lblIP);
		
		ipBox = new JComboBox<Object>(); //TODO: Change type!
		GridBagConstraints gbc_ipBox = new GridBagConstraints();
		gbc_ipBox.insets = new Insets(0, 0, 5, 0);
		gbc_ipBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_ipBox.gridx = 0;
		gbc_ipBox.gridy = 2;
		networkPanel.add(ipBox, gbc_ipBox);
		
		GridBagConstraints gbc_rigidArea_5 = new GridBagConstraints();
		gbc_rigidArea_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_rigidArea_5.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_5.gridx = 0;
		gbc_rigidArea_5.gridy = 3;
		networkPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea_5);
		
		lblPort = new JLabel("Port:");
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPort.insets = new Insets(0, 0, 5, 0);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 4;
		networkPanel.add(lblPort, gbc_lblPort);
		
		portField = new JTextField();
		GridBagConstraints gbc_portField = new GridBagConstraints();
		gbc_portField.insets = new Insets(0, 0, 5, 0);
		gbc_portField.fill = GridBagConstraints.HORIZONTAL;
		gbc_portField.gridx = 0;
		gbc_portField.gridy = 5;
		networkPanel.add(portField, gbc_portField);
		portField.setColumns(10);
		
		buttonPanel = new JPanel();
		centerPanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		resetButton = new JButton("Reset to Default");
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("reset")){
					JBSCore.msgLogger.addMessage("Called Command: \"" + e.getActionCommand() + "\" on " + OptionsPanel2.this.getClass());
				}
			}
		});
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(resetButton);
		
		saveButton = new JButton("Save Settings");
		saveButton.setActionCommand("save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("save")){
					JBSCore.msgLogger.addMessage("Called Command: \"" + e.getActionCommand() + "\" on " + OptionsPanel2.this.getClass());
					//TODO: Change later
					String x = (String) resBox.getSelectedItem();
					
					// If a custom resolution was set in the Settings.cfg, the resBox won't possibly have a match, this prevents a NullPointerException!
					if(x == null){
						x = ResolutionManager.DisplayModeToString(JBSCore.resolutionManager.getCurrentDisplayMode(),false);
						resBox.setSelectedItem(x); // Prevents that the JComboBox selection is still null!
					}
					
					String[] split = x.split("x");
					JBattleships.game.changeResolution(new Resolution(Integer.parseInt(split[0]),Integer.parseInt(split[1])));
					
					if(rdbtnFull.isSelected()){
						JBattleships.game.changeScreenMode(ScreenMode.MODE_FULLSCREEN);
					}else if(rdbtnWin.isSelected()){
						JBattleships.game.changeScreenMode(ScreenMode.MODE_WINDOWED);
					}else if(rdbtnLess.isSelected()){
						JBattleships.game.changeScreenMode(ScreenMode.MODE_BORDERLESS);
					}
					//TODO: Doesn't work fully yet!
					boolean check = JBattleships.game.getDataManager().getOptionsManager().saveOptions(new HashMap<String, String[]>());
					System.out.println("Saving was: " + check);
				}
			}
		});
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(saveButton);
		
		backButton = new JButton("Back");
		backButton.setActionCommand("back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("back")){
					JBSCore.msgLogger.addMessage("Called Command: \"" + e.getActionCommand() + "\" on " + OptionsPanel2.this.getClass());
					OptionsPanel2.this.parent.swapContainer(OptionsPanel2.this.parent.getMainPanel());
				}
			}
		});
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(backButton);
		
		switch(JBattleships.game.getScreenMode()){
		case MODE_FULLSCREEN:
			buttonGroup.setSelected(rdbtnFull.getModel(), true);
			break;
		case MODE_BORDERLESS:
			buttonGroup.setSelected(rdbtnLess.getModel(), true);
			break;
		case MODE_WINDOWED:
			buttonGroup.setSelected(rdbtnWin.getModel(), true);
			break;
		}
	}
}
