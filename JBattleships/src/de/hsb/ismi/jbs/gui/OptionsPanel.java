/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;


import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.DisplayMode;
import java.awt.GridLayout;


import javax.swing.JLabel;


import java.awt.Component;


import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


import javax.swing.JRadioButton;


import java.awt.Dimension;
import java.net.Inet4Address;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.JSlider;


import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;


import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OptionsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5051718464952511210L;
	private JPanel centerPanel;
	private JPanel gfxPanel;
	private JPanel sfxPanel;
	private JPanel otherPanel;
	private JPanel buttonPanel;
	private JPanel networkPanel;
	private JPanel mixedPanel;
	private JPanel gamePanel;

	private JBSButton resetButton;
	private JBSButton saveButton;
	private JBSButton backButton;
	private JLabel lblRes;
	private JComboBox<Resolution> resBox;
	private JLabel lblMode;
	private JRadioButton rdbtnFull;
	private JRadioButton rdbtnWin;
	private JRadioButton rdbtnLess;
	private final ButtonGroup buttonGroup;
	private JLabel lblVolume;
	private JSlider sliderSound;
	private JSlider sliderMusic;
	private JLabel lblMusic;
	private JLabel lblIP;
	private JComboBox<Inet4Address> ipBox;
	private JLabel lblPort;
	private JTextField portField;
	private JLabel lblLang;
	private JComboBox<String> langBox;
	private JCheckBox chckbxDebug;
	
	/**
	 * 
	 */
	public OptionsPanel(JBSGUI parent) {
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		add(parent.generateHeader(), BorderLayout.NORTH);
		centerPanel = new JPanel();
		centerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		add(new AlphaContainer(centerPanel), BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(2, 0, 0, 0));
		
		buttonGroup = new ButtonGroup();
		gfxPanel = new JPanel();
		gfxPanel.setOpaque(false);
		centerPanel.add(gfxPanel);
		gfxPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), JBattleships.game.getLocalization("GAME_TITLE_GRAPHICS_SETTINGS"), TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.MAIN_FONT, new Color(0, 0, 0)));
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
		
		lblRes = new JLabel(JBattleships.game.getLocalization("GAME_RESOLUTION"));
		lblRes.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblRes = new GridBagConstraints();
		gbc_lblRes.insets = new Insets(0, 0, 5, 0);
		gbc_lblRes.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRes.gridx = 0;
		gbc_lblRes.gridy = 1;
		gfxPanel.add(lblRes, gbc_lblRes);
		
				
		DisplayMode[] modes = JBSCoreGame.screenDeviceManager.getSupportedDisplayModes(new int[]{JBSCoreGame.screenDeviceManager.getCurrentDisplayMode().getRefreshRate()});
		Resolution[] res = new Resolution[modes.length];
		for(int i = 0; i < modes.length; i++){
			res[i] = Resolution.convertDisplayModeToResolution(modes[i]);
		}
		int index = -1;
		for(int j = 0; j < res.length; j++){
			if(res[j].equals(JBattleships.game.getCurrentResolution())){
				index = j;
			}
		}
		
		resBox = new JComboBox<Resolution>(res);
		resBox.setSelectedIndex(index);
		resBox.setFont(JBSGUI.MAIN_FONT);
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
		
		lblMode = new JLabel(JBattleships.game.getLocalization("GAME_GRAPHICS_MODE"));
		lblMode.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMode = new GridBagConstraints();
		gbc_lblMode.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMode.insets = new Insets(0, 0, 5, 0);
		gbc_lblMode.gridx = 0;
		gbc_lblMode.gridy = 4;
		gfxPanel.add(lblMode, gbc_lblMode);
		
		rdbtnFull = new JRadioButton(JBattleships.game.getLocalization("GAME_FULLSCREEN"));
		rdbtnFull.setFont(JBSGUI.MAIN_FONT);
		rdbtnFull.setOpaque(false);
		buttonGroup.add(rdbtnFull);
		GridBagConstraints gbc_rdbtnFull = new GridBagConstraints();
		gbc_rdbtnFull.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnFull.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFull.gridx = 0;
		gbc_rdbtnFull.gridy = 5;
		gfxPanel.add(rdbtnFull, gbc_rdbtnFull);
		
		rdbtnWin = new JRadioButton(JBattleships.game.getLocalization("GAME_WINDOWED"));
		rdbtnWin.setFont(JBSGUI.MAIN_FONT);
		rdbtnWin.setOpaque(false);
		buttonGroup.add(rdbtnWin);
		GridBagConstraints gbc_rdbtnWin = new GridBagConstraints();
		gbc_rdbtnWin.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnWin.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnWin.gridx = 0;
		gbc_rdbtnWin.gridy = 6;
		gfxPanel.add(rdbtnWin, gbc_rdbtnWin);
		
		rdbtnLess = new JRadioButton(JBattleships.game.getLocalization("GAME_BORDERLESS"));
		rdbtnLess.setFont(JBSGUI.MAIN_FONT);
		rdbtnLess.setOpaque(false);
		buttonGroup.add(rdbtnLess);
		GridBagConstraints gbc_rdbtnLess = new GridBagConstraints();
		gbc_rdbtnLess.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnLess.gridx = 0;
		gbc_rdbtnLess.gridy = 7;
		gfxPanel.add(rdbtnLess, gbc_rdbtnLess);
		
		sfxPanel = new JPanel();
		sfxPanel.setOpaque(false);
		sfxPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), JBattleships.game.getLocalization("GAME_TITLE_AUDIO_SETTINGS"), TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.MAIN_FONT, new Color(0, 0, 0)));
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
		
		lblVolume = new JLabel(JBattleships.game.getLocalization("GAME_VOLUME"));
		lblVolume.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.insets = new Insets(0, 0, 5, 0);
		gbc_lblVolume.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblVolume.gridx = 0;
		gbc_lblVolume.gridy = 1;
		sfxPanel.add(lblVolume, gbc_lblVolume);
		
		sliderSound = new JSlider();
		sliderSound.setValue(JBattleships.game.getSoundVolume());
		sliderSound.setFont(JBSGUI.MAIN_FONT);
		sliderSound.setPaintTicks(true);
		@SuppressWarnings("unchecked")
		Hashtable<Integer, JComponent> volumeTable = sliderSound.createStandardLabels(50);
		JLabel lblSoundValue = new JLabel(Integer.toString(JBattleships.game.getSoundVolume()));
		lblSoundValue.setFont(JBSGUI.MAIN_FONT);
		volumeTable.replace(50, lblSoundValue);
		sliderSound.setLabelTable(volumeTable);
		sliderSound.addChangeListener(e -> {
			lblSoundValue.setText(Integer.toString(sliderSound.getValue()));
			JBattleships.game.setSoundVolume(sliderSound.getValue());
			lblSoundValue.updateUI();
		});
		sliderSound.setPaintLabels(true);
		sliderSound.setOpaque(false);
		GridBagConstraints gbc_sliderVolume = new GridBagConstraints();
		gbc_sliderVolume.insets = new Insets(0, 0, 5, 0);
		gbc_sliderVolume.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderVolume.gridx = 0;
		gbc_sliderVolume.gridy = 2;
		sfxPanel.add(sliderSound, gbc_sliderVolume);
		
		GridBagConstraints gbc_rigidArea_3 = new GridBagConstraints();
		gbc_rigidArea_3.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_3.fill = GridBagConstraints.BOTH;
		gbc_rigidArea_3.gridx = 0;
		gbc_rigidArea_3.gridy = 3;
		sfxPanel.add(Box.createRigidArea(new Dimension(20, 20)), gbc_rigidArea_3);
		
		lblMusic = new JLabel(JBattleships.game.getLocalization("GAME_MUSIC"));
		lblMusic.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblMusic = new GridBagConstraints();
		gbc_lblMusic.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMusic.insets = new Insets(0, 0, 5, 0);
		gbc_lblMusic.gridx = 0;
		gbc_lblMusic.gridy = 4;
		sfxPanel.add(lblMusic, gbc_lblMusic);
		
		sliderMusic = new JSlider();
		sliderMusic.setFont(JBSGUI.MAIN_FONT);
		sliderMusic.setValue(JBattleships.game.getMusicVolume());
		@SuppressWarnings("unchecked")
		Hashtable<Integer, JComponent> musicTable = sliderMusic.createStandardLabels(50);
		JLabel lblMusicValue = new JLabel(Integer.toString(JBattleships.game.getMusicVolume()));
		lblMusicValue.setFont(JBSGUI.MAIN_FONT);
		musicTable.replace(50, lblMusicValue);
		sliderMusic.setLabelTable(musicTable);
		sliderMusic.addChangeListener(e -> {
			lblMusicValue.setText(Integer.toString(sliderMusic.getValue()));
			JBattleships.game.setMusicVolume(sliderMusic.getValue());
			lblMusicValue.updateUI();
		});
		sliderMusic.setPaintTicks(true);
		sliderMusic.setPaintLabels(true);
		sliderMusic.setOpaque(false);
		GridBagConstraints gbc_sliderMusic = new GridBagConstraints();
		gbc_sliderMusic.fill = GridBagConstraints.BOTH;
		gbc_sliderMusic.gridx = 0;
		gbc_sliderMusic.gridy = 5;
		sfxPanel.add(sliderMusic, gbc_sliderMusic);
		
		otherPanel = new JPanel();
		otherPanel.setOpaque(false);
		otherPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), JBattleships.game.getLocalization("GAME_TITLE_NETWORK_SETTINGS"), TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.MAIN_FONT, new Color(0, 0, 0)));
		centerPanel.add(otherPanel);
		otherPanel.setLayout(new BorderLayout(0, 0));
		
		networkPanel = new JPanel();
		networkPanel.setOpaque(false);
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
		
		lblIP = new JLabel(JBattleships.game.getLocalization("GAME_IP"));
		lblIP.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblIP = new GridBagConstraints();
		gbc_lblIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblIP.insets = new Insets(0, 0, 5, 0);
		gbc_lblIP.gridx = 0;
		gbc_lblIP.gridy = 1;
		networkPanel.add(lblIP, gbc_lblIP);
		
		ipBox = new JComboBox<Inet4Address>(new Inet4Address[]{(Inet4Address) Inet4Address.getLoopbackAddress()});
		ipBox.setFont(JBSGUI.MAIN_FONT);
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
		
		lblPort = new JLabel(JBattleships.game.getLocalization("GAME_PORT"));
		lblPort.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_lblPort = new GridBagConstraints();
		gbc_lblPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPort.insets = new Insets(0, 0, 5, 0);
		gbc_lblPort.gridx = 0;
		gbc_lblPort.gridy = 4;
		networkPanel.add(lblPort, gbc_lblPort);
		
		portField = new JTextField();
		portField.setFont(JBSGUI.MAIN_FONT);
		GridBagConstraints gbc_portField = new GridBagConstraints();
		gbc_portField.insets = new Insets(0, 0, 5, 0);
		gbc_portField.fill = GridBagConstraints.HORIZONTAL;
		gbc_portField.gridx = 0;
		gbc_portField.gridy = 5;
		networkPanel.add(portField, gbc_portField);
		portField.setColumns(10);
		
		mixedPanel = new JPanel();
		mixedPanel.setOpaque(false);
		centerPanel.add(mixedPanel);
		mixedPanel.setLayout(new BoxLayout(mixedPanel, BoxLayout.Y_AXIS));
		
		gamePanel = new JPanel();
		gamePanel.setOpaque(false);
		gamePanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_GAME_SETTINGS"), TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		mixedPanel.add(gamePanel);
		GridBagLayout gbl_gamePanel = new GridBagLayout();
		gbl_gamePanel.columnWidths = new int[]{0};
		gbl_gamePanel.rowHeights = new int[] {0, 0, 0, 0, 0};
		gbl_gamePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_gamePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gamePanel.setLayout(gbl_gamePanel);
		
		lblLang = new JLabel(JBattleships.game.getLocalization("GAME_LANGUAGE"));
		lblLang.setFont(JBSGUI.MAIN_FONT);
		lblLang.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblLang = new GridBagConstraints();
		gbc_lblLang.anchor = GridBagConstraints.WEST;
		gbc_lblLang.insets = new Insets(0, 0, 5, 0);
		gbc_lblLang.gridx = 0;
		gbc_lblLang.gridy = 0;
		gamePanel.add(lblLang, gbc_lblLang);
		
		
		String[] langs = JBattleships.game.getDataManager().getLocalizationManager().getLanguageTable();
		String[] localizedLangs = new String[langs.length];
		String active = "";
		for(int i = 0; i < langs.length; i++){
			if(langs[i].equals(JBattleships.game.getDataManager().getLocalizationManager().getActiveLanguage())){
				active = JBattleships.game.getLocalization("GAME_LANGUAGE_" + langs[i].toUpperCase());
			}
			localizedLangs[i] = JBattleships.game.getLocalization("GAME_LANGUAGE_" + langs[i].toUpperCase());
		}
		langBox = new JComboBox<String>(localizedLangs);
		langBox.setFont(JBSGUI.MAIN_FONT);
		langBox.setSelectedItem(active);
		GridBagConstraints gbc_langBox = new GridBagConstraints();
		gbc_langBox.insets = new Insets(0, 0, 5, 0);
		gbc_langBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_langBox.gridx = 0;
		gbc_langBox.gridy = 1;
		gamePanel.add(langBox, gbc_langBox);
		
		chckbxDebug = new JCheckBox(JBattleships.game.getLocalization("GAME_DEBUG_MODE"));
		chckbxDebug.setFont(JBSGUI.MAIN_FONT);
		chckbxDebug.setOpaque(false);
		GridBagConstraints gbc_chckbxDebug = new GridBagConstraints();
		gbc_chckbxDebug.anchor = GridBagConstraints.WEST;
		gbc_chckbxDebug.gridx = 0;
		gbc_chckbxDebug.gridy = 2;
		gamePanel.add(chckbxDebug, gbc_chckbxDebug);
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		mixedPanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		resetButton = new JBSButton(JBattleships.game.getLocalization("GAME_RESET"));
		resetButton.addActionListener(e -> {
			DebugLog.logInfo("Called Command: \"" + e.getActionCommand() + "\" on " + OptionsPanel.this.getClass());
		});
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(new AlphaContainer(resetButton));
		
		saveButton = new JBSButton(JBattleships.game.getLocalization("GAME_SAVE_SETTINGS"));
		saveButton.addActionListener(e -> {
					DebugLog.logInfo("Called Command: \"" + e.getActionCommand() + "\" on " + OptionsPanel.this.getClass());
					Resolution r = (Resolution) resBox.getSelectedItem();
					
					// If a custom resolution was set in the Settings.cfg, the resBox won't possibly have a match, this prevents a NullPointerException!
					if(r == null){
						r = Resolution.convertDisplayModeToResolution(JBSCoreGame.screenDeviceManager.getCurrentDisplayMode());
						resBox.setSelectedItem(r); // Prevents that the JComboBox selection is still null!
					}
					
					JBattleships.game.changeResolution(r);
					
					if(rdbtnFull.isSelected()){
						JBattleships.game.changeScreenMode(ScreenMode.MODE_FULLSCREEN);
						r = Resolution.convertDisplayModeToResolution(JBSCoreGame.screenDeviceManager.getCurrentDisplayMode());
						JBattleships.game.changeResolution(r);
						resBox.setSelectedItem(r);
					}else if(rdbtnWin.isSelected()){
						JBattleships.game.changeScreenMode(ScreenMode.MODE_WINDOWED);
					}else if(rdbtnLess.isSelected()){
						JBattleships.game.changeScreenMode(ScreenMode.MODE_BORDERLESS);
					}
					String[] languages = JBattleships.game.getDataManager().getLocalizationManager().getLanguageTable();
					JBattleships.game.changeLanguage(languages[langBox.getSelectedIndex()]);
					boolean check = JBattleships.game.saveOptions();
					parent.getMainFrame().dispose();
					JBattleships.game.initGameGUI();
					if(check){
						DebugLog.logInfo("Saving options was successful.");
					}else{
						DebugLog.logWarning("Error saving options.");
					}
					// Sets a timer to show the OptionsPanel after the GUI has been recreated. Otherwise, the swapContainer command will be ignored due to threading issues.
					Timer t = new Timer();
					t.schedule(new TimerTask() {
						@Override
						public void run() {
							JBattleships.game.getMainGUI().swapContainer(JBattleships.game.getMainGUI().getOptionsPanel());
						}
					}, 150);
		});
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(new AlphaContainer(saveButton) );
		
		backButton = new JBSButton(JBattleships.game.getLocalization("GAME_BACK"));
		backButton.setActionCommand("back");
		backButton.addActionListener(e -> {
			DebugLog.logInfo("Called Command: \"" + e.getActionCommand() + "\" on " + OptionsPanel.this.getClass());
			parent.restoreRootContainer(true);
		});
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(new AlphaContainer(backButton));
		
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
