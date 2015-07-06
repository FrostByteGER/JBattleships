/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import de.hsb.ismi.jbs.engine.game.JBSGameType;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * Wrapper JPanel class that contains the elements for player
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PlayerSlotPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7352625873930133475L;
	private JLabel lblName = new JLabel();
	private JTextField nameField = new JTextField(10);
	private JCheckBox checkboxAI = new JCheckBox();
	private JCheckBox checkboxActive = new JCheckBox();
	
	/**
	 * Creates a new PlayerSlotPanel with a preset nameField String.
	 * @wbp.parser.constructor
	 */
	public PlayerSlotPanel(JBSGameType gameType) {
		initGUI(gameType);
	}
	
	/**
	 * Creates a new PlayerSlotPanel with a given nameField String.
	 * @param fieldContent The content of the nameField.
	 */
	public PlayerSlotPanel(JBSGameType gameType, String fieldContent) {
		nameField = new JTextField(fieldContent, 10);
		initGUI(gameType);
	}
	
	/**
	 * 
	 */
	private void initGUI(JBSGameType gameType){
		lblName = new JLabel(JBattleships.game.getLocalization("GAME_PLAYER_NAME"));
		nameField = new JTextField(JBattleships.game.getLocalization("GAME_PLAYER"), 10);
		checkboxAI = new JCheckBox(JBattleships.game.getLocalization("GAME_IS_AI"));
		checkboxActive = new JCheckBox(JBattleships.game.getLocalization("GAME_IS_ACTIVE"));
		
		lblName.setFont(JBSGUI.MAIN_FONT);
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblName);
		nameField.setFont(JBSGUI.MAIN_FONT);
		add(nameField);
		checkboxAI.setFont(JBSGUI.MAIN_FONT);
		checkboxAI.setEnabled(false);
		add(checkboxAI);
		checkboxActive.setFont(JBSGUI.MAIN_FONT);
		
		checkboxActive.addItemListener(i -> {
			checkboxAI.setEnabled(checkboxActive.isSelected());
		});
		
		checkboxAI.setSelected(false);
		add(checkboxActive);
		checkboxAI.setOpaque(false);
		checkboxActive.setOpaque(false);
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
