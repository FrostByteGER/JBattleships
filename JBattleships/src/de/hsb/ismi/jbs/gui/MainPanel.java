/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;

import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import de.hsb.ismi.jbs.engine.core.JBSGameType;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class MainPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel buttonPanel;
	private JButton btnLocal;
	private JButton btnOnline;
	private JButton btnProfiles;
	private JButton btnOptions;
	private JButton btnCredits;
	private JButton btnQuit;
	private Component rigidArea;
	private JPanel versionPanel;
	private JLabel versionLbl;

	/**
	 * 
	 */
	public MainPanel(JBSGUI parent) {
		this.parent = parent;
		this.setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		add(parent.generateHeader(), BorderLayout.NORTH);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		add(buttonPanel, BorderLayout.CENTER);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] {200};
		gbl_buttonPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		btnLocal = new JButton("Local");
		btnLocal.setActionCommand("local");
		btnLocal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("local")){
					
					parent.swapContainer(new PreGamePanel(parent, JBSGameType.GAME_LOCAL));
					
					/**
					JBSPlayer[] p = new JBSPlayer[1];
					p[0] = new JBSPlayer();
					
					JBSGameField[] fi = new JBSGameField[1];
					fi[0] = new JBSGameField(p[0],16);
					
					DataManager dm = new DataManager();
					
					JBSPlayer[] players = new JBSPlayer[2];
					
					players[0] = new JBSPlayer();
					
					JBSShip s = new JBSDestroyer(dm);
					
					s.setHealth(3);
					s.setCooldown(2);
					
					players[0].addShip(s);
					players[0].addShip(new JBSCorvette(dm));
					players[0].addShip(new JBSFrigate(dm));
					players[0].addShip(new JBSSubmarine(dm));
					players[0].addShip(new JBSDestroyer(dm));
					players[0].addShip(new JBSCorvette(dm));
					players[0].addShip(new JBSFrigate(dm));
					players[0].addShip(new JBSSubmarine(dm));
					players[0].addShip(new JBSDestroyer(dm));
					players[0].addShip(new JBSCorvette(dm));
					players[0].addShip(new JBSFrigate(dm));
					players[0].addShip(new JBSSubmarine(dm));
					
					Game game = new Game(JBSGameType.GAME_LAN, fi, players);
					
					parent.swapContainer(new GameFieldContainer(game));
					*/
				}
				
			}
		});
		btnLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnLocal = new GridBagConstraints();
		gbc_btnLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLocal.insets = new Insets(0, 0, 5, 0);
		gbc_btnLocal.gridx = 0;
		gbc_btnLocal.gridy = 0;
		buttonPanel.add(btnLocal, gbc_btnLocal);
		
		btnOnline = new JButton("Online");
		btnOnline.addActionListener(e -> {
			parent.swapContainer(new OnlinePanel(this.parent));
		});
		btnOnline.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnOnline = new GridBagConstraints();
		gbc_btnOnline.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOnline.insets = new Insets(0, 0, 5, 0);
		gbc_btnOnline.gridx = 0;
		gbc_btnOnline.gridy = 1;
		buttonPanel.add(btnOnline, gbc_btnOnline);
		
		rigidArea = Box.createRigidArea(new Dimension(20, 5));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 2;
		buttonPanel.add(rigidArea, gbc_rigidArea);
		
		btnProfiles = new JButton("Profiles");
		GridBagConstraints gbc_btnProfiles = new GridBagConstraints();
		gbc_btnProfiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnProfiles.insets = new Insets(0, 0, 5, 0);
		gbc_btnProfiles.gridx = 0;
		gbc_btnProfiles.gridy = 3;
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
		gbc_btnOptions.gridy = 4;
		buttonPanel.add(btnOptions, gbc_btnOptions);
		
		btnCredits = new JButton("Credits");
		GridBagConstraints gbc_btnCredits = new GridBagConstraints();
		gbc_btnCredits.insets = new Insets(0, 0, 5, 0);
		gbc_btnCredits.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCredits.gridx = 0;
		gbc_btnCredits.gridy = 5;
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
		gbc_btnQuit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuit.gridx = 0;
		gbc_btnQuit.gridy = 6;
		buttonPanel.add(btnQuit, gbc_btnQuit);
		
		versionPanel = new JPanel();
		versionPanel.setOpaque(false);
		FlowLayout fl_versionPanel = (FlowLayout) versionPanel.getLayout();
		fl_versionPanel.setAlignment(FlowLayout.RIGHT);
		add(versionPanel, BorderLayout.SOUTH);
		
		versionLbl = new JLabel("ALPHA 0.2");
		versionPanel.add(versionLbl);
	}

}
