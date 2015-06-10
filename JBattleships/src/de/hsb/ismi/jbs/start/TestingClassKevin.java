/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import de.hsb.ismi.jbs.engine.io.parser.ResourceParser;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class TestingClassKevin {

	/**
	 * 
	 */
	public TestingClassKevin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final ResourceParser p = new ResourceParser();
					BufferedImage[] b = null;
					try {
						b = p.parseAnimation("Data/Textures/testwater.png").getSourceSprites();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					JFrame mainFrame = new JFrame();
					mainFrame.setResizable(true);
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setBounds(100, 100, 400,400);
					
					JPanel contentPane = new JPanel();
					contentPane.setLayout(new BorderLayout(0, 0));
					JTabbedPane jtp = new JTabbedPane();
					for(int j = 0; j < b.length; j++){
						//jtp.addTab(Integer.toString(j + 1), new JLabel(new ImageIcon(b[j])));
					}
					JPanel animP = new JPanel(new BorderLayout(0,0));
					
					jtp.addTab("Animation", animP);
					contentPane.add(jtp,BorderLayout.CENTER);
					JButton btn = new JButton("Start!");
					btn.setActionCommand("start");
					btn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getActionCommand().equals("start")){
								//TODO
								try {
									BufferedImage[] x = p.parseAnimation("Data/Textures/testexplosion.png").getSourceSprites();
									new Thread(new Runnable() {
										@Override
										public void run() {
											int i = 0;
											while(true){
												animP.removeAll();
												animP.add(new JLabel(new ImageIcon(x[i])), BorderLayout.CENTER);
												animP.updateUI();
												if(i < x.length - 1){
													i++;
												}else{
													i = 0;
												}
												try {
													Thread.sleep(42); // Animation speed
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
											}
										}
									}, "Animation-Thread").start();//TODO


								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
					});
					animP.add(btn, BorderLayout.SOUTH);
					
					mainFrame.setContentPane(contentPane);
					mainFrame.setLocationRelativeTo(null); // Sets GUI to center of the screen
					mainFrame.setVisible(true); // Call always at the end!
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
