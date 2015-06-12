package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;

/**
 * The core GUI-class, everything starts here.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSGUI{

	private JFrame mainFrame = new JFrame("JBattleships ALPHA");
	private JPanel contentPane = new JPanel();
	@Deprecated
	private Stack<JPanel> panelStack  = new Stack<JPanel>();
	
	private MainPanel mainPanel = new MainPanel(this);
	private OptionsPanel optionsPanel = new OptionsPanel(this);
	
	private BufferedImage backgroundImage = null;

	/**
	 * 
	 */
	public JBSGUI() {
		try {
			backgroundImage = ImageIO.read(new File("Data/Textures/jbs_background.jpg"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Inits GUI
	 */
	public void initGUI(Resolution res, ScreenMode mode){
		mainFrame.setResizable(JBSCoreGame.RESIZABLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(100, 100, res.getWidth(), res.getHeight());
		
		contentPane = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -6588552075773032990L;

			/* (non-Javadoc)
			 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
			 */
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(getBackgroundImage(), 0, 0, this.getWidth(),this.getHeight(), null);
			}
		};
		contentPane.setLayout(new BorderLayout(0, 0));

		//TODO: Remove add call
		contentPane.add(mainPanel,BorderLayout.CENTER);
		
		mainFrame.setContentPane(contentPane);

		changeScreenMode(mode);
		mainFrame.setLocationRelativeTo(null); // Sets GUI to center of the screen
		mainFrame.setVisible(true); // Call always at the end!
		
	}
	
	/**
	 * Swaps the current center-component with the given one.
	 * @param container
	 */
	public void swapContainer(JPanel container){
		contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
		contentPane.add(container,BorderLayout.CENTER);
		container.updateUI();
		JBSCoreGame.ioQueue.insertInput("Swapped JPanel!", JBSCoreGame.MSG_LOGGER_KEY);
	}
	
	/**
	 * @deprecated
	 * <br>Restores the previous JPanel that was visible before a new one was called.
	 */
	public void restorePrevContainer(){
		JBSCoreGame.ioQueue.insertInput("DEPRECATED METHOD, DO NOT USE!", JBSCoreGame.MSG_LOGGER_KEY);
		if(!panelStack.empty()){
			//swapContainer(panelStack.pop());
			//JBSCoreGame.msgLogger.addMessage("Restored previous JPanel!");
		}else{
			//JBSCoreGame.msgLogger.addMessage("Stack is Empty!");
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
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 40));
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
	public final OptionsPanel getOptionsPanel() {
		return optionsPanel;
	}
	
	public final void changeFrameSize(Resolution r){
		mainFrame.setSize(r.getWidth(), r.getHeight());
		mainFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * Sets the screenMode. I.e. borderless.
	 * @param sm The screenMode.
	 */
	public final void changeScreenMode(ScreenMode sm){
		mainFrame.dispose(); // Critical, otherwise, IllegalFrameStateException will be thrown
		switch(sm){
		case MODE_FULLSCREEN:
			mainFrame.setUndecorated(true);
			break;
		case MODE_BORDERLESS:
			mainFrame.setUndecorated(true);
			break;
		case MODE_WINDOWED:
			mainFrame.setUndecorated(false);
			break;
		}
		mainFrame.setVisible(true); // Makes GUI visible again
	}

	/**
	 * @return the backgroundImage
	 */
	public final BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @param backgroundImage the backgroundImage to set
	 */
	public final void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

}
