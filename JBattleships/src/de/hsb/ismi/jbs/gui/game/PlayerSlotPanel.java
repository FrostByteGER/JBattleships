/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import de.hsb.ismi.jbs.engine.game.JBSGameType;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * Wrapper JPanel class that contains the elements for player
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PlayerSlotPanel extends JPanel {

	private JLabel lblName = new JLabel();
	private JTextField nameField = new JTextField(10);
	private JCheckBox checkboxAI = new JCheckBox();
	private JCheckBox checkboxActive = new JCheckBox();
	@Deprecated
	private JBSButton btnKick = new JBSButton();
	
	/**
	 * Creates a new PlayerSlotPanel with a preset nameField String.
	 * @wbp.parser.constructor
	 */
	public PlayerSlotPanel(boolean isHost, JBSGameType gameType) {
		initGUI(isHost, gameType);
	}
	
	/**
	 * Creates a new PlayerSlotPanel with a given nameField String.
	 * @param fieldContent The content of the nameField.
	 */
	public PlayerSlotPanel(boolean isHost, JBSGameType gameType, String fieldContent) {
		nameField = new JTextField(fieldContent, 10);
		initGUI(isHost, gameType);
	}
	
	/**
	 * 
	 */
	private void initGUI(boolean isHost, JBSGameType gameType){
		lblName = new JLabel(JBattleships.game.getLocalization("GAME_PLAYER_NAME"));
		nameField = new JTextField(JBattleships.game.getLocalization("GAME_PLAYER"), 10);
		checkboxAI = new JCheckBox(JBattleships.game.getLocalization("GAME_IS_AI"));
		checkboxActive = new JCheckBox(JBattleships.game.getLocalization("GAME_IS_ACTIVE"));
		btnKick = new JBSButton(JBattleships.game.getLocalization("GAME_KICK_PLAYER"));
		
		lblName.setFont(JBSGUI.MAIN_FONT);
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		add(lblName);
		nameField.setFont(JBSGUI.MAIN_FONT);
		add(nameField);
		checkboxAI.setFont(JBSGUI.MAIN_FONT);
		checkboxAI.setEnabled(false);
		add(checkboxAI);
		btnKick.setFont(JBSGUI.MAIN_FONT);
		checkboxAI.addItemListener(i -> {
			btnKick.setEnabled(!checkboxAI.isSelected());
		});
		checkboxActive.setFont(JBSGUI.MAIN_FONT);
		
		checkboxActive.addItemListener(i -> {
			btnKick.setEnabled(checkboxActive.isSelected());
			checkboxAI.setEnabled(checkboxActive.isSelected());
		});
		
		checkboxAI.setSelected(false);
		add(checkboxActive);
		checkboxAI.setOpaque(false);
		checkboxActive.setOpaque(false);
		if(isHost && gameType == JBSGameType.GAME_ONLINE){
			add(new AlphaContainer(btnKick) );
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
