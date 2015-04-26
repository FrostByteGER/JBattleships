package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private MainPanel mainPanel;
	private OptionsPanel2 optionsPanel;

	/**
	 * Create the frame and its components.
	 */
	public JBSGUI() {
		panelStack = new Stack<JPanel>();
		mainFrame = new JFrame("JBattleships ALPHA");
		mainFrame.setResizable(JBSCore.RESIZABLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, JBSCore.RESOLUTIONS[0][0], JBSCore.RESOLUTIONS[0][1]);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		optionsPanel = new OptionsPanel2(this);
		mainPanel = new MainPanel(this);
		//TODO: Remove add call
		contentPane.add(mainPanel,BorderLayout.CENTER);
		mainFrame.setContentPane(contentPane);

		
		mainFrame.setUndecorated(false);
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
		contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
		contentPane.add(container,BorderLayout.CENTER);
		container.updateUI();
		JBSCore.msgLogger.addMessage("Swapped JPanel!");
	}
	
	/**
	 * @deprecated
	 * <br>Restores the previous JPanel that was visible before a new one was called.
	 */
	public void restorePrevContainer(){
		JBSCore.msgLogger.addMessage("DEPRECATED METHOD, DO NOT USE!");
		if(!panelStack.empty()){
			//swapContainer(panelStack.pop());
			//JBSCore.msgLogger.addMessage("Restored previous JPanel!");
		}else{
			//JBSCore.msgLogger.addMessage("Stack is Empty!");
		}
	}
	
	/**
	 * Generates a new Header
	 * @return The new Header
	 */
	public JPanel generateHeader(){
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.add(Box.createVerticalStrut(20));
		JLabel headerLbl = new JLabel("JBattleships");
		headerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		headerPanel.add(headerLbl);
		headerPanel.add(Box.createVerticalStrut(20));
		headerPanel.setOpaque(false);
		return headerPanel;
	}

	/**
	 * @return the mainPanel
	 */
	public final MainPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @return the optionsPanel
	 */
	public final OptionsPanel2 getOptionsPanel() {
		return optionsPanel;
	}

}
