package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * The core GUI-class, everything GUI related starts here.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSGUI{

	private JFrame mainFrame = new JFrame();
	private JPanel contentPane = new JPanel();
	private Stack<JPanel> panelStack  = new Stack<JPanel>();
	
	private MainPanel mainPanel;
	private OptionsPanel optionsPanel;
	private ProfilePanel profilePanel;
	
	private BufferedImage backgroundImage = null;
	private BufferedImage headerImage = null;
	
	public static final Color BACKGROUND_COLOR = new Color(0.5411f, 0.5411f, 0.5411f, 0.4f);
	public static final Font MAIN_FONT = new Font("Tahoma", Font.PLAIN, 22);
	
	public static Clip backgroundMusic = null;

	/**
	 * 
	 */
	public JBSGUI() {
		mainFrame.setTitle(JBattleships.game.getLocalization("GAME_TITLE"));
		backgroundImage = JBattleships.game.getDataManager().getResourceManager().getTexture("jbs_background.jpg");
		headerImage = JBattleships.game.getDataManager().getResourceManager().getTexture("header.png");
		
		mainPanel = new MainPanel(this);
		optionsPanel = new OptionsPanel(this);
		profilePanel = new ProfilePanel(this);
	}
	
	/**
	 * Inits GUI
	 */
	public void initGUI(Resolution res, ScreenMode mode){
		mainFrame.setResizable(JBSCoreGame.RESIZABLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(0, 0, res.getWidth(), res.getHeight());
		
		backgroundMusic = JBattleships.game.getDataManager().getResourceManager().getAudioFile("music_mainmenu.wav");
		
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
				g.drawImage(backgroundImage, 0, 0, this.getWidth(),this.getHeight(), null);
			}
		};
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(mainPanel,BorderLayout.CENTER);
		
		mainFrame.setContentPane(contentPane);

		changeScreenMode(mode);
		mainFrame.setLocationRelativeTo(null); // Sets GUI to center of the screen
		mainFrame.setVisible(true); // Call always at the end!
		if(backgroundMusic != null){
			backgroundMusic.setFramePosition(0);
			((FloatControl)backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN)).setValue((((float)JBattleships.game.getMusicVolume())/100.0f)*(80f)-80.0f);
			backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
	/**
	 * Swaps the current center-component with the given one and adds the previous one to the stack.
	 * @param container
	 */
	public void swapContainer(JPanel container){
		JPanel p = (JPanel) ((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER);
		contentPane.remove(p);
		contentPane.add(container,BorderLayout.CENTER);
		panelStack.push(p);
		container.updateUI();
		DebugLog.logInfo("Swapped JPanel!");
	}
	
	/**
	 * Swaps the current center-component with the given one without adding the previous one to the stack.
	 * Be careful, it destroys the stack-order!
	 * @param container
	 */
	public void forceSwapContainer(JPanel container){
		JPanel p = (JPanel) ((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER);
		contentPane.remove(p);
		contentPane.add(container,BorderLayout.CENTER);
		container.updateUI();
		DebugLog.logInfo("Swapped JPanel!");
	}
	
	/**
	 * <br>Restores the previous JPanel that was visible before a new one was called.
	 */
	public void restorePrevContainer(){
		if(!panelStack.empty()){
			JPanel p = panelStack.pop();
			contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
			contentPane.add(p,BorderLayout.CENTER);
			p.updateUI();
			DebugLog.logInfo("Restored previous JPanel!");
		}else{
			DebugLog.logInfo("Stack is Empty!");
		}
	}
	
	/**
	 * Restores the first JPanel that was visible before new ones were called.
	 * Optionally clears the stack.
	 */
	public void restoreRootContainer(boolean clearStack){
		if(!panelStack.empty()){
			JPanel p = panelStack.firstElement();
			contentPane.remove(((BorderLayout)contentPane.getLayout()).getLayoutComponent(BorderLayout.CENTER));
			contentPane.add(p,BorderLayout.CENTER);
			if(clearStack){
				panelStack.clear();
			}
			p.updateUI();
			DebugLog.logInfo("Restored Rootpanel!");
		}else{
			DebugLog.logInfo("Stack is Empty!");		}
	}
	
	/**
	 * Generates a new Header
	 * @return The new Header
	 */
	public JPanel generateHeader(){
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
		headerPanel.add(Box.createVerticalStrut(5));
		JLabel headerLbl = new JLabel(new ImageIcon(headerImage));
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 40));
		headerLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		headerPanel.add(headerLbl);
		headerPanel.add(Box.createVerticalStrut(5));
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

	/**
	 * @return the mainFrame
	 */
	public final JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param mainFrame the mainFrame to set
	 */
	public final void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * @return the profilePanel
	 */
	public final ProfilePanel getProfilePanel() {
		return profilePanel;
	}
}
