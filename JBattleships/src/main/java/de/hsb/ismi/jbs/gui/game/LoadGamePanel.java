/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import de.hsb.ismi.jbs.engine.game.Game;
import de.hsb.ismi.jbs.engine.game.managers.GameManager;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class LoadGamePanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4467704170669512400L;
	private JBSGUI parent;
	private JPanel centerPanel = new JPanel();
	private JList<String> loadList = new JList<String>();
	private DefaultListModel<String> loadListModel = new DefaultListModel<>();
	private JBSButton btnLoad;
	private JBSButton btnDelete;
	private JBSButton btnBack;
	private JLabel lblLoad;
	private JScrollPane scrollPane = new JScrollPane();

	/**
	 * 
	 */
	public LoadGamePanel(JBSGUI parent) {
		this.parent = parent;
		initPanel();	
	}

	private void initPanel() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		
		this.add(parent.generateHeader(), BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		
		btnLoad = new JBSButton(JBattleships.game.getLocalization("GAME_LOAD_SAVE"));
		btnDelete = new JBSButton(JBattleships.game.getLocalization("GAME_DELETE_SAVE"));
		btnBack = new JBSButton(JBattleships.game.getLocalization("GAME_BACK"));
		lblLoad = new JLabel(JBattleships.game.getLocalization("GAME_TITLE_LOAD_SAVE"));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{0, 150, 0};
		gridBagLayout.columnWidths = new int[]{0, 116, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		centerPanel.setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblLoad = new GridBagConstraints();
		gbc_lblLoad.fill = GridBagConstraints.BOTH;
		gbc_lblLoad.gridwidth = 3;
		gbc_lblLoad.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoad.gridx = 0;
		gbc_lblLoad.gridy = 0;
		lblLoad.setFont(JBSGUI.MAIN_FONT);
		lblLoad.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblLoad, gbc_lblLoad);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		centerPanel.add(scrollPane, gbc_scrollPane);
		loadList.setFont(JBSGUI.MAIN_FONT);
		loadList.setModel(loadListModel);
		loadList.setSelectedIndex(0);
		loadList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loadList.setOpaque(false);
		scrollPane.setViewportView(loadList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBackground(JBSGUI.BACKGROUND_COLOR);
		
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.fill = GridBagConstraints.BOTH;
		gbc_btnLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoad.gridx = 0;
		gbc_btnLoad.gridy = 2;
		btnLoad.addActionListener(e -> {
			Game g = JBattleships.game.getDataManager().getPersistenceManager().getSaveGame(loadList.getSelectedValue());
			//Dont create this after starting the game, causes the fireStartedGame Event NOT to be called!
			if(g != null){
				GameManager gm = JBattleships.game.generateGame();
				gm.setGame(g);
				MainGamePanel gfc = new MainGamePanel(this.parent);
				JBattleships.game.getGameManager().startGame();
				parent.swapContainer(gfc);
			}
		});
		btnLoad.setFont(JBSGUI.MAIN_FONT);
		centerPanel.add(new AlphaContainer(btnLoad), gbc_btnLoad);
		
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 0, 5);
		gbc_btnDelete.gridx = 1;
		gbc_btnDelete.gridy = 2;
		btnDelete.addActionListener(e -> {
			String name = loadList.getSelectedValue();
			if(name != null){
				JBattleships.game.getDataManager().getPersistenceManager().deleteSaveGame(name);
				loadListModel.removeElement(loadList.getSelectedValue());
				loadList.setSelectedIndex(0);
			}
		});
		btnDelete.setFont(JBSGUI.MAIN_FONT);
		centerPanel.add(new AlphaContainer(btnDelete), gbc_btnDelete);
		
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.fill = GridBagConstraints.BOTH;
		gbc_btnBack.gridx = 2;
		gbc_btnBack.gridy = 2;
		btnBack.addActionListener(e -> {
			parent.restorePrevContainer();
		});
		btnBack.setFont(JBSGUI.MAIN_FONT);
		centerPanel.add(new AlphaContainer(btnBack), gbc_btnBack);
		
		
		
		loadSaveGames();
	}
	
	private void loadSaveGames(){
		JBattleships.game.getDataManager().getPersistenceManager().loadGames();
		for(String s : JBattleships.game.getDataManager().getPersistenceManager().getSaveGames().keySet()){
			loadListModel.addElement(s);
		}
	}

}
