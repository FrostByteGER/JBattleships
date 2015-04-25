package de.hsb.ismi.jbs.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JSplitPane;

import de.hsb.ismi.jbs.engine.core.Game;

public class GameFieldContainder extends JPanel {
	
	private JPanel uperSiedPanel;
	private JPanel lowerSidePanel;
	private JPanel uperMainPanel;
	private JPanel lowerMainPanel;
	
	private Game game;
	
	
	public GameFieldContainder(Game game) {
		
		this.game = game;
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		uperSiedPanel = new GameSidePanel();
		uperSiedPanel.setBackground(Color.BLUE);
		
		splitPane.setRightComponent(uperSiedPanel);
		
		uperMainPanel = new GameFieldPanel();
		splitPane.setLeftComponent(uperMainPanel);
		uperMainPanel.setLayout(new BorderLayout(0, 0));
		
		lowerMainPanel = new JPanel();
		uperMainPanel.add(lowerMainPanel, BorderLayout.SOUTH);

	}
	
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setContentPane(new GameFieldContainder(null));
		f.setVisible(true);
	}
	
	
	
	
	
}
