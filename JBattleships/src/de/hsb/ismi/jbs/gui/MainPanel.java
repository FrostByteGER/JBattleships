/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class MainPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel buttonPanel;
	private JBSButton btnLocal;
	private JBSButton btnProfiles;
	private JBSButton btnOptions;
	private JBSButton btnCredits;
	private JBSButton btnQuit;
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
		
		btnLocal = new JBSButton("Local");
		btnLocal.setActionCommand("local");
		btnLocal.addActionListener(e -> {
			parent.swapContainer(new PreLocalGameChoicePanel(parent));
		});
		btnLocal.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnLocal = new GridBagConstraints();
		gbc_btnLocal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLocal.insets = new Insets(0, 0, 5, 0);
		gbc_btnLocal.gridx = 0;
		gbc_btnLocal.gridy = 0;
		
		buttonPanel.add(new AlphaContainer( btnLocal), gbc_btnLocal);
		
		rigidArea = Box.createRigidArea(new Dimension(20, 5));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 2;
		buttonPanel.add(rigidArea, gbc_rigidArea);
		
		btnProfiles = new JBSButton("Profiles");
		GridBagConstraints gbc_btnProfiles = new GridBagConstraints();
		gbc_btnProfiles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnProfiles.insets = new Insets(0, 0, 5, 0);
		gbc_btnProfiles.gridx = 0;
		gbc_btnProfiles.gridy = 3;
		buttonPanel.add(new AlphaContainer(btnProfiles) , gbc_btnProfiles);
		
		btnOptions = new JBSButton("Options");
		btnOptions.setActionCommand("options");
		btnOptions.addActionListener(e -> {
			parent.swapContainer(parent.getOptionsPanel());
		});
		GridBagConstraints gbc_btnOptions = new GridBagConstraints();
		gbc_btnOptions.insets = new Insets(0, 0, 5, 0);
		gbc_btnOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOptions.gridx = 0;
		gbc_btnOptions.gridy = 4;
		buttonPanel.add(new AlphaContainer(btnOptions) , gbc_btnOptions);
		
		btnCredits = new JBSButton("Credits");
		btnCredits.setActionCommand("credits");
		btnCredits.addActionListener(e -> {
			
		});
		GridBagConstraints gbc_btnCredits = new GridBagConstraints();
		gbc_btnCredits.insets = new Insets(0, 0, 5, 0);
		gbc_btnCredits.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCredits.gridx = 0;
		gbc_btnCredits.gridy = 5;
		buttonPanel.add(new AlphaContainer(btnCredits) , gbc_btnCredits);
		
		btnQuit = new JBSButton("Quit");
		btnQuit.setActionCommand("quit");
		btnQuit.addActionListener(e -> {
			Frame[] frames = Frame.getFrames();  
			for (Frame f:frames){
				f.dispose();
			}
			System.exit(0);
		});
		GridBagConstraints gbc_btnQuit = new GridBagConstraints();
		gbc_btnQuit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuit.gridx = 0;
		gbc_btnQuit.gridy = 6;
		buttonPanel.add(new AlphaContainer(btnQuit) , gbc_btnQuit);
		
		versionPanel = new JPanel();
		versionPanel.setOpaque(false);
		FlowLayout fl_versionPanel = (FlowLayout) versionPanel.getLayout();
		fl_versionPanel.setAlignment(FlowLayout.RIGHT);
		add(versionPanel, BorderLayout.SOUTH);
		
		versionLbl = new JLabel("ALPHA 0.2");
		versionPanel.add(versionLbl);
	}
}
