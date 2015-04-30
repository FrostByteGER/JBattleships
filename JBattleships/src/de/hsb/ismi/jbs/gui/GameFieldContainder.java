package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;

public class GameFieldContainder extends JPanel {
	public GameFieldContainder() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel2 = new JPanel();
		splitPane.setLeftComponent(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel2.add(panel_12, BorderLayout.SOUTH);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
