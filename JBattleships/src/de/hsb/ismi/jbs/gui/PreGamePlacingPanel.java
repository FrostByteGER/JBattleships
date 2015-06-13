/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.core.JBSCorvette;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSFrigate;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSSubmarine;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.TitledBorder;

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
	
	private JBSGUI parent;
	
	private JPanel centerPanel;
	private JPanel header;
	private JPanel buttonPanel;
	private JPanel shipPanel;
	
	private JButton btnCancel;
	private JButton btnContinue;
	private GameFieldPanel fieldPanel;
	private JButton btnDestroyer;
	private JButton btnFrigate;
	private JButton btnCorvette;
	private JButton btnSubmarine;
	private JTextArea textArea;
	
	private int destroyersLeft = 0;
	private int frigatesLeft = 0;
	private int corvettesLeft = 0;
	private int subsLeft = 0;
	
	private JBSShip activeShip;
	private JBSPlayer activePlayer;
	private GameManager gm;
	private int activePlayerIndex;
	private JBSPlayer[] players = null;
	
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
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("cancel")){
					JBSCoreGame.ioQueue.insertInput("Called Command: \"" + e.getActionCommand() + "\" on " + PreGamePlacingPanel.this.getClass(), JBSCoreGame.MSG_LOGGER_KEY);
					PreGamePlacingPanel.this.parent.swapContainer(PreGamePlacingPanel.this.parent.getMainPanel());
				}
			}
		});
		buttonPanel.add(btnCancel);
		
		btnContinue = new JButton("Continue");
		btnContinue.setActionCommand("continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("continue")){
					nextPlayer();
				}
			}
		});
		buttonPanel.add(btnContinue);
		
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
		
		centerPanel.add(fieldPanel, "cell 0 0,width :90%:,grow");

		
		shipPanel = new JPanel();
		shipPanel.setOpaque(true);
		shipPanel.setBackground(JBSGUI.BACKGROUND_COLOR);
		centerPanel.add(new AlphaContainer(shipPanel), "cell 1 0,grow");
		shipPanel.setBorder(new TitledBorder(null, "Ship List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_shipPanel = new GridBagLayout();
		gbl_shipPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_shipPanel.columnWeights = new double[]{1.0};
		gbl_shipPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		shipPanel.setLayout(gbl_shipPanel);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArea.setWrapStyleWord(true);
		textArea.setText("Choose a shiptype and left-click on the board to place your ship. Use right-click to change the direction.");
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
		
		btnDestroyer = new JButton("Destroyers Left: " + destroyersLeft);
		btnDestroyer.setActionCommand("placedest");
		btnDestroyer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("placedest")){
					if(destroyersLeft > 0){
						activeShip = new JBSDestroyer();	
						lblSelectedShip.setText("Selected Ship: Destroyer");
					}
				}
			}
		});
		
		lblSelectedShip = new JLabel("Selected Ship: None");
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
		shipPanel.add(btnDestroyer, gbc_btnDestroyer);
		
		btnFrigate = new JButton("Frigates Left: " + frigatesLeft);
		btnFrigate.setActionCommand("placefrig");
		btnFrigate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("placefrig")){
					if(frigatesLeft > 0){
						activeShip = new JBSFrigate();
						lblSelectedShip.setText("Selected Ship: Frigate");
					}
				}
			}
		});
		GridBagConstraints gbc_btnFrigate = new GridBagConstraints();
		gbc_btnFrigate.ipady = 20;
		gbc_btnFrigate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFrigate.insets = new Insets(0, 5, 5, 0);
		gbc_btnFrigate.gridx = 0;
		gbc_btnFrigate.gridy = 3;
		shipPanel.add(btnFrigate, gbc_btnFrigate);
		
		btnCorvette = new JButton("Corvettes Left: " + corvettesLeft);
		btnCorvette.setActionCommand("placecorv");
		btnCorvette.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("placecorv")){
					if(corvettesLeft > 0){
						activeShip = new JBSCorvette();	
						lblSelectedShip.setText("Selected Ship: Corvette");
					}
				}
			}
		});
		GridBagConstraints gbc_btnCorvette = new GridBagConstraints();
		gbc_btnCorvette.ipady = 20;
		gbc_btnCorvette.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCorvette.insets = new Insets(0, 5, 5, 0);
		gbc_btnCorvette.gridx = 0;
		gbc_btnCorvette.gridy = 4;
		shipPanel.add(btnCorvette, gbc_btnCorvette);
		
		btnSubmarine = new JButton("Submarines Left: " + subsLeft);
		btnSubmarine.setActionCommand("placesub");
		btnSubmarine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("placesub")){
					if(subsLeft > 0){
						activeShip = new JBSSubmarine();	
						lblSelectedShip.setText("Selected Ship: Submarine");
					}
				}
			}
		});
		GridBagConstraints gbc_btnSubmarine = new GridBagConstraints();
		gbc_btnSubmarine.ipady = 20;
		gbc_btnSubmarine.insets = new Insets(0, 5, 5, 0);
		gbc_btnSubmarine.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSubmarine.gridx = 0;
		gbc_btnSubmarine.gridy = 5;
		shipPanel.add(btnSubmarine, gbc_btnSubmarine);
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
		fieldPanel.setShowSelection(false);
		fieldPanel.addGameFieldActionListener(new GameFieldActionListener() {
			@Override
			public void clickFired(JPanel instigator) {
				if(instigator instanceof GameFieldPanel && activeShip != null){
					JBSCoreGame.ioQueue.insertInput("Fired Event " +  GameFieldActionListener.class.getSimpleName() + " from: " + instigator.getClass().getSimpleName(), JBSCoreGame.MSG_LOGGER_KEY);
					GameFieldPanel gfp = (GameFieldPanel)instigator;
					activeShip.setPositon(gfp.getSelectx(), gfp.getSelecty(), gfp.getDirection());
					if(gfp.getGamefild().addShip(activeShip)){
						if(activeShip instanceof JBSDestroyer){
							destroyersLeft--;
							btnDestroyer.setText("Destroyers Left: " + destroyersLeft);	
						}else if(activeShip instanceof JBSFrigate){
							frigatesLeft--;
							btnFrigate.setText("Frigates Left: " + frigatesLeft);	
						}else if(activeShip instanceof JBSCorvette){
							corvettesLeft--;
							btnCorvette.setText("Corvettes Left: " + corvettesLeft);	
						}else if(activeShip instanceof JBSSubmarine){
							subsLeft--;
							btnSubmarine.setText("Submarines Left: " + subsLeft);	
						}
						activePlayer.addShip(activeShip);
						JBSCoreGame.ioQueue.insertInput("Successfully placed " + activeShip.getClass().getSimpleName() + " at X:" + gfp.getSelectx() + " Y: " + gfp.getSelecty() + " Direction: " + gfp.getDirection(), JBSCoreGame.MSG_LOGGER_KEY);
						
						activeShip = null;
						lblSelectedShip.setText("Selected Ship: None");
					}else{
						JBSCoreGame.ioQueue.insertInput("Could not place " + activeShip.getClass().getSimpleName() + " at X:" + gfp.getSelectx() + " Y: " + gfp.getSelecty() + " Direction: " + gfp.getDirection(), JBSCoreGame.MSG_LOGGER_KEY);
					}
				}
			}
		});
		
		if(activePlayer instanceof JBSAIPlayer){
			JBSAIPlayer ai = (JBSAIPlayer)activePlayer;
			removeAll();
			setLayout(new BorderLayout(0, 0));
			JLabel aiNotify = new JLabel("AI Player is placing its ships, please wait...", SwingConstants.CENTER);
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
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					//Manually call updatePlayerData to bypass the display of the GUI.
					nextPlayer();
				}
			}, 10);
		}else{
			// Updates the panel with the new player-data since this is a human player!
			updatePanel();
			if(activePlayerIndex == gm.getGame().getPlayers().length - 1){
				btnContinue.setText("Start Game");
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
		//TODO: Add security question if destroyersLeft etc... is > 0
		if(activePlayerIndex < gm.getGame().getPlayers().length - 1){
			activePlayerIndex++;
			initPlayerData();
		}else if(activePlayerIndex == gm.getGame().getPlayers().length - 1){
			gm.startGame();
			parent.swapContainer(new MainGamePanel(parent));
			JBSCoreGame.ioQueue.insertInput("Started Game!", JBSCoreGame.MSG_LOGGER_KEY);
		}
	}

}
