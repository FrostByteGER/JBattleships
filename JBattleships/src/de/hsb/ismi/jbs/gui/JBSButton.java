package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Einfacher JButton mit voreinstellungen
 * 
 * @author Jan Schult
 *
 */
public class JBSButton extends JButton {
	
	private Color selectedColor = new Color(0.5411f, 0.5411f, 0.5411f*1.5f, 0.8f);
	//private Color defaultColor = new Color(0.5411f, 0.5411f, 0.5411f, 0.6f);
	private Color defaultColor = new Color(0.5411f, 0.5411f, 0.5411f*1.5f, 0.6f);

	public JBSButton() {
		JBSInit();
	}

	public JBSButton(Icon arg0) {
		super(arg0);
		JBSInit();
	}

	public JBSButton(String arg0) {
		super(arg0);
		JBSInit();
	}

	public JBSButton(Action arg0) {
		super(arg0);
		JBSInit();
	}

	public JBSButton(String arg0, Icon arg1) {
		super(arg0, arg1);
		JBSInit();
	}
	
	private void JBSInit() {
		setUI(new BasicButtonUI());
		setBackground(defaultColor);
		setForeground(Color.WHITE);
		setFocusPainted(false);
		setFont(JBSGUI.MAIN_FONT);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(defaultColor);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(selectedColor);	
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {			
			}
		});
	}
}
