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
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSOptionsPanel extends JPanel implements ActionListener {
	
	private JPanel headerPanel;
	private JLabel lblJbattleships;
	private JPanel centerPanel;
	private JPanel gfxPanel;
	private JPanel sfxPanel;
	private JPanel otherPanel;
	private JPanel panel;
	private JButton resetButton;
	private JButton saveButton;
	private JButton backButton;
	
	/**
	 * 
	 */
	public JBSOptionsPanel() {
		setLayout(new BorderLayout(0, 0));
		
		headerPanel = new JPanel();
		add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		
		headerPanel.add(Box.createVerticalStrut(20));
		
		lblJbattleships = new JLabel("JBattleships");
		lblJbattleships.setAlignmentX(Component.CENTER_ALIGNMENT);
		headerPanel.add(lblJbattleships);
		
		headerPanel.add(Box.createVerticalStrut(20));
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		gfxPanel = new JPanel();
		centerPanel.add(gfxPanel);
		gfxPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graphics", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sfxPanel = new JPanel();
		sfxPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Audio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		centerPanel.add(sfxPanel);
		
		otherPanel = new JPanel();
		otherPanel.setBorder(new TitledBorder(null, "Other", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		centerPanel.add(otherPanel);
		otherPanel.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		otherPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		resetButton = new JButton("Reset to Default");
		resetButton.addActionListener(this);
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(resetButton);
		
		saveButton = new JButton("Save Settings");
		saveButton.addActionListener(this);
		saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(saveButton);
		
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(backButton);
	}

	
	public void actionPerformed(ActionEvent e) {
		
	}
}
