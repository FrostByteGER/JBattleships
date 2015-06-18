/**
 * 
 */
package de.hsb.ismi.jbs.start;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import de.hsb.ismi.jbs.gui.AlphaContainer;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class TestFrame extends JFrame {

	private JPanel contentPane;
	private JPanel centerPanel;
	private JRadioButton rdbtn1;
	private JRadioButton rdbtn2;
	private JRadioButton rdbtn3;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JCheckBox chckbx1;
	private JCheckBox chckbx2;

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
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_centerPanel.rowWeights = new double[]{1.0};
		centerPanel.setLayout(gbl_centerPanel);
		
		leftPanel = new JPanel();
		leftPanel.setOpaque(true);
		leftPanel.setBackground(new Color(0.9f, 0.9f, 0.9f, 0.2f));
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.weightx = 0.5;
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		centerPanel.add(new AlphaContainer(leftPanel), gbc_leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		rdbtn1 = new JRadioButton("Button 1");
		rdbtn1.setOpaque(false);
		leftPanel.add(rdbtn1);
		
		rdbtn2 = new JRadioButton("Button 2");
		rdbtn2.setOpaque(false);
		leftPanel.add(rdbtn2);
		
		rdbtn3 = new JRadioButton("Button 3");
		rdbtn3.setOpaque(false);
		leftPanel.add(rdbtn3);
		
		rightPanel = new JPanel();
		rightPanel.setBackground(new Color(0.9f, 0.9f, 0.9f, 0.5f));
		rightPanel.setOpaque(true);
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.weightx = 0.5;
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 0;
		centerPanel.add(new AlphaContainer(rightPanel), gbc_rightPanel);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
		
		chckbx1 = new JCheckBox("Checkbox 1");
		chckbx1.setOpaque(false);
		rightPanel.add(chckbx1);
		
		chckbx2 = new JCheckBox("Checkbox 2");
		chckbx2.setOpaque(false);
		rightPanel.add(chckbx2);
	}

}
