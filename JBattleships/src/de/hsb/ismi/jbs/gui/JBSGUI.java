package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.util.Stack;

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
	private Stack<JPanel> panelStack;
	private int x;
	
	/**
	 * Create the frame and its components.
	 */
	public JBSGUI() {
		panelStack = new Stack<JPanel>();
		mainFrame = new JFrame("JBattleships ALPHA");
		mainFrame.setResizable(JBSCore.RESIZABLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, JBSCore.resolutions[0][0], JBSCore.resolutions[0][1]);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		//TODO: Remove add call
		contentPane.add(new JBSOptionsPanel(this),BorderLayout.CENTER);
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
	 * Swaps the current center-component with the given one.
	 * @param container
	 */
	public void swapContainer(JPanel container){
		//TODO: Check if remove works!
		panelStack.push((JPanel) ((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
		contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
		contentPane.add(container,BorderLayout.CENTER);
		container.updateUI();
		JBSCore.msgLogger.addMessage("Swapped JPanel!");
	}
	
	/**
	 * Restores the previous JPanel that was visible before a new one was called.
	 */
	public void restorePrevContainer(){
		if(!panelStack.empty()){
			swapContainer(panelStack.pop());
			JBSCore.msgLogger.addMessage("Restored previous JPanel!");
		}else{
			JBSCore.msgLogger.addMessage("Stack is Empty!");
		}
	}

}
