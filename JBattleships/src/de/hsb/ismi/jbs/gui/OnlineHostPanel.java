/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class OnlineHostPanel extends JPanel {
	
	JBSGUI parent;
	
	private JPanel centerPanel;

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
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0};
		gbl_centerPanel.rowHeights = new int[]{0};
		gbl_centerPanel.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
	}

}
