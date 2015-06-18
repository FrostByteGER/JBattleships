/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import de.hsb.ismi.jbs.engine.core.JBSGameType;
import java.awt.Font;

/**
 * Wrapper JPanel class that contains the elements for player
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGamePlayerPanel extends JPanel {

	private JLabel lblName = new JLabel("Name:");
	private JTextField nameField = new JTextField("Undefined", 10);
	private JCheckBox checkboxAI = new JCheckBox("AI?");
	private JCheckBox checkboxActive = new JCheckBox("Active?");
	private JButton btnKick = new JButton("Kick Player");
	
	/**
	 * Creates a new PreGamePlayerPanel with a preset nameField String.
	 * @wbp.parser.constructor
	 */
	public PreGamePlayerPanel(boolean isHost, JBSGameType gameType) {
		initGUI(isHost, gameType);
	}
	
	/**
	 * Creates a new PreGamePlayerPanel with a given nameField String.
	 * @param fieldContent The content of the nameField.
	 */
	public PreGamePlayerPanel(boolean isHost, JBSGameType gameType, String fieldContent) {
		nameField = new JTextField(fieldContent, 10);
		initGUI(isHost, gameType);
	}
	
	/**
	 * 
	 */
	private void initGUI(boolean isHost, JBSGameType gameType){
		lblName.setFont(JBSGUI.SERVER_FONT);
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblName);
		nameField.setFont(JBSGUI.SERVER_FONT);
		add(nameField);
		checkboxAI.setFont(JBSGUI.SERVER_FONT);
		add(checkboxAI);
		
		checkboxAI.addItemListener(i -> {
			btnKick.setEnabled(checkboxAI.isSelected());
		});
		checkboxActive.setFont(JBSGUI.SERVER_FONT);
		
		checkboxActive.addItemListener(i -> {
			btnKick.setEnabled(checkboxActive.isSelected());
			nameField.setEditable(checkboxActive.isSelected());
		});
		
		checkboxAI.setSelected(false);
		add(checkboxActive);
		checkboxAI.setOpaque(false);
		checkboxActive.setOpaque(false);
		if(isHost && gameType == JBSGameType.GAME_ONLINE){
			add(btnKick);
		}
	}

	/**
	 * @return the nameField
	 */
	public final JTextField getNameField() {
		return nameField;
	}
	
	/**
	 * 
	 */
	public final String getName(){
		return nameField.getText();
	}
	
	/**
	 * 
	 * @param text
	 */
	public final void setName(String text){
		nameField.setText(text);
	}

	/**
	 * @return the checkboxAI
	 */
	public final JCheckBox getCheckboxAI() {
		return checkboxAI;
	}
	
	/**
	 * 
	 * @param b
	 */
	public final void setAISelected(boolean b) {
		checkboxAI.setSelected(b);
	}
	
	/**
	 * 
	 * @return
	 */
	public final boolean isAISelected() {
		return checkboxAI.isSelected();
	}

	/**
	 * 
	 * @return
	 */
	public final JCheckBox getCheckboxActive() {
		return checkboxActive;
	}
	
	/**
	 * 
	 * @param b
	 */
	public final void setActiveSelected(boolean b) {
		checkboxActive.setSelected(b);
	}
	
	/**
	 * 
	 * @return
	 */
	public final boolean isActiveSelected() {
		return checkboxActive.isSelected();
	}

	/**
	 * @return the btnKick
	 */
	public final JButton getBtnKick() {
		return btnKick;
	}

}
