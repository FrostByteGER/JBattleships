package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JBSGUI{

	private JFrame mainFrame;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public JBSGUI() {
		mainFrame = new JFrame("JBattleships ALPHA");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		mainFrame.setContentPane(contentPane);
		mainFrame.setLocationRelativeTo(null); // Sets GUI to center of the screen
		mainFrame.setVisible(true); // Call always at the end!
		
	}
	
	public void initGUI(){
		
	}

}
