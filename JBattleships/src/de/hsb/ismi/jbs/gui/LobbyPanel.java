/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class LobbyPanel extends JPanel {

	private JBSGUI parent;
	
	private JPanel header = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	
	private JButton btnCancel;
	private JButton btnStart;

	private PreGamePlayerPanel[] playerPanels;
	private JPanel otherPanel;
	private JLabel lblFieldSize;
	private JSpinner fieldSizeSpinner;
	private JCheckBox chckbxUseCannon;
	private JCheckBox chckbxUseNavalMines;
	private JBSButtonGroup btnGroup;
	
	/** The GameType of this Panel */
	private JBSGameType gameType;
	private ArrayList<JBSPlayer> players = new ArrayList<>(0);
	
	
	/**
	 * Create the panel.
	 */
	public LobbyPanel(JBSGUI parent, JBSGameType type, boolean isHost) {
		setOpaque(false);
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
		
		centerPanel.setOpaque(true);
		centerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		add(new AlphaContainer(centerPanel), BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		buttonPanel.setOpaque(false);
		centerPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(e -> {
				JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + LobbyPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
				parent.restorePrevContainer();
		});
		buttonPanel.add(btnCancel);
		
		btnStart = new JButton("Continue");
		btnStart.setActionCommand("continue");
		buttonPanel.add(btnStart);
		for(PreGamePlayerPanel pgpp : playerPanels){
			pgpp.setOpaque(false);
			playerPanel.add(pgpp);
		}
		
		playerPanel.setOpaque(false);
		playerPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		playerPanel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(new AlphaContainer(playerPanel), BorderLayout.CENTER);
		playerPanel.setLayout(new GridLayout(8, 1, 0, 0));
		otherPanel.setOpaque(false);
		otherPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
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
		chckbxUseNavalMines.setOpaque(false);
		GridBagConstraints gbc_chckbxUseNavalMines = new GridBagConstraints();
		gbc_chckbxUseNavalMines.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxUseNavalMines.gridx = 0;
		gbc_chckbxUseNavalMines.gridy = 1;
		otherPanel.add(chckbxUseNavalMines, gbc_chckbxUseNavalMines);
		
		chckbxUseCannon = new JCheckBox("Use Coastal Artillery?");
		chckbxUseCannon.setOpaque(false);
		GridBagConstraints gbc_chckbxUseCannon = new GridBagConstraints();
		gbc_chckbxUseCannon.weighty = 1.0;
		gbc_chckbxUseCannon.weightx = 1.0;
		gbc_chckbxUseCannon.gridx = 1;
		gbc_chckbxUseCannon.gridy = 1;
		otherPanel.add(chckbxUseCannon, gbc_chckbxUseCannon);
	}
	
	private class HostButtonPanel extends JPanel{
		
		private JButton btnCancel = new JButton();
		private JButton btnStart = new JButton();
		
		public HostButtonPanel(){
			initPanel();
		}
		
		private void initPanel(){
			setLayout(new FlowLayout());
			setOpaque(false);
			
			btnCancel.addActionListener(e -> {});
		}
		
		
	}

}
