/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGameFieldsPanel extends JPanel {
	
	private JBSGUI parent;
	private JPanel header;
	private JPanel buttonPanel;
	private JButton btnCancel;
	private JButton btnContinue;
	private GameFieldPanel fieldPanel;
	
	public PreGameFieldsPanel(JBSGUI parent) {
		this.parent = parent;
		this.header = parent.generateHeader();
		setLayout(new BorderLayout(0, 0));
		add(header, BorderLayout.NORTH);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(buttonPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("cancel")){
					JBSCore.msgLogger.addMessage("Called Command: \"" + e.getActionCommand() + "\" on " + PreGameFieldsPanel.this.getClass());
					PreGameFieldsPanel.this.parent.swapContainer(PreGameFieldsPanel.this.parent.getMainPanel());
				}
			}
		});
		buttonPanel.add(btnCancel);
		
		btnContinue = new JButton("Continue");
		btnContinue.setActionCommand("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("continue")){
				}
			}
		});
		buttonPanel.add(btnContinue);
		GameManager gm = JBattleships.game.getGameManager();
		fieldPanel = new GameFieldPanel(new JBSGameField(gm.getPlayers().get(0), gm.getFieldSize()), gm.getFieldSize());
		add(fieldPanel, BorderLayout.CENTER);
		
		
		
	}

}
