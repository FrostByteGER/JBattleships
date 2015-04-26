/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.swing.border.TitledBorder;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class MainPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel buttonPanel;
	private JButton btnLocal;
	private JButton btnLAN;
	private JButton btnOnline;
	private JButton btnProfiles;
	private JButton btnOptions;
	private JButton btnCredits;
	private JButton btnQuit;
	private Component rigidArea;
	private JPanel versionPanel;
	private JLabel versionLbl;
	
	
	private BufferedImage bg;

	/**
	 * 
	 */
	public MainPanel(JBSGUI parent) {
		this.parent = parent;
		setLayout(new BorderLayout(0, 0));
		add(parent.generateHeader(), BorderLayout.NORTH);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		add(buttonPanel, BorderLayout.CENTER);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {200};
		gbl_buttonPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		btnLocal = new JButton("Local");
		btnLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnLocal = new GridBagConstraints();
		gbc_btnLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLocal.insets = new Insets(0, 0, 5, 0);
		gbc_btnLocal.gridx = 0;
		gbc_btnLocal.gridy = 0;
		buttonPanel.add(btnLocal, gbc_btnLocal);
		
		btnLAN = new JButton("LAN");
		btnLAN.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnLAN = new GridBagConstraints();
		gbc_btnLAN.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLAN.insets = new Insets(0, 0, 5, 0);
		gbc_btnLAN.gridx = 0;
		gbc_btnLAN.gridy = 1;
		buttonPanel.add(btnLAN, gbc_btnLAN);
		
		btnOnline = new JButton("Online");
		btnOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnOnline = new GridBagConstraints();
		gbc_btnOnline.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOnline.insets = new Insets(0, 0, 5, 0);
		gbc_btnOnline.gridx = 0;
		gbc_btnOnline.gridy = 2;
		buttonPanel.add(btnOnline, gbc_btnOnline);
		
		rigidArea = Box.createRigidArea(new Dimension(20, 5));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 3;
		buttonPanel.add(rigidArea, gbc_rigidArea);
		
		btnProfiles = new JButton("Profiles");
		GridBagConstraints gbc_btnProfiles = new GridBagConstraints();
		gbc_btnProfiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnProfiles.insets = new Insets(0, 0, 5, 0);
		gbc_btnProfiles.gridx = 0;
		gbc_btnProfiles.gridy = 4;
		buttonPanel.add(btnProfiles, gbc_btnProfiles);
		
		btnOptions = new JButton("Options");
		btnOptions.setActionCommand("options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("options")){
					parent.swapContainer(parent.getOptionsPanel());
				}
			}
		});
		GridBagConstraints gbc_btnOptions = new GridBagConstraints();
		gbc_btnOptions.insets = new Insets(0, 0, 5, 0);
		gbc_btnOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOptions.gridx = 0;
		gbc_btnOptions.gridy = 5;
		buttonPanel.add(btnOptions, gbc_btnOptions);
		
		btnCredits = new JButton("Credits");
		GridBagConstraints gbc_btnCredits = new GridBagConstraints();
		gbc_btnCredits.insets = new Insets(0, 0, 5, 0);
		gbc_btnCredits.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCredits.gridx = 0;
		gbc_btnCredits.gridy = 6;
		buttonPanel.add(btnCredits, gbc_btnCredits);
		
		btnQuit = new JButton("Quit");
		btnQuit.setActionCommand("quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("quit")){
					Frame[] frames = Frame.getFrames();  
			        for (Frame f:frames){
			        	f.dispose();
			        }  
			        System.exit(0);
				}
			}
		});
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.insets = new Insets(0, 0, 5, 0);
		gbc_btnQuit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuit.gridx = 0;
		gbc_btnQuit.gridy = 7;
		buttonPanel.add(btnQuit, gbc_btnQuit);
		
		versionPanel = new JPanel();
		versionPanel.setOpaque(false);
		FlowLayout fl_versionPanel = (FlowLayout) versionPanel.getLayout();
		fl_versionPanel.setAlignment(FlowLayout.RIGHT);
		add(versionPanel, BorderLayout.SOUTH);
		
		versionLbl = new JLabel("PRE-ALPHA");
		versionPanel.add(versionLbl);
		
		try {
			bg = ImageIO.read(new File("Data/Textures/jbs_background.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this.getWidth(),this.getHeight(), null);
	}

}
