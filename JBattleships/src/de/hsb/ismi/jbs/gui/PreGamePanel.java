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
	private JLabel lblFieldSize;
	private JSpinner spinner;
	private JPanel panel;
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
		GridBagLayout gbl_mixed = new GridBagLayout();
		gbl_mixed.columnWidths = new int[]{219, 0};
		gbl_mixed.rowHeights = new int[]{81, 81, 81, 0};
		gbl_mixed.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_mixed.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mixed.setLayout(gbl_mixed);
		
		lblFieldSize = new JLabel("Field Size:");
		GridBagConstraints gbc_lblFieldSize = new GridBagConstraints();
		gbc_lblFieldSize.fill = GridBagConstraints.BOTH;
		gbc_lblFieldSize.insets = new Insets(0, 0, 5, 0);
		gbc_lblFieldSize.gridx = 0;
		gbc_lblFieldSize.gridy = 0;
		mixed.add(lblFieldSize, gbc_lblFieldSize);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 256, 1));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 0;
		gbc_spinner.gridy = 1;
		mixed.add(spinner, gbc_spinner);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Players", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		mixed.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblName01 = new JLabel("Name:");
		lblName01.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName01);
		
		nameField01 = new JTextField();
		panel.add(nameField01);
		nameField01.setColumns(10);
		
		chckbxAI01 = new JCheckBox("AI?");
		panel.add(chckbxAI01);
		
		chckbxActive01 = new JCheckBox("Active");
		panel.add(chckbxActive01);
		
		JLabel lblName02 = new JLabel("Name:");
		lblName02.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName02);
		
		nameField02 = new JTextField();
		nameField02.setColumns(10);
		panel.add(nameField02);
		
		chckbxAI02 = new JCheckBox("AI?");
		panel.add(chckbxAI02);
		
		chckbxActive02 = new JCheckBox("Active");
		panel.add(chckbxActive02);
		
		JLabel lblName03 = new JLabel("Name:");
		lblName03.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName03);
		
		nameField03 = new JTextField();
		nameField03.setColumns(10);
		panel.add(nameField03);
		
		chckbxAI03 = new JCheckBox("AI?");
		panel.add(chckbxAI03);
		
		chckbxActive03 = new JCheckBox("Active");
		panel.add(chckbxActive03);
		
		JLabel lblName04 = new JLabel("Name:");
		lblName04.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName04);
		
		nameField04 = new JTextField();
		nameField04.setColumns(10);
		panel.add(nameField04);
		
		chckbxAI04 = new JCheckBox("AI?");
		panel.add(chckbxAI04);
		
		chckbxActive04 = new JCheckBox("Active");
		panel.add(chckbxActive04);
		
		JLabel lblName05 = new JLabel("Name:");
		lblName05.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName05);
		
		nameField05 = new JTextField();
		nameField05.setColumns(10);
		panel.add(nameField05);
		
		chckbxAI05 = new JCheckBox("AI?");
		panel.add(chckbxAI05);
		
		chckbxActive05 = new JCheckBox("Active");
		panel.add(chckbxActive05);
		
		JLabel lblName06 = new JLabel("Name:");
		lblName06.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName06);
		
		nameField06 = new JTextField();
		nameField06.setColumns(10);
		panel.add(nameField06);
		
		chckbxAI06 = new JCheckBox("AI?");
		panel.add(chckbxAI06);
		
		chckbxActive06 = new JCheckBox("Active");
		panel.add(chckbxActive06);
		
		JLabel lblName07 = new JLabel("Name:");
		lblName07.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName07);
		
		nameField07 = new JTextField();
		nameField07.setColumns(10);
		panel.add(nameField07);
		
		chckbxAI07 = new JCheckBox("AI?");
		panel.add(chckbxAI07);
		
		chckbxActive07 = new JCheckBox("Active");
		panel.add(chckbxActive07);
		
		JLabel lblName08 = new JLabel("Name:");
		lblName08.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblName08);
		
		nameField08 = new JTextField();
		nameField08.setColumns(10);
		panel.add(nameField08);
		
		chckbxAI08 = new JCheckBox("AI?");
		panel.add(chckbxAI08);
		
		chckbxActive08 = new JCheckBox("Active");
		panel.add(chckbxActive08);
		
		shipPanel = new JPanel();
		shipPanel.setBorder(new TitledBorder(null, "Ship Settings:", TitledBorder.CENTER, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 18), null));
		settingsPanel.add(shipPanel);
		shipPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblDestroyer = new JLabel("Destroyers:");
		lblDestroyer.setFont(new Font("Tahoma", Font.PLAIN, 17));
		shipPanel.add(lblDestroyer);
		
		destroyerSpinner = new JSpinner();
		destroyerSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		shipPanel.add(destroyerSpinner);
		
		lblFrigate = new JLabel("Frigates:");
		lblFrigate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		shipPanel.add(lblFrigate);
		
		frigateSpinner = new JSpinner();
		frigateSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		shipPanel.add(frigateSpinner);
		
		lblCorvette = new JLabel("Corvettes:");
		lblCorvette.setFont(new Font("Tahoma", Font.PLAIN, 17));
		shipPanel.add(lblCorvette);
		
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		shipPanel.add(spinner_2);
		
		lblSubmarines = new JLabel("Submarines:");
		lblSubmarines.setFont(new Font("Tahoma", Font.PLAIN, 17));
		shipPanel.add(lblSubmarines);
		
		subSpinner = new JSpinner();
		subSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		shipPanel.add(subSpinner);
		
	}

}
