/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import java.awt.GridBagLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import de.hsb.ismi.jbs.engine.io.manager.GamePersistenceManager;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.JBSBlurredPanel;
import de.hsb.ismi.jbs.start.JBattleships;

import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import java.awt.Color;

/**
 * 
 * @author Kevin Kuegler
 * @version 1.00
 */
public class SaveGamePanel extends JBSBlurredPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5522098731874486074L;

	
	private JList<String> saveList;
	private DefaultListModel<String> saveListModel;
	private JScrollPane saveScrollPane;
	private JLabel lblSaveGame;
	private JBSButton btnSaveGame;
	private JBSButton btnDeleteGame;
	private JBSButton btnBack;
	private JTextField saveNameField;
	private JLabel lblName;
	

	/**
	 * 
	 */
	public SaveGamePanel() {
		super(30, 30);
		initPanel();
	}
	
	/**
	 * 
	 */
	private void initPanel() {
		setOpaque(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 100, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblSaveGame = new JLabel(JBattleships.game.getLocalization("GAME_TITLE_SAVE"));
		lblSaveGame.setFont(JBSGUI.MAIN_FONT);
		lblSaveGame.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSaveGame = new GridBagConstraints();
		gbc_lblSaveGame.gridwidth = 3;
		gbc_lblSaveGame.fill = GridBagConstraints.BOTH;
		gbc_lblSaveGame.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaveGame.gridx = 1;
		gbc_lblSaveGame.gridy = 1;
		add(lblSaveGame, gbc_lblSaveGame);
		
		saveListModel = new DefaultListModel<>();
		saveList = new JList<String>(saveListModel);
		saveList.setFont(JBSGUI.MAIN_FONT);
		saveList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_saveList = new GridBagConstraints();
		gbc_saveList.gridwidth = 3;
		gbc_saveList.insets = new Insets(0, 0, 5, 5);
		gbc_saveList.fill = GridBagConstraints.BOTH;
		gbc_saveList.gridx = 1;
		gbc_saveList.gridy = 2;
		saveScrollPane = new JScrollPane(saveList);
		saveScrollPane.getViewport().setOpaque(false);
		saveScrollPane.setBorder(BorderFactory.createEmptyBorder());
		saveScrollPane.setBackground(JBSGUI.BACKGROUND_COLOR);
		add(saveScrollPane, gbc_saveList);
		
		btnSaveGame = new JBSButton(JBattleships.game.getLocalization("GAME_SAVE"));
		btnSaveGame.addActionListener(e -> {
			JBattleships.game.getDataManager().getPersistenceManager().saveGame(saveNameField.getText() + GamePersistenceManager.GAME_SAVE_EXTENSION);
			setVisible(false);
		});
		btnSaveGame.setEnabled(false);
		
		lblName = new JLabel(JBattleships.game.getLocalization("GAME_TITLE_SAVE_NAME"));
		lblName.setFont(JBSGUI.MAIN_FONT);
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		add(lblName, gbc_lblName);
		
		saveNameField = new JTextField();
		saveNameField.setFont(JBSGUI.MAIN_FONT);
		((AbstractDocument)saveNameField.getDocument()).setDocumentFilter(new DocumentFilter(){
		    @Override
		    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
		    	if(string != null){
			        for (int n = string.length(); n > 0; n--) {
			            char c = string.charAt(n - 1);
			            if(Character.isAlphabetic(c) || (c >= '0' && c <= '9')){
			                super.replace(fb, i, i1, String.valueOf(c), as);
			                if(!btnSaveGame.isEnabled()){
			                	btnSaveGame.setEnabled(true);
			                }
			            }else{
			            	if(saveNameField.getBackground() != Color.PINK){
				                saveNameField.setBackground(Color.PINK);
			                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE_WARNING_ILLEGAL"));
			            	}
			            }
			        }
		    	}else{
		    		super.replace(fb, i, i1, "", as);
		    	}
		    }
		});
		saveNameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(saveListModel.contains(saveNameField.getText())){
		    		lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE_WARNING_DUPLICATE"));
		    		lblSaveGame.setForeground(Color.ORANGE);
		    		saveNameField.setBackground(Color.ORANGE);
		    	}else{
	                if(saveNameField.getBackground() == Color.ORANGE){
	                	saveNameField.setBackground(Color.WHITE);
	                	lblSaveGame.setForeground(Color.BLACK);
	                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE_STANDARD"));
	                }
		    	}
				if(saveNameField.getText().isEmpty()){
					btnSaveGame.setEnabled(false);
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(saveListModel.contains(saveNameField.getText())){
		    		lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE_WARNING_DUPLICATE"));
		    		lblSaveGame.setForeground(Color.ORANGE);
		    		saveNameField.setBackground(Color.ORANGE);
		    	}else{
	                if(saveNameField.getBackground() == Color.ORANGE){
	                	saveNameField.setBackground(Color.WHITE);
	                	lblSaveGame.setForeground(Color.BLACK);
	                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE_STANDARD"));
	                }
		    	}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(saveListModel.contains(saveNameField.getText())){
		    		lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE_WARNING_DUPLICATE"));
		    		lblSaveGame.setForeground(Color.ORANGE);
		    		saveNameField.setBackground(Color.ORANGE);
		    	}else{
	                if(saveNameField.getBackground() == Color.ORANGE){
	                	saveNameField.setBackground(Color.WHITE);
	                	lblSaveGame.setForeground(Color.BLACK);
	                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_TITLE_SAVE"));
	                }
		    	}
			}
		});
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.BOTH;
		gbc_nameField.gridx = 2;
		gbc_nameField.gridy = 3;
		add(saveNameField, gbc_nameField);
		saveNameField.setColumns(10);
		GridBagConstraints gbc_btnSaveGame = new GridBagConstraints();
		gbc_btnSaveGame.fill = GridBagConstraints.BOTH;
		gbc_btnSaveGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveGame.gridx = 1;
		gbc_btnSaveGame.gridy = 4;
		add(btnSaveGame, gbc_btnSaveGame);
		
		btnDeleteGame = new JBSButton(JBattleships.game.getLocalization("GAME_DELETE_SAVE"));
		btnDeleteGame.addActionListener(e -> {
			String name = saveList.getSelectedValue();
			if(name != null){
				JBattleships.game.getDataManager().getPersistenceManager().deleteSaveGame(name);
				saveListModel.removeElement(saveList.getSelectedValue());
				saveList.setSelectedIndex(0);
				if(saveListModel.size() == 0){
					btnDeleteGame.setEnabled(false);
				}
			}
		});
		GridBagConstraints gbc_btnDeleteGame = new GridBagConstraints();
		gbc_btnDeleteGame.fill = GridBagConstraints.BOTH;
		gbc_btnDeleteGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteGame.gridx = 2;
		gbc_btnDeleteGame.gridy = 4;
		add(btnDeleteGame, gbc_btnDeleteGame);

		
		btnBack = new JBSButton(JBattleships.game.getLocalization("GAME_BACK"));
		btnBack.addActionListener(e -> {
			setVisible(false);
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 3;
		gbc_btnBack.gridy = 4;
		add(btnBack, gbc_btnBack);
	}
	
	private void loadSaveGames(){
		JBattleships.game.getDataManager().getPersistenceManager().loadGames();
		for(String s : JBattleships.game.getDataManager().getPersistenceManager().getSaveGames().keySet()){
			saveListModel.addElement(s);
		}
		if(saveListModel.size() == 0){
			btnDeleteGame.setEnabled(false);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.hsb.ismi.jbs.gui.utility.JBSBlurredPanel#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			loadSaveGames();
		}else{
			saveListModel.removeAllElements();
			saveNameField.setText(null);
		}
	}
	
}
