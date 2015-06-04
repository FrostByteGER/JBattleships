/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	
	/**
	 * Creates a new PreGamePlayerPanel with a preset nameField String.
	 */
	public PreGamePlayerPanel() {
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblName);
		add(nameField);
		add(checkboxAI);
		add(checkboxActive);
	}
	
	/**
	 * Creates a new PreGamePlayerPanel with a given nameField String.
	 * @param fieldContent The content of the nameField.
	 */
	public PreGamePlayerPanel(String fieldContent) {
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblName);
		nameField = new JTextField(fieldContent, 10);
		add(nameField);
		add(checkboxAI);
		add(checkboxActive);
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

}
