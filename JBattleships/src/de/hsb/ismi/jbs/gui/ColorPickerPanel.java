/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
@Deprecated
public class ColorPickerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -143893310294630029L;
	private JButton btnColor;
	private Color color;

	/**
	 * 
	 */
	public ColorPickerPanel(){
		setLayout(new BorderLayout(0, 0));
		color = new Color(777777);
		
		btnColor = new JButton("New button"){
			/**
			 * 
			 */
			private static final long serialVersionUID = 6620534352163125029L;

			/* (non-Javadoc)
			 * @see javax.swing.JComponent#paint(java.awt.Graphics)
			 */
			@Override
			public void paint(Graphics g) {
				g.setColor(color);
				g.fillRect(getLocation().x, getLocation().y, getSize().width, getSize().height);
				//super.paint(g);
			}
		};
		btnColor.setActionCommand("color");
		btnColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("color")){
					//TODO: Change this and create a custom JDialog with changeable language!
					//TODO 2: Delete
					ColorPickerPanel.this.color = JColorChooser.showDialog(ColorPickerPanel.this, "Choose a new Color", new Color(0));
				}
				
			}
		});
		add(btnColor);

	}

}
