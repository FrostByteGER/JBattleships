/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import de.hsb.ismi.jbs.gui.AlphaContainer;

import java.awt.SystemColor;

import javax.swing.JButton;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JButton btn1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //ALWAYS SET BEFORE CREATING THE FRAME!
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestFrame() {
		initPanel();
	}
	
	private void initPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		
		btn1 = new JButton("Facebook");
		btn1.setBackground(new Color(59, 89, 182));
		btn1.setForeground(Color.WHITE);
		btn1.setFocusPainted(false);
		btn1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn1.setUI(new BasicButtonUI(){
			/* (non-Javadoc)
			 * @see javax.swing.plaf.basic.BasicButtonUI#paint(java.awt.Graphics, javax.swing.JComponent)
			 */
			@Override
			public void paint(Graphics g, JComponent c) {
				super.paint(g, c);
			}
		});
		contentPane.add(btn1, BorderLayout.CENTER);
	}

}
