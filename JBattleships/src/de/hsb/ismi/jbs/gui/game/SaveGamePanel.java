/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import de.hsb.ismi.jbs.engine.io.manager.GamePersistenceManager;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.JBSBlurredPanel;
import de.hsb.ismi.jbs.start.JBattleships;

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
import javax.swing.ListSelectionModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import java.awt.Color;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class SaveGamePanel extends JBSBlurredPanel{
	
	private JBSGUI parent;
	
	private JList<String> saveList;
	private DefaultListModel<String> saveListModel;
	private JScrollPane saveScrollPane;
	private JLabel lblSaveGame;
	private JBSButton btnSaveGame;
	private JBSButton btnDeleteGame;
	private JBSButton btnBack;
	private JTextField nameField;
	private JLabel lblName;
	

	/**
	 * 
	 */
	public SaveGamePanel(JBSGUI parent) {
		super(30, 30);
		this.parent = parent;
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
		
		lblSaveGame = new JLabel("Save Game:");
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
		
		btnSaveGame = new JBSButton("Save Game");
		btnSaveGame.addActionListener(e -> {
			JBattleships.game.getDataManager().getPersistenceManager().saveGame(nameField.getText() + GamePersistenceManager.GAME_SAVE_EXTENSION);
		});
		btnSaveGame.setEnabled(false);
		
		lblName = new JLabel("Enter a name:");
		lblName.setFont(JBSGUI.MAIN_FONT);
		lblName.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		nameField.setFont(JBSGUI.MAIN_FONT);
		((AbstractDocument)nameField.getDocument()).setDocumentFilter(new DocumentFilter(){
		    @Override
		    public void replace(FilterBypass fb, int i, int i1, String string, AttributeSet as) throws BadLocationException {
		        for (int n = string.length(); n > 0; n--) {
		            char c = string.charAt(n - 1);
		            if(Character.isAlphabetic(c) || (c >= '0' && c <= '9')){
		                super.replace(fb, i, i1, String.valueOf(c), as);
		                if(!btnSaveGame.isEnabled()){
		                	btnSaveGame.setEnabled(true);
		                }
		            }else{
		            	if(nameField.getBackground() != Color.PINK){
			                nameField.setBackground(Color.PINK);
		                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_WARNING_ILLEGAL"));
		            	}
		            }
		        }
		    }
		});
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(saveListModel.contains(nameField.getText())){
		    		lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_WARNING_DUPLICATE"));
		    		lblSaveGame.setForeground(Color.ORANGE);
		    		nameField.setBackground(Color.ORANGE);
		    	}else{
	                if(nameField.getBackground() == Color.ORANGE){
	                	nameField.setBackground(Color.WHITE);
	                	lblSaveGame.setForeground(Color.BLACK);
	                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_STANDARD"));
	                }
		    	}
				if(nameField.getText().isEmpty()){
					btnSaveGame.setEnabled(false);
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(saveListModel.contains(nameField.getText())){
		    		lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_WARNING_DUPLICATE"));
		    		lblSaveGame.setForeground(Color.ORANGE);
		    		nameField.setBackground(Color.ORANGE);
		    	}else{
	                if(nameField.getBackground() == Color.ORANGE){
	                	nameField.setBackground(Color.WHITE);
	                	lblSaveGame.setForeground(Color.BLACK);
	                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_STANDARD"));
	                }
		    	}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(saveListModel.contains(nameField.getText())){
		    		lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_WARNING_DUPLICATE"));
		    		lblSaveGame.setForeground(Color.ORANGE);
		    		nameField.setBackground(Color.ORANGE);
		    	}else{
	                if(nameField.getBackground() == Color.ORANGE){
	                	nameField.setBackground(Color.WHITE);
	                	lblSaveGame.setForeground(Color.BLACK);
	                	lblSaveGame.setText(JBattleships.game.getLocalization("GAME_SAVE_STANDARD"));
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
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		GridBagConstraints gbc_btnSaveGame = new GridBagConstraints();
		gbc_btnSaveGame.fill = GridBagConstraints.BOTH;
		gbc_btnSaveGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveGame.gridx = 1;
		gbc_btnSaveGame.gridy = 4;
		add(btnSaveGame, gbc_btnSaveGame);
		
		btnDeleteGame = new JBSButton("Delete Game");
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

		
		btnBack = new JBSButton("Back");
		btnBack.addActionListener(e -> {
			setVisible(false);
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 3;
		gbc_btnBack.gridy = 4;
		add(btnBack, gbc_btnBack);
		
		loadSaveGames();
	}
	
	private void loadSaveGames(){
		for(String s : JBattleships.game.getDataManager().getPersistenceManager().getSaveGames().keySet()){
			saveListModel.addElement(s);
		}
		if(saveListModel.size() == 0){
			btnDeleteGame.setEnabled(false);
		}
	}
	
}
