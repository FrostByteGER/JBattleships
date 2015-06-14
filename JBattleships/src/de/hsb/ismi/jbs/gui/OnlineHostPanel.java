/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlineHostPanel extends JPanel {
	private JPanel centerPanel;

	/**
	 * Create the panel.
	 */
	public OnlineHostPanel() {

		initPanel();
	}
	private void initPanel() {
		
		centerPanel = new JPanel();
		add(centerPanel);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0};
		gbl_centerPanel.rowHeights = new int[]{0};
		gbl_centerPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
	}

}
