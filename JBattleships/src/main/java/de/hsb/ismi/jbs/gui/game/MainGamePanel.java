/**
 * 
 */
package de.hsb.ismi.jbs.gui.game;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.game.Game;
import de.hsb.ismi.jbs.engine.game.GameListener;
import de.hsb.ismi.jbs.engine.game.managers.RoundManager;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.gui.JBSButton;
import de.hsb.ismi.jbs.gui.JBSGUI;
import de.hsb.ismi.jbs.gui.utility.AlphaContainer;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class MainGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5233315862494496314L;
	private Game game;
	private RoundManager roundManager;
	
	private int selectedGameField;
	
	private GameFieldPanel gameFieldPanel;
	private GameSidePanel gameSidePanel;
	
	private JPanel sidePanel;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JPanel playerSelectionPanel;
	private BattleLogPanel battleLogPanel;
	
	private JLabel fieldNumber;
	private JLabel activePlayerlbl;
	private JBSButton btnExit;
	private JBSButton btnShoot;
	private JBSButton btnEndRound;
	private JBSButton btnSaveGame;
	private JBSButton btnShowShips;

	
	/**
	 * Create the panel.
	 */
	public MainGamePanel(JBSGUI parent) {
		this.game = JBattleships.game.getGameManager().getGame();
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		sidePanel = new JPanel();
		sidePanel.setOpaque(false);
		
		battleLogPanel = new BattleLogPanel();
		
		selectedGameField = 0;
		
		roundManager = JBattleships.game.getGameManager().getRoundManager();
		
		gameSidePanel = new GameSidePanel(game.getPlayers()[game.getActivePlayerInt()]);
		centerPanel.add(sidePanel, "cell 1 0,width :35%:,grow");
		sidePanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		activePlayerlbl = new JLabel(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
		activePlayerlbl.setFont(JBSGUI.MAIN_FONT);
		sidePanel.add(activePlayerlbl, "cell 0 0,height :10%:,grow");
		sidePanel.add(new AlphaContainer(gameSidePanel) , "cell 0 1,height :65%:,grow");

		sidePanel.add(battleLogPanel, "cell 0 2,height :25%:,grow");
		
		btnExit = new JBSButton(JBattleships.game.getLocalization("GAME_EXIT_MATCH"));
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(e -> {
			parent.restoreRootContainer(true);
		});
		btnShoot = new JBSButton(JBattleships.game.getLocalization("GAME_SHOOT"));
		
		btnShoot.setActionCommand("shoot");
		btnShoot.addActionListener(e -> {
				if(game.getActivePlayerInt() == selectedGameField){
					//chat.setText(chat.getText()+"\nDont shoot yourself");
				}else{
					if(gameSidePanel.getSelectedship().canShoot()){
						roundManager.fireRound(game.getPlayer(selectedGameField), game.getActivePlayer(), gameSidePanel.getSelectedship(), gameFieldPanel.getSelectx(), gameFieldPanel.getSelecty(), gameFieldPanel.getDirection());
						roundManager.fireAnalyzeRound(game.getActivePlayer());
						roundManager.fireEndRound(game.getActivePlayer());
						DebugLog.logInfo("Round ended for Player: " + game.getActivePlayer().getName());
						roundManager.reset();
						game.nextPlayer();
						gameSidePanel.setPlayer(game.getActivePlayer());
						gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
						gameSidePanel.repaint();
						activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
						selectedGameField = game.getActivePlayerInt();
						fieldNumber.setText(game.getActivePlayer().getName());
						if(game.getActivePlayer() instanceof JBSAIPlayer){
							JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
							ai.processRound(game);
							DebugLog.logInfo("AI " + game.getActivePlayer().getName() + " ended its turn!");
							for(ActionListener a: btnEndRound.getActionListeners()) {
							    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pass") {

									/**
									 * 
									 */
									private static final long serialVersionUID = 7140433730255705194L;
							    });
							}
						}
					}else{
						//chat.setText(chat.getText()+"\nCan't shoot");
					}
				}
		});
		
		btnEndRound = new JBSButton(JBattleships.game.getLocalization("GAME_END_ROUND"));
		btnEndRound.setActionCommand("pass");
		btnEndRound.addActionListener(e -> {
			roundManager.fireEndRound(game.getActivePlayer());
			DebugLog.logInfo("Round ended for Player: " + game.getActivePlayer().getName());
			roundManager.reset();
			game.nextPlayer();
			gameSidePanel.setPlayer(game.getActivePlayer());
			gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
			gameSidePanel.repaint();
			activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
			selectedGameField = game.getActivePlayerInt();
			fieldNumber.setText(game.getActivePlayer().getName());
			if(game.getActivePlayer() instanceof JBSAIPlayer){
				JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
				ai.processRound(game);
				DebugLog.logInfo("AI " + game.getActivePlayer().getName() + " ended its turn!");
				for(ActionListener a: btnEndRound.getActionListeners()) {
				    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pass") {
						/**
						 * 
						 */
						private static final long serialVersionUID = 7140433730255705194L;
				    });
				}
			}
		});
		
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.add(new AlphaContainer(btnExit));
		buttonPanel.add(new AlphaContainer(btnShoot));
		buttonPanel.add(new AlphaContainer(btnEndRound));
		
		add(buttonPanel,BorderLayout.SOUTH);
		
		btnSaveGame = new JBSButton(JBattleships.game.getLocalization("GAME_SAVE"));
		btnSaveGame.addActionListener(e -> {
			parent.getMainFrame().getGlassPane().setVisible(true);
		});
		
		buttonPanel.add(new AlphaContainer(btnSaveGame));
		
		btnShowShips =  new JBSButton(JBattleships.game.getLocalization("GAME_SHOW_SHIPS"));
		
		btnShowShips.addActionListener(e -> {
			gameFieldPanel.setShowships(true);
			selectedGameField = game.getActivePlayerInt();
			gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
			activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
			fieldNumber.setText(game.getActivePlayer().getName());
		});
		
		buttonPanel.add(btnShowShips);
		
		gameFieldPanel = new GameFieldPanel(game.getPlayers()[game.getActivePlayerInt()].getPlayerField(),500,50);
		gameFieldPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(gameFieldPanel , "cell 0 0,width :65%:,grow");
		
		playerSelectionPanel = new JPanel();
		playerSelectionPanel.setOpaque(false);
		playerSelectionPanel.setLayout(new FlowLayout());
		
		JBSButton pbut = new JBSButton();
		pbut.setText(JBattleships.game.getLocalization("GAME_NEXT"));
		pbut.addActionListener(e -> {
			gameFieldPanel.setShowships(false);
			if(game.getPlayers().length-1>selectedGameField){
				selectedGameField++;

			}else if(game.getPlayers().length-1 == selectedGameField){
				selectedGameField = 0;
			}
			fieldNumber.setText(game.getPlayer(selectedGameField).getName());

			gameFieldPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
		});
		
		JBSButton mbut = new JBSButton();
		mbut.setText(JBattleships.game.getLocalization("GAME_PREVIOUS"));
		mbut.addActionListener(e -> {
			gameFieldPanel.setShowships(false);
			if(selectedGameField>0){
				selectedGameField--;

			}else if(selectedGameField == 0){
				selectedGameField = game.getPlayers().length-1;
			}
			fieldNumber.setText(game.getPlayer(selectedGameField).getName());
			
			gameFieldPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
		});
		
		fieldNumber = new JLabel();
		fieldNumber.setFont(JBSGUI.MAIN_FONT);		
		fieldNumber.setText(game.getPlayer(selectedGameField).getName());
		
		playerSelectionPanel.add(new AlphaContainer(mbut));
		playerSelectionPanel.add(fieldNumber);
		playerSelectionPanel.add(new AlphaContainer(pbut));
		
		add(playerSelectionPanel,BorderLayout.NORTH);
		
		gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
		
		parent.getMainFrame().setGlassPane(new SaveGamePanel());
		
		JBattleships.game.getGameManager().addGameListener(new GameListener() {
			
			@Override
			public void fireStartedGame() {
				activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER")  + " " + game.getActivePlayer().getName());
			}
			
			@Override
			public void fireEndedGame() {
				parent.swapContainer(new PostGamePanel(parent));
			}
		});
		
		if(game.getActivePlayer() instanceof JBSAIPlayer){
			JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
			ai.processRound(game);
			DebugLog.logInfo("AI " + game.getActivePlayer().getName() + " ended its turn!");
			for(ActionListener a: btnEndRound.getActionListeners()) {
			    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "pass") {

					/**
					 * 
					 */
					private static final long serialVersionUID = 7140433730255705194L;
			    });
			}
		}
	}
}
