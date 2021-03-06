/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.actors.ships.JBSCorvette;
import de.hsb.ismi.jbs.engine.actors.ships.JBSDestroyer;
import de.hsb.ismi.jbs.engine.actors.ships.JBSFrigate;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.actors.ships.JBSSubmarine;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.game.JBSGameField;
import de.hsb.ismi.jbs.engine.game.managers.GameManager;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.Font;

import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

import java.awt.SystemColor;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class PreGamePlacingPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5578342633172322046L;

	private JBSGUI parent;
	
	private JPanel centerPanel;
	private JPanel header;
	private JPanel buttonPanel;
	private JPanel shipPanel;
	
	private JBSButton btnCancel;
	private JBSButton btnContinue;
	private GameFieldPanel fieldPanel;
	private JBSButton btnDestroyer;
	private JBSButton btnFrigate;
	private JBSButton btnCorvette;
	private JBSButton btnSubmarine;
	private JTextArea textArea;
	
	private int destroyersLeft = 0;
	private int frigatesLeft = 0;
	private int corvettesLeft = 0;
	private int subsLeft = 0;
	
	private JBSShip activeShip;
	private JBSPlayer activePlayer;
	private GameManager gm;
	private int activePlayerIndex;
	
	private JLabel lblSelectedShip;
		
	/**
	 * 
	 * @param parent
	 */
	public PreGamePlacingPanel(JBSGUI parent) {
		activePlayerIndex = 0;
		gm = JBattleships.game.getGameManager();
		this.parent = parent;
		initPlayerData();
	}
	
	/**
	 * Initiates the Panel.
	 */
	public void initPanel(){
		this.header = parent.generateHeader();
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		add(header, BorderLayout.NORTH);
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(buttonPanel, BorderLayout.SOUTH);
		
		btnCancel = new JBSButton(JBattleships.game.getLocalization("GAME_CANCEL"));
		btnCancel.addActionListener(e -> {
			DebugLog.logInfo("Called Command: \"" + e.getActionCommand() + "\" on " + PreGamePlacingPanel.this.getClass());
			parent.restoreRootContainer(true);
		});
		buttonPanel.add(new AlphaContainer(btnCancel) );
		
		btnContinue = new JBSButton(JBattleships.game.getLocalization("GAME_CONTINUE"));
		btnContinue.setEnabled(false);
		btnContinue.addActionListener(e -> {
			nextPlayer();
		});
		buttonPanel.add(new AlphaContainer(btnContinue) );
		
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		
		GridBagConstraints gbc_fieldPanel = new GridBagConstraints();
		gbc_fieldPanel.weightx = 0.9;
		gbc_fieldPanel.weighty = 1.0;
		gbc_fieldPanel.fill = GridBagConstraints.BOTH;
		gbc_fieldPanel.insets = new Insets(0, 0, 0, 5);
		gbc_fieldPanel.gridx = 0;
		gbc_fieldPanel.gridy = 0;
		centerPanel.setLayout(new MigLayout("", "[grow][561px]", "[412px,grow]"));
		
		centerPanel.add(fieldPanel , "cell 0 0,width :90%:,grow");

		
		shipPanel = new JPanel();
		shipPanel.setOpaque(true);
		shipPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		centerPanel.add(new AlphaContainer(shipPanel), "cell 1 0,grow");
		shipPanel.setBorder(new TitledBorder(null, JBattleships.game.getLocalization("GAME_SHIP_LIST"), TitledBorder.LEADING, TitledBorder.TOP, JBSGUI.MAIN_FONT, null));
		GridBagLayout gbl_shipPanel = new GridBagLayout();
		gbl_shipPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_shipPanel.columnWeights = new double[]{1.0};
		gbl_shipPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		shipPanel.setLayout(gbl_shipPanel);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setWrapStyleWord(true);
		textArea.setText(JBattleships.game.getLocalization("GAME_PLACING_HINT"));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setBackground(SystemColor.menu);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		shipPanel.add(textArea, gbc_textArea);
		
		btnDestroyer = new JBSButton(JBattleships.game.getLocalization("GAME_DESTROYERS_LEFT") + " " + destroyersLeft);
		btnDestroyer.addActionListener(e -> {
			if(destroyersLeft > 0){
				activeShip = new JBSDestroyer();	
				lblSelectedShip.setText(JBattleships.game.getLocalization("GAME_SELECTED_SHIP") + " " +JBattleships.game.getLocalization("GAME_DESTROYER"));
			}
		});
		
		lblSelectedShip = new JLabel(JBattleships.game.getLocalization("GAME_SELECTED_SHIP") + " " +  JBattleships.game.getLocalization("GAME_SELECTED_NONE"));
		lblSelectedShip.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblSelectedShip = new GridBagConstraints();
		gbc_lblSelectedShip.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectedShip.gridx = 0;
		gbc_lblSelectedShip.gridy = 1;
		shipPanel.add(lblSelectedShip, gbc_lblSelectedShip);
		GridBagConstraints gbc_btnDestroyer = new GridBagConstraints();
		gbc_btnDestroyer.ipady = 20;
		gbc_btnDestroyer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDestroyer.insets = new Insets(0, 5, 5, 0);
		gbc_btnDestroyer.gridx = 0;
		gbc_btnDestroyer.gridy = 2;
		shipPanel.add(new AlphaContainer(btnDestroyer) , gbc_btnDestroyer);
		
		btnFrigate = new JBSButton(JBattleships.game.getLocalization("GAME_FRIGATES_LEFT") + " " + frigatesLeft);
		btnFrigate.addActionListener(e -> {
			if(frigatesLeft > 0){
				activeShip = new JBSFrigate();
				lblSelectedShip.setText(JBattleships.game.getLocalization("GAME_SELECTED_SHIP") + " " + JBattleships.game.getLocalization("GAME_FRIGATE"));
			}
		});
		GridBagConstraints gbc_btnFrigate = new GridBagConstraints();
		gbc_btnFrigate.ipady = 20;
		gbc_btnFrigate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFrigate.insets = new Insets(0, 5, 5, 0);
		gbc_btnFrigate.gridx = 0;
		gbc_btnFrigate.gridy = 3;
		shipPanel.add(new AlphaContainer(btnFrigate) , gbc_btnFrigate);
		
		btnCorvette = new JBSButton(JBattleships.game.getLocalization("GAME_CORVETTES_LEFT") + " " + corvettesLeft);
		btnCorvette.addActionListener(e -> {
			if(corvettesLeft > 0){
				activeShip = new JBSCorvette();	
				lblSelectedShip.setText(JBattleships.game.getLocalization("GAME_SELECTED_SHIP") + " " + JBattleships.game.getLocalization("GAME_CORVETTE"));
			}
		});
		GridBagConstraints gbc_btnCorvette = new GridBagConstraints();
		gbc_btnCorvette.ipady = 20;
		gbc_btnCorvette.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCorvette.insets = new Insets(0, 5, 5, 0);
		gbc_btnCorvette.gridx = 0;
		gbc_btnCorvette.gridy = 4;
		shipPanel.add(new AlphaContainer(btnCorvette) , gbc_btnCorvette);
		
		btnSubmarine = new JBSButton(JBattleships.game.getLocalization("GAME_SUBMARINES_LEFT") + " " + subsLeft);
		btnSubmarine.addActionListener(e -> {
			if(subsLeft > 0){
				activeShip = new JBSSubmarine();	
				lblSelectedShip.setText(JBattleships.game.getLocalization("GAME_SELECTED_SHIP") + " " + JBattleships.game.getLocalization("GAME_SUBMARINE"));
			}
		});
		GridBagConstraints gbc_btnSubmarine = new GridBagConstraints();
		gbc_btnSubmarine.ipady = 20;
		gbc_btnSubmarine.insets = new Insets(0, 5, 5, 0);
		gbc_btnSubmarine.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSubmarine.gridx = 0;
		gbc_btnSubmarine.gridy = 5;
		shipPanel.add(new AlphaContainer(btnSubmarine) , gbc_btnSubmarine);
	}
	
	/**
	 * Initiates the player-relevant data.
	 */
	private void initPlayerData(){
		destroyersLeft = gm.getGame().getDestroyerCount();
		frigatesLeft = gm.getGame().getFrigateCount();
		corvettesLeft = gm.getGame().getCorvetteCount();
		subsLeft = gm.getGame().getSubmarineCount();
		activePlayer = gm.getGame().getPlayer(activePlayerIndex);
		activePlayer.setPlayerField(new JBSGameField(gm.getFieldSize()));
		fieldPanel = new GameFieldPanel(activePlayer.getPlayerField(), 400, gm.getFieldSize());
		fieldPanel.setShowships(true);
		fieldPanel.setShowSelection(false);
		fieldPanel.addGameFieldActionListener(instigator -> {
			
				if(instigator instanceof GameFieldPanel && activeShip != null){
					DebugLog.logInfo("Fired Event " +  GameFieldActionListener.class.getSimpleName() + " from: " + instigator.getClass().getSimpleName());
					GameFieldPanel gfp = (GameFieldPanel)instigator;
					activeShip.setPositon(gfp.getSelectx(), gfp.getSelecty(), gfp.getDirection());
					if(gfp.getGamefild().addShip(activeShip)){
						if(activeShip instanceof JBSDestroyer){
							destroyersLeft--;
							btnDestroyer.setText(JBattleships.game.getLocalization("GAME_DESTROYERS_LEFT") + " " + destroyersLeft);
							activePlayer.addShip(activeShip);
							if(destroyersLeft == 0){
								activeShip = null;
							}else{
								activeShip = new JBSDestroyer();
							}
						}else if(activeShip instanceof JBSFrigate){
							frigatesLeft--;
							btnFrigate.setText(JBattleships.game.getLocalization("GAME_FRIGATES_LEFT") + " " + frigatesLeft);
							activePlayer.addShip(activeShip);
							if(frigatesLeft == 0){
								activeShip = null;
							}else{
								activeShip = new JBSFrigate();
							}
						}else if(activeShip instanceof JBSCorvette){
							corvettesLeft--;
							btnCorvette.setText(JBattleships.game.getLocalization("GAME_CORVETTES_LEFT") + " " + corvettesLeft);
							activePlayer.addShip(activeShip);
							if(corvettesLeft == 0){
								activeShip = null;
							}else{
								activeShip = new JBSCorvette();
							}
						}else if(activeShip instanceof JBSSubmarine){
							subsLeft--;
							btnSubmarine.setText(JBattleships.game.getLocalization("GAME_SUBMARINES_LEFT") + " " + subsLeft);
							activePlayer.addShip(activeShip);
							if(subsLeft == 0){
								activeShip = null;
							}else{
								activeShip = new JBSSubmarine();
							}
						}
						
						//DebugLog.logInfo("Successfully placed " + activeShip.getClass().getSimpleName() + " at X:" + gfp.getSelectx() + " Y: " + gfp.getSelecty() + " Direction: " + gfp.getDirection());
						
						if(activeShip == null){
							lblSelectedShip.setText(JBattleships.game.getLocalization("GAME_SELECTED_SHIP") + " " + JBattleships.game.getLocalization("GAME_SELECTED_NONE"));
						}
					}else{
						DebugLog.logInfo("Could not place " + activeShip.getClass().getSimpleName() + " at X:" + gfp.getSelectx() + " Y: " + gfp.getSelecty() + " Direction: " + gfp.getDirection());
					}
					if(destroyersLeft + frigatesLeft + corvettesLeft + subsLeft == 0){
						btnContinue.setEnabled(true);
					}
				}
		});
		
		if(activePlayer instanceof JBSAIPlayer){
			JBSAIPlayer ai = (JBSAIPlayer)activePlayer;
			removeAll();
			setLayout(new BorderLayout(0, 0));
			setOpaque(false);
			JLabel aiNotify = new JLabel(JBattleships.game.getLocalization("GAME_AI_HINT"), SwingConstants.CENTER);
			aiNotify.setFont(new Font("Tahoma", Font.BOLD, 16));
			updateUI();
			add(aiNotify, BorderLayout.CENTER);
			
			////
			// AI Magic
			ai.placeShips();
			////
			
			/* 
			 * Sets a timer to notify the player about the AI. The AI code runs probably extremely fast, 
			 * so its strange to click on next and instantaniously see the next placementpanel. 
			 * As a result, the player expects to see the AI working but instead sees the next player-placement-panel!
			 * So this Timer gives the player a visual feedback that the AI is working now.
			 * The timer is set to 3 seconds.
			 */
			int time = 3000;
			if(JBSCoreGame.DEBUG_MODE){
				time = 10;
			}
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					//Manually call updatePlayerData to bypass the display of the GUI.
					nextPlayer();
				}
			}, time);
		}else{
			// Updates the panel with the new player-data since this is a human player!
			updatePanel();
			if(activePlayerIndex == gm.getGame().getPlayers().length - 1){
				btnContinue.setText(JBattleships.game.getLocalization("GAME_START"));
			}
		}
	}
	
	/**
	 * Reinitiates the panel and its components.
	 */
	private void updatePanel(){
		removeAll();
		initPanel();
	}
	
	/**
	 * Gets next player and initiates the panel with its data. Also checks if its the last player and starts the game.
	 */
	private void nextPlayer(){
		if(activePlayerIndex < gm.getGame().getPlayers().length - 1){
			activePlayerIndex++;
			initPlayerData();
		}else if(activePlayerIndex == gm.getGame().getPlayers().length - 1){
			gm.startGame();
			parent.swapContainer(new MainGamePanel(parent));
			DebugLog.logInfo("Started Game!");
		}
	}

}
