/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * Wrapper JPanel class that contains the elements for player
 * @author Kevin Kuegler
 * @version 1.00
 */
public class LobbyPlayerSlotPanel extends JPanel {

	private JLabel lblName = new JLabel("Name:");
	private JTextField nameField = new JTextField("", 10);
	private JCheckBox checkboxAI = new JCheckBox("AI?");
	private JCheckBox checkboxActive = new JCheckBox("Active?");
	private JButton btnKick = new JButton("Kick Player");
	
	private int position = 1;
	
	/**
	 * Creates a new LobbyPlayerSlotPanel with a preset nameField String.
	 * @param isHost
	 * @param gameType
	 */
	public LobbyPlayerSlotPanel(boolean isHost, JBSGameType gameType) {
		initGUI(isHost, gameType);
	}
	
	/**
	 * Creates a new LobbyPlayerSlotPanel with a given nameField String.
	 * @param fieldContent The content of the nameField.
	 */
	public LobbyPlayerSlotPanel(boolean isHost, JBSGameType gameType, String fieldContent) {
		nameField.setText(fieldContent);
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
		btnKick.setFont(JBSGUI.SERVER_FONT);

		checkboxActive.setFont(JBSGUI.SERVER_FONT);
		
		if(isHost){
			add(checkboxActive);
			checkboxAI.addItemListener(i -> {
				btnKick.setEnabled(!checkboxAI.isSelected());
				JBattleships.game.getGameServer().kickPlayer(nameField.getText());
				if(!checkboxAI.isSelected()){
					btnKick.setEnabled(checkboxAI.isSelected());
					setName("");
					JBattleships.game.getGameServer().setCurrentPlayerCount(JBattleships.game.getGameServer().getCurrentPlayerCount() + 1);
				}else{
					JBattleships.game.getGameServer().setCurrentPlayerCount(JBattleships.game.getGameServer().getCurrentPlayerCount() - 1);
					setName("CPU #" + position);
				}
				System.out.println(JBattleships.game.getGameServer().getCurrentPlayerCount());
			});
			
			checkboxActive.addItemListener(i -> {
				checkboxAI.setEnabled(checkboxActive.isSelected());
				nameField.setText("");
				if(checkboxActive.isSelected()){
					if(!checkboxAI.isSelected()){
						JBattleships.game.getGameServer().setCurrentPlayerCount(JBattleships.game.getGameServer().getCurrentPlayerCount() + 1);
					}else{
						setName("CPU #" + position);
					}
				}else{
					if(!checkboxAI.isSelected()){
						JBattleships.game.getGameServer().setCurrentPlayerCount(JBattleships.game.getGameServer().getCurrentPlayerCount() - 1);
						JBattleships.game.getGameServer().kickPlayer(nameField.getText());
					}
				}
				System.out.println(JBattleships.game.getGameServer().getCurrentPlayerCount());
			});
			
			btnKick.addActionListener(e -> {
				JBattleships.game.getGameServer().kickPlayer(nameField.getText());
			});
		}else{
			checkboxAI.setEnabled(false);
			checkboxActive.setEnabled(false);
		}
		checkboxAI.setSelected(false);
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

	/**
	 * @return the position
	 */
	public final int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public final void setPosition(int position) {
		this.position = position;
	}

}
