/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ChatPanel extends JPanel {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4342585729435357807L;
	
	private JTextArea chatArea = new JTextArea();
	private JTextField inputField = new JTextField();
	private final JButton btnEnter = new JButton("Enter");
	
	private static final int MAX_LINE_COUNT = 255;

	/**
	 * Create the panel.
	 */
	public ChatPanel() {
		initPanel();
	}
	
	private void initPanel(){
		setBorder(new TitledBorder(null, "Chat:", TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.SERVER_FONT, null));
		inputField.setActionCommand("enter");
		inputField.addActionListener(e -> {
			if (!inputField.getText().equals("")) {
				addTextToChat(inputField.getText());
				inputField.setText("");
			}
		});
		inputField.setColumns(10);
		setOpaque(false);
		chatArea.setOpaque(false);

		GridBagLayout gbl_chatPanel = new GridBagLayout();
		gbl_chatPanel.columnWidths = new int[] {220, 0};
		gbl_chatPanel.rowHeights = new int[] {22, 0};
		gbl_chatPanel.columnWeights = new double[]{1.0, 0.0};
		gbl_chatPanel.rowWeights = new double[]{0.0, 0.0};
		setLayout(gbl_chatPanel);
		
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.weightx = 1.0;
		gbc_textArea.weighty = 1.0;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		chatArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(chatArea);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		add(scrollPane, gbc_textArea);
		
		GridBagConstraints gbc_inputField = new GridBagConstraints();
		gbc_inputField.fill = GridBagConstraints.BOTH;
		gbc_inputField.gridx = 0;
		gbc_inputField.gridy = 1;
		add(inputField, gbc_inputField);
		
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.fill = GridBagConstraints.VERTICAL;
		gbc_btnEnter.gridx = 1;
		gbc_btnEnter.gridy = 1;
		btnEnter.setFont(JBSGUI.SERVER_FONT);
		btnEnter.addActionListener(e -> {
			if (!inputField.getText().equals("")) {
				addTextToChat(inputField.getText());
				inputField.setText("");
			}
		});
		add(btnEnter, gbc_btnEnter);
	}
	
	/**
	 * Returns the chatInputField text.
	 * @return
	 */
	public final String getChatText(){
		return inputField.getText();
	}
	
	/**
	 * Adds the given text to the chat
	 * @param text Text to add
	 */
	public void addTextToChat(String text){
		if(chatArea.getLineCount() > MAX_LINE_COUNT){
			chatArea.setText("");
		}
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
