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
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGamePlayerPanel extends JPanel {

	private JLabel lblName;
	private JTextField nameField;
	private JCheckBox checkboxAI;
	private JCheckBox checkboxActive;
	
	/**
	 * 
	 */
	public PreGamePlayerPanel() {
		
		lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblName);
		
		nameField = new JTextField();
		add(nameField);
		nameField.setColumns(10);
		
		checkboxAI = new JCheckBox("AI?");
		add(checkboxAI);
		
		checkboxActive = new JCheckBox("Active?");
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
	public final void getName(String text){
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
