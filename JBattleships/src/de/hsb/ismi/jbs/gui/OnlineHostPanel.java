/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;

import de.hsb.ismi.jbs.engine.core.JBSGameType;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlineHostPanel extends JPanel {
	
	JBSGUI parent;
	private JButton btnCancel;
	private JButton btnCreateLobby;
	private JPanel panel;
	private LobbyPanel centerLobby;

	/**
	 * Create the panel.
	 */
	public OnlineHostPanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();
	}
	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		add(parent.generateHeader(), BorderLayout.NORTH);
		setOpaque(false);
		
		centerLobby = new LobbyPanel(parent, JBSGameType.GAME_ONLINE, true);
		add(centerLobby, BorderLayout.CENTER);
		
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		panel.add(btnCancel);
		
		btnCreateLobby = new JButton("Create Lobby");
		btnCreateLobby.addActionListener(e -> {
			
		});
		panel.add(btnCreateLobby);
	}

}
