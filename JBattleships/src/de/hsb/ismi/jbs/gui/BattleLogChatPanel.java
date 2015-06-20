/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class BattleLogChatPanel extends JPanel {
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private ChatPanel chatPanel = new ChatPanel();
	
	private JPanel logPanel = new JPanel();

	private JTextArea logArea = new JTextArea();

	private boolean showChatPanel = true;

	/**
	 * Create the panel.
	 */
	public BattleLogChatPanel(boolean showChatPanel, boolean showBattleLog) {
		setLayout(new BorderLayout(0, 0));
		
		add(tabbedPane);
		
		chatPanel.setOpaque(false);
		logPanel.setOpaque(false);
		setOpaque(false);
		tabbedPane.setOpaque(false);
		
		this.showChatPanel = showChatPanel;
		if (showChatPanel) {
			tabbedPane.addTab("Chat", null, chatPanel, null);
		}
		if(showBattleLog){
			tabbedPane.addTab("Battle-Log", null, logPanel, null);
		}
		
		
		logPanel.setLayout(new BorderLayout(0, 0));
		
		logArea.setEditable(false);
		logPanel.add(logArea);

	}
	
	
	
	/**
	 * @return the showChatPanel
	 */
	public final boolean isShowChatPanel() {
		return showChatPanel;
	}
	
	/**
	 * @param showChatPanel the showChatPanel to set
	 */
	public final void setShowChatPanel(boolean showChatPanel) {
		
		if(showChatPanel && !this.showChatPanel){
			tabbedPane.removeTabAt(0);
			tabbedPane.removeTabAt(1);
			tabbedPane.addTab("Chat", chatPanel);
			tabbedPane.addTab("Battle-Log", logPanel);
		}else if(!showChatPanel && this.showChatPanel){
			tabbedPane.removeTabAt(0);
		}
		this.showChatPanel = showChatPanel;
	}
	
	/**
	 * Adds the given text to the battlelog
	 * @param text Text to add
	 */
	public void addTextToBattleLog(String text){
		logArea.append(text + System.lineSeparator());
	}



	/**
	 * @param text
	 * @see de.hsb.ismi.jbs.gui.ChatPanel#addTextToChat(java.lang.String)
	 */
	public void addTextToChat(String text) {
		chatPanel.addTextToChat(text);
	}



	/**
	 * @return
	 * @see de.hsb.ismi.jbs.gui.ChatPanel#getChatText()
	 */
	public final String getChatText() {
		return chatPanel.getChatText();
	}



	/**
	 * 
	 * @see de.hsb.ismi.jbs.gui.ChatPanel#toggleChatInput()
	 */
	public void toggleChatInput() {
		chatPanel.toggleChatInput();
	}


}
