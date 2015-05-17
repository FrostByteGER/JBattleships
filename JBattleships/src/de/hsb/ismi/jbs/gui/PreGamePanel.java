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

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import de.hsb.ismi.jbs.core.JBSCore;

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
	private JTextField nameField01;
	private JCheckBox chckbxAI01;
	private JCheckBox chckbxActive01;
	private JTextField nameField02;
	private JCheckBox chckbxAI02;
	private JCheckBox chckbxActive02;
	private JTextField nameField03;
	private JCheckBox chckbxAI03;
	private JCheckBox chckbxActive03;
	private JTextField nameField04;
	private JCheckBox chckbxAI04;
	private JCheckBox chckbxActive04;
	private JTextField nameField05;
	private JCheckBox chckbxAI05;
	private JCheckBox chckbxActive05;
	private JTextField nameField06;
	private JCheckBox chckbxAI06;
	private JCheckBox chckbxActive06;
	private JTextField nameField07;
	private JCheckBox chckbxAI07;
	private JCheckBox chckbxActive07;
	private JTextField nameField08;
	private JCheckBox chckbxAI08;
	private JCheckBox chckbxActive08;
	private JLabel lblDestroyer;
	private JSpinner destroyerSpinner;
	private JLabel lblFrigate;
	private JSpinner frigateSpinner;
	private JLabel lblCorvette;
	private JSpinner spinner_2;
	private JLabel lblSubmarines;
	private JSpinner subSpinner;
	private JPanel otherPanel;
	private JLabel lblFieldSize;
	private JSpinner fieldSizeSpinner;
	private JCheckBox chckbxUseCannon;
	private JCheckBox chckbxUseNavalMines;
	
	
	/**
	 * Create the panel.
	 */
	public PreGamePanel(JBSGUI parent) {
		this.parent = parent;
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
					JBSCore.msgLogger.addMessage("Called Command: \"" + e.getActionCommand() + "\" on " + PreGamePanel.this.getClass());
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
		fieldSizeSpinner.setModel(new SpinnerNumberModel(new Integer(32), new Integer(0), null, new Integer(1)));
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
		playerPanel.setLayout(new GridLayout(0, 4, 20, 5));
		
		JLabel lblName01 = new JLabel("Name:");
		lblName01.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName01);
		
		nameField01 = new JTextField();
		playerPanel.add(nameField01);
		nameField01.setColumns(10);
		
		chckbxAI01 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI01);
		
		chckbxActive01 = new JCheckBox("Active");
		playerPanel.add(chckbxActive01);
		
		JLabel lblName02 = new JLabel("Name:");
		lblName02.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName02);
		
		nameField02 = new JTextField();
		nameField02.setColumns(10);
		playerPanel.add(nameField02);
		
		chckbxAI02 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI02);
		
		chckbxActive02 = new JCheckBox("Active");
		playerPanel.add(chckbxActive02);
		
		JLabel lblName03 = new JLabel("Name:");
		lblName03.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName03);
		
		nameField03 = new JTextField();
		nameField03.setColumns(10);
		playerPanel.add(nameField03);
		
		chckbxAI03 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI03);
		
		chckbxActive03 = new JCheckBox("Active");
		playerPanel.add(chckbxActive03);
		
		JLabel lblName04 = new JLabel("Name:");
		lblName04.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName04);
		
		nameField04 = new JTextField();
		nameField04.setColumns(10);
		playerPanel.add(nameField04);
		
		chckbxAI04 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI04);
		
		chckbxActive04 = new JCheckBox("Active");
		playerPanel.add(chckbxActive04);
		
		JLabel lblName05 = new JLabel("Name:");
		lblName05.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName05);
		
		nameField05 = new JTextField();
		nameField05.setColumns(10);
		playerPanel.add(nameField05);
		
		chckbxAI05 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI05);
		
		chckbxActive05 = new JCheckBox("Active");
		playerPanel.add(chckbxActive05);
		
		JLabel lblName06 = new JLabel("Name:");
		lblName06.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName06);
		
		nameField06 = new JTextField();
		nameField06.setColumns(10);
		playerPanel.add(nameField06);
		
		chckbxAI06 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI06);
		
		chckbxActive06 = new JCheckBox("Active");
		playerPanel.add(chckbxActive06);
		
		JLabel lblName07 = new JLabel("Name:");
		lblName07.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName07);
		
		nameField07 = new JTextField();
		nameField07.setColumns(10);
		playerPanel.add(nameField07);
		
		chckbxAI07 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI07);
		
		chckbxActive07 = new JCheckBox("Active");
		playerPanel.add(chckbxActive07);
		
		JLabel lblName08 = new JLabel("Name:");
		lblName08.setHorizontalAlignment(SwingConstants.TRAILING);
		playerPanel.add(lblName08);
		
		nameField08 = new JTextField();
		nameField08.setColumns(10);
		playerPanel.add(nameField08);
		
		chckbxAI08 = new JCheckBox("AI?");
		playerPanel.add(chckbxAI08);
		
		chckbxActive08 = new JCheckBox("Active");
		playerPanel.add(chckbxActive08);
		
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
		
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(3), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.weighty = 1.0;
		gbc_spinner_2.weightx = 1.0;
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 2;
		shipPanel.add(spinner_2, gbc_spinner_2);
		
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
