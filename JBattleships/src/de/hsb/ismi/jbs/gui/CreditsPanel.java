/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class CreditsPanel extends JPanel {
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public CreditsPanel() {

		initPanel();
	}
	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
	}

}
