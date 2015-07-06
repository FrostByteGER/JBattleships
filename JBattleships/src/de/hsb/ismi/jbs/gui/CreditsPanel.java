/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.SwingConstants;

import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class CreditsPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel centerPanel;
	private JLabel header;
	private JLabel lblCredits1;
	private JLabel lblCredits2;
	private JLabel lblCredits3;
	private JLabel lblGameMadeBy;
	private JButton btnBack;

	/**
	 * Create the panel.
	 */
	public CreditsPanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();
	}
	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		
		header = new JLabel(new ImageIcon(JBattleships.game.getDataManager().getResourceManager().getTexture("header_credits.png")));
		add(header, BorderLayout.NORTH);
		
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 46, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 29, 14, 0, 0, 0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		lblGameMadeBy = new JLabel("Game Made by:");
		lblGameMadeBy.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblGameMadeBy.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblGameMadeBy = new GridBagConstraints();
		gbc_lblGameMadeBy.fill = GridBagConstraints.BOTH;
		gbc_lblGameMadeBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblGameMadeBy.gridx = 1;
		gbc_lblGameMadeBy.gridy = 1;
		centerPanel.add(lblGameMadeBy, gbc_lblGameMadeBy);
		
		lblCredits1 = new JLabel("Kevin K\u00FCgler");
		lblCredits1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCredits1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCredits1 = new GridBagConstraints();
		gbc_lblCredits1.fill = GridBagConstraints.BOTH;
		gbc_lblCredits1.anchor = GridBagConstraints.NORTH;
		gbc_lblCredits1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCredits1.gridx = 1;
		gbc_lblCredits1.gridy = 2;
		centerPanel.add(lblCredits1, gbc_lblCredits1);
		
		lblCredits2 = new JLabel("Jan Schult");
		lblCredits2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCredits2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCredits2 = new GridBagConstraints();
		gbc_lblCredits2.fill = GridBagConstraints.BOTH;
		gbc_lblCredits2.anchor = GridBagConstraints.NORTH;
		gbc_lblCredits2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCredits2.gridx = 1;
		gbc_lblCredits2.gridy = 3;
		centerPanel.add(lblCredits2, gbc_lblCredits2);
		
		lblCredits3 = new JLabel("Steven Lauzening");
		lblCredits3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCredits3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCredits3 = new GridBagConstraints();
		gbc_lblCredits3.fill = GridBagConstraints.BOTH;
		gbc_lblCredits3.insets = new Insets(0, 0, 5, 5);
		gbc_lblCredits3.anchor = GridBagConstraints.NORTH;
		gbc_lblCredits3.gridx = 1;
		gbc_lblCredits3.gridy = 4;
		centerPanel.add(lblCredits3, gbc_lblCredits3);
		
		btnBack = new JButton(JBattleships.game.getLocalization("GAME_BACK"));
		btnBack.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 6;
		centerPanel.add(btnBack, gbc_btnBack);
	}

}
