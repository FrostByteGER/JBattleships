/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JLabel;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class CreditsPanel extends JPanel {
	private JPanel centerPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Create the panel.
	 */
	public CreditsPanel() {

		initPanel();
	}
	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		
		lblNewLabel = new JLabel("New label");
		centerPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		centerPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("New label");
		centerPanel.add(lblNewLabel_2);
	}

}
