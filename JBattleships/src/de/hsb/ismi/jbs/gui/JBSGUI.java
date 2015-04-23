package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.hsb.ismi.jbs.core.JBSCore;

/**
 * The core GUI-class, everything starts here.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSGUI{

	private JFrame mainFrame;
	private JPanel contentPane;

	/**
	 * Create the frame and its components.
	 */
	public JBSGUI() {
		mainFrame = new JFrame("JBattleships ALPHA");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, JBSCore.resolutions[0][0], JBSCore.resolutions[0][1]);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//TODO: Remove add call
		contentPane.add(new JBSOptionsPanel(),BorderLayout.CENTER);
		mainFrame.setContentPane(contentPane);
		mainFrame.setLocationRelativeTo(null); // Sets GUI to center of the screen
		mainFrame.setVisible(true); // Call always at the end!
		
	}
	
	/**
	 * Inits GUI
	 */
	public void initGUI(){
		
	}
	
	/**
	 * Swaps the current center-component with the given one
	 * @param container
	 */
	public void swapContainer(JPanel container){
		//TODO: Check if remove works!
		contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
		contentPane.add(container,BorderLayout.CENTER);
	}

}
