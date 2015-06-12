/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JSpinner;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.Color;
import java.awt.Font;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGamePanel extends JPanel {

	private JBSGUI parent;
	private JPanel header;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JButton btnCancel;
	private JButton btnContinue;
	private JPanel settingsPanel;
	private JPanel mixed;
	private JPanel shipPanel;
	private JPanel playerPanel;
	private PreGamePlayerPanel[] playerPanels;
	private JLabel lblDestroyer;
	private JSpinner destroyerSpinner;
	private JLabel lblFrigate;
	private JSpinner frigateSpinner;
	private JLabel lblCorvette;
	private JSpinner corvetteSpinner;
	private JLabel lblSubmarines;
	private JSpinner subSpinner;
	private JPanel otherPanel;
	private JLabel lblFieldSize;
	private JSpinner fieldSizeSpinner;
	private JCheckBox chckbxUseCannon;
	private JCheckBox chckbxUseNavalMines;
	private JBSButtonGroup btnGroup;
	
	/** The GameType of this Panel */
	private JBSGameType gameType;
	
	
	/**
	 * Create the panel.
	 */
	public PreGamePanel(JBSGUI parent, JBSGameType type) {
		btnGroup = new JBSButtonGroup();
		playerPanels = new PreGamePlayerPanel[8];
		for(int i = 0;i < playerPanels.length;i++){
			PreGamePlayerPanel pp = new PreGamePlayerPanel();
			pp.setName("Player" + " #" + (i + 1));
			btnGroup.add(pp.getCheckboxActive());
			playerPanels[i] = pp;
		}
		playerPanels[0].setActiveSelected(true);
		playerPanels[1].setActiveSelected(true);
		
		this.parent = parent;
		gameType = type;
		header = parent.generateHeader();
		setLayout(new BorderLayout(0, 0));
		add(header, BorderLayout.NORTH);
		
		
		
		centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Main Settings", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 25), new Color(0, 0, 0)));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		buttonPanel = new JPanel();
		centerPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("cancel")){
					JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + PreGamePanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
					PreGamePanel.this.parent.swapContainer(PreGamePanel.this.parent.getMainPanel());
				}
			}
		});
		buttonPanel.add(btnCancel);
		
		btnContinue = new JButton("Continue");
		btnContinue.setActionCommand("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("continue")){
					JBattleships.game.generateGame();
					GameManager gm = JBattleships.game.getGameManager();
					for(PreGamePlayerPanel pregpp : playerPanels){
						//TODO: add various checks!
						boolean active = pregpp.isActiveSelected();
						if(active){
							boolean ai = pregpp.isAISelected();
							String name = pregpp.getName();
							if(ai){
								gm.addPlayer(new JBSAIPlayer(name));
							}else{
								gm.addPlayer(new JBSPlayer(name));
							}
						}
					}
					int dc = 1;
					//destroyerSpinner.commitEdit(); TODO: Discard?
					try{
						dc = ((Integer)destroyerSpinner.getValue());
					}catch(ClassCastException cce){
						cce.printStackTrace();
					}
					gm.setDestroyerCount(dc);
					
					int fc = 1;
					try{
						fc = ((Integer)frigateSpinner.getValue());
					}catch(ClassCastException cce){
						cce.printStackTrace();
					}
					gm.setFrigateCount(fc);
					
					int cc = 1;
					try{
						cc = ((Integer)corvetteSpinner.getValue());
					}catch(ClassCastException cce){
						cce.printStackTrace();
					}
					gm.setCorvetteCount(cc);
					
					int sc = 1;
					try{
						sc = ((Integer)subSpinner.getValue());
					}catch(ClassCastException cce){
						cce.printStackTrace();
					}
					gm.setSubmarineCount(sc);
					
					int fs = 16;
					try{
						fs = ((Integer)fieldSizeSpinner.getValue());
					}catch(ClassCastException cce){
						cce.printStackTrace();
					}
					gm.createGame(gameType, fs);
					JBSCoreGame.ioQueue.insertInput("Created Game!", JBSCoreGame.MSG_LOGGER_KEY);
					JBSCoreGame.ioQueue.insertInput(gm.toString(), JBSCoreGame.MSG_LOGGER_KEY);
					
					parent.swapContainer(new PreGamePlacingPanel(PreGamePanel.this.parent));
					//parent.swapContainer(new ColorPickerPanel());
				}
			}
		});
		buttonPanel.add(btnContinue);
		
		settingsPanel = new JPanel();
		centerPanel.add(settingsPanel, BorderLayout.CENTER);
		settingsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		mixed = new JPanel();
		mixed.setBorder(new TitledBorder(null, "Other:", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 18), null));
		settingsPanel.add(mixed);
		mixed.setLayout(new GridLayout(0, 1, 0, 0));
		
		otherPanel = new JPanel();
		mixed.add(otherPanel);
		GridBagLayout gbl_otherPanel = new GridBagLayout();
		gbl_otherPanel.columnWidths = new int[]{0, 0, 0};
		gbl_otherPanel.rowHeights = new int[]{0, 0, 0};
		gbl_otherPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_otherPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		otherPanel.setLayout(gbl_otherPanel);
		
		lblFieldSize = new JLabel("Field Size:");
		lblFieldSize.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblFieldSize = new GridBagConstraints();
		gbc_lblFieldSize.fill = GridBagConstraints.BOTH;
		gbc_lblFieldSize.weighty = 1.0;
		gbc_lblFieldSize.weightx = 1.0;
		gbc_lblFieldSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblFieldSize.gridx = 0;
		gbc_lblFieldSize.gridy = 0;
		otherPanel.add(lblFieldSize, gbc_lblFieldSize);
		
		fieldSizeSpinner = new JSpinner();
		fieldSizeSpinner.setModel(new SpinnerNumberModel(new Integer(16), new Integer(10), new Integer(20), new Integer(1)));
		GridBagConstraints gbc_fieldSizeSpinner = new GridBagConstraints();
		gbc_fieldSizeSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_fieldSizeSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldSizeSpinner.weighty = 1.0;
		gbc_fieldSizeSpinner.weightx = 1.0;
		gbc_fieldSizeSpinner.gridx = 1;
		gbc_fieldSizeSpinner.gridy = 0;
		otherPanel.add(fieldSizeSpinner, gbc_fieldSizeSpinner);
		
		chckbxUseNavalMines = new JCheckBox("Use Naval Mines?");
		GridBagConstraints gbc_chckbxUseNavalMines = new GridBagConstraints();
		gbc_chckbxUseNavalMines.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxUseNavalMines.gridx = 0;
		gbc_chckbxUseNavalMines.gridy = 1;
		otherPanel.add(chckbxUseNavalMines, gbc_chckbxUseNavalMines);
		
		chckbxUseCannon = new JCheckBox("Use Coastal Artillery?");
		GridBagConstraints gbc_chckbxUseCannon = new GridBagConstraints();
		gbc_chckbxUseCannon.weighty = 1.0;
		gbc_chckbxUseCannon.weightx = 1.0;
		gbc_chckbxUseCannon.gridx = 1;
		gbc_chckbxUseCannon.gridy = 1;
		otherPanel.add(chckbxUseCannon, gbc_chckbxUseCannon);
		
		playerPanel = new JPanel();
		playerPanel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mixed.add(playerPanel);
		playerPanel.setLayout(new GridLayout(8, 1, 0, 0));
		for(PreGamePlayerPanel pgpp : playerPanels){
			playerPanel.add(pgpp);
		}
		
		shipPanel = new JPanel();
		shipPanel.setBorder(new TitledBorder(null, "Ship Settings:", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 18), null));
		settingsPanel.add(shipPanel);
		GridBagLayout gbl_shipPanel = new GridBagLayout();
		gbl_shipPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_shipPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		shipPanel.setLayout(gbl_shipPanel);
		
		lblDestroyer = new JLabel("Destroyers:");
		lblDestroyer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblDestroyer = new GridBagConstraints();
		gbc_lblDestroyer.weighty = 1.0;
		gbc_lblDestroyer.weightx = 1.0;
		gbc_lblDestroyer.fill = GridBagConstraints.BOTH;
		gbc_lblDestroyer.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestroyer.gridx = 0;
		gbc_lblDestroyer.gridy = 0;
		shipPanel.add(lblDestroyer, gbc_lblDestroyer);
		
		destroyerSpinner = new JSpinner();
		destroyerSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_destroyerSpinner = new GridBagConstraints();
		gbc_destroyerSpinner.weighty = 1.0;
		gbc_destroyerSpinner.weightx = 1.0;
		gbc_destroyerSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_destroyerSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_destroyerSpinner.gridx = 1;
		gbc_destroyerSpinner.gridy = 0;
		shipPanel.add(destroyerSpinner, gbc_destroyerSpinner);
		
		lblFrigate = new JLabel("Frigates:");
		lblFrigate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblFrigate = new GridBagConstraints();
		gbc_lblFrigate.weighty = 1.0;
		gbc_lblFrigate.weightx = 1.0;
		gbc_lblFrigate.fill = GridBagConstraints.BOTH;
		gbc_lblFrigate.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrigate.gridx = 0;
		gbc_lblFrigate.gridy = 1;
		shipPanel.add(lblFrigate, gbc_lblFrigate);
		
		frigateSpinner = new JSpinner();
		frigateSpinner.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_frigateSpinner = new GridBagConstraints();
		gbc_frigateSpinner.weighty = 1.0;
		gbc_frigateSpinner.weightx = 1.0;
		gbc_frigateSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_frigateSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_frigateSpinner.gridx = 1;
		gbc_frigateSpinner.gridy = 1;
		shipPanel.add(frigateSpinner, gbc_frigateSpinner);
		
		lblCorvette = new JLabel("Corvettes:");
		lblCorvette.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblCorvette = new GridBagConstraints();
		gbc_lblCorvette.weighty = 1.0;
		gbc_lblCorvette.weightx = 1.0;
		gbc_lblCorvette.fill = GridBagConstraints.BOTH;
		gbc_lblCorvette.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorvette.gridx = 0;
		gbc_lblCorvette.gridy = 2;
		shipPanel.add(lblCorvette, gbc_lblCorvette);
		
		corvetteSpinner = new JSpinner();
		corvetteSpinner.setModel(new SpinnerNumberModel(new Integer(3), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.weighty = 1.0;
		gbc_spinner_2.weightx = 1.0;
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 2;
		shipPanel.add(corvetteSpinner, gbc_spinner_2);
		
		lblSubmarines = new JLabel("Submarines:");
		lblSubmarines.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblSubmarines = new GridBagConstraints();
		gbc_lblSubmarines.weighty = 1.0;
		gbc_lblSubmarines.weightx = 1.0;
		gbc_lblSubmarines.fill = GridBagConstraints.BOTH;
		gbc_lblSubmarines.insets = new Insets(0, 0, 0, 5);
		gbc_lblSubmarines.gridx = 0;
		gbc_lblSubmarines.gridy = 3;
		shipPanel.add(lblSubmarines, gbc_lblSubmarines);
		
		subSpinner = new JSpinner();
		subSpinner.setModel(new SpinnerNumberModel(new Integer(4), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_subSpinner = new GridBagConstraints();
		gbc_subSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_subSpinner.weighty = 1.0;
		gbc_subSpinner.weightx = 1.0;
		gbc_subSpinner.gridx = 1;
		gbc_subSpinner.gridy = 3;
		shipPanel.add(subSpinner, gbc_subSpinner);
		
	}

}
