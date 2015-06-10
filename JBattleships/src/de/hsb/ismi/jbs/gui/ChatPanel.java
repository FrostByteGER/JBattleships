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
public class ChatPanel extends JPanel {
	
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel chatPanel = new JPanel();
	private JPanel logPanel = new JPanel();
	private JTextArea chatArea = new JTextArea();
	private JTextField inputField = new JTextField();
	private JTextArea logArea = new JTextArea();

	private boolean showChatPanel = true;
	private final JButton btnEnter = new JButton("Enter");
	/**
	 * Create the panel.
	 */
	public ChatPanel(boolean showChatPanel) {
		inputField.setActionCommand("enter");
		inputField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("enter")){
					if (!inputField.getText().equals("")) {
						addTextToChat(inputField.getText());
						inputField.setText("");
					}
				}
			}
		});
		inputField.setColumns(10);
		setLayout(new BorderLayout(0, 0));
		
		add(tabbedPane);
		
		this.showChatPanel = showChatPanel;
		if (showChatPanel) {
			tabbedPane.addTab("Chat", null, chatPanel, null);
		}
		GridBagLayout gbl_chatPanel = new GridBagLayout();
		gbl_chatPanel.columnWidths = new int[] {220, 0};
		gbl_chatPanel.rowHeights = new int[] {22, 0};
		gbl_chatPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_chatPanel.rowWeights = new double[]{0.0, 0.0};
		chatPanel.setLayout(gbl_chatPanel);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.weightx = 1.0;
		gbc_textArea.weighty = 1.0;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		chatArea.setEditable(false);
		chatPanel.add(chatArea, gbc_textArea);
		
		GridBagConstraints gbc_inputField = new GridBagConstraints();
		gbc_inputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_inputField.gridx = 0;
		gbc_inputField.gridy = 1;
		chatPanel.add(inputField, gbc_inputField);
		
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 1;
		btnEnter.setActionCommand("enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("enter")){
					if (!inputField.getText().equals("")) {
						addTextToChat(inputField.getText());
						inputField.setText("");
					}
				}
			}
		});
		chatPanel.add(btnEnter, gbc_btnEnter);
		
		tabbedPane.addTab("Battle-Log", null, logPanel, null);
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
	 * Returns the chatInputField text.
	 * @return
	 */
	public final String getChatText(){
		return inputField.getText();
	}
	
	/**
	 * Adds the given text to the battlelog
	 * @param text Text to add
	 */
	public void addTextToBattleLog(String text){
		logArea.append(text + System.lineSeparator());
	}
	
	/**
	 * Adds the given text to the chat
	 * @param text Text to add
	 */
	public void addTextToChat(String text){
		chatArea.append(text + System.lineSeparator());
	}
	
	public void toggleChatInput(){
		if(inputField.isEnabled() && btnEnter.isEnabled()){
			inputField.setEnabled(false);
			btnEnter.setEnabled(false);
		}else{
			inputField.setEnabled(true);
			btnEnter.setEnabled(true);
		}
	}

}
