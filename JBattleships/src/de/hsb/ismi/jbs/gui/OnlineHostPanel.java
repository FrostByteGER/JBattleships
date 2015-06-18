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
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlineHostPanel extends JPanel {
	
	JBSGUI parent;
	
	private JPanel centerPanel;
	private JButton btnCancel;
	private JButton btnCreateLobby;
	private JPanel panel;

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
		centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(null, "New Online-Game", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
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
