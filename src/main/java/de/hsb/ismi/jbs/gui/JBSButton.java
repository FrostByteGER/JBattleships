package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * Custom button class that modifies the UI of it.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class JBSButton extends JButton {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3411017032450323548L;
	private Color selectedColor = new Color(0.5411f, 0.5411f, 0.5411f*1.5f, 0.8f);
	private Color defaultColor = new Color(0.5411f, 0.5411f, 0.5411f*1.5f, 0.6f);
	private Clip hoverClip = null;
	private Clip clickClip = null;

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
		hoverClip = JBattleships.game.getDataManager().getResourceManager().getAudioFile("sfx_button_hover.wav");
		clickClip = JBattleships.game.getDataManager().getResourceManager().getAudioFile("sfx_button_click.wav");
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
				if(clickClip != null){
					clickClip.setFramePosition(0);
					((FloatControl)clickClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue((((float)JBattleships.game.getSoundVolume())/100.0f)*(80f)-80.0f);
					clickClip.start();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(defaultColor);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(selectedColor);	
				if(hoverClip != null){
					hoverClip.setFramePosition(0);
					((FloatControl)hoverClip.getControl(FloatControl.Type.MASTER_GAIN)).setValue((((float)JBattleships.game.getSoundVolume())/100.0f)*(80f)-80.0f);
					hoverClip.start();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}
}
