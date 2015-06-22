/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.GameListener;
import de.hsb.ismi.jbs.engine.core.manager.RoundManager;
import de.hsb.ismi.jbs.start.JBattleships;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class MainGamePanel extends JPanel {

	private Game game;
	private RoundManager roundManager;
	
	private int selectedGameField;
	
	@SuppressWarnings("unused")
	private JBSGUI parent;
	
	private GameFieldPanel gameFieldPanel;
	private GameSidePanel gameSidePanel;
	
	private JPanel sidePanel;
	private JPanel centerPanel;
	private JPanel lowerSidePanel;
	private JPanel lowerMainPanel;
	private JPanel uperMainPanel;
	private BattleLogPanel battleLogPanel;
	
	private JLabel fieldNumber;
	private JLabel activePlayerlbl;
	private JBSButton btnExit;
	private JBSButton btnShoot;
	private JBSButton btnEndRound;
	private JBSButton btnSaveGame;

	
	/**
	 * Create the panel.
	 */
	public MainGamePanel(JBSGUI parent) {
		this.parent = parent;
		this.game = JBattleships.game.getGameManager().getGame();
		setLayout(new BorderLayout(0, 0));
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
		
		sidePanel = new JPanel();
		
		battleLogPanel = new BattleLogPanel();
		
		selectedGameField = 0;
		
		roundManager = JBattleships.game.getGameManager().getRoundManager();
		
		gameSidePanel = new GameSidePanel(game.getPlayers()[game.getActivePlayerInt()]);
		centerPanel.add(sidePanel, "cell 1 0,width :35%:,grow");
		sidePanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		activePlayerlbl = new JLabel(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
		activePlayerlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		sidePanel.add(activePlayerlbl, "cell 0 0,height :10%:,grow");
		sidePanel.add(gameSidePanel, "cell 0 1,height :65%:,grow");

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
						JBSCoreGame.ioQueue.insertInput("Round ended for Player: " + game.getActivePlayer().getName(), JBSCoreGame.MSG_LOGGER_KEY);
						roundManager.reset();
						game.nextPlayer();
						gameSidePanel.setPlayer(game.getActivePlayer());
						gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
						gameSidePanel.repaint();
						activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
						if(game.getActivePlayer() instanceof JBSAIPlayer){
							JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
							ai.processRound(game);
							JBSCoreGame.ioQueue.insertInput("AI " + game.getActivePlayer().getName() + " ended its turn!", JBSCoreGame.MSG_LOGGER_KEY);
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
			JBSCoreGame.ioQueue.insertInput("Round ended for Player: " + game.getActivePlayer().getName(), JBSCoreGame.MSG_LOGGER_KEY);
			roundManager.reset();
			game.nextPlayer();
			gameSidePanel.setPlayer(game.getActivePlayer());
			gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
			gameSidePanel.repaint();
			activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER") + " " + game.getActivePlayer().getName());
			if(game.getActivePlayer() instanceof JBSAIPlayer){
				JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
				ai.processRound(game);
				JBSCoreGame.ioQueue.insertInput("AI " + game.getActivePlayer().getName() + " ended its turn!", JBSCoreGame.MSG_LOGGER_KEY);
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
		
		lowerSidePanel = new JPanel();
		
		lowerSidePanel.setLayout(new FlowLayout());
		lowerSidePanel.add(new AlphaContainer(btnExit));
		lowerSidePanel.add(new AlphaContainer(btnShoot));
		lowerSidePanel.add(new AlphaContainer(btnEndRound));
		
		add(lowerSidePanel,BorderLayout.SOUTH);
		
		btnSaveGame = new JBSButton(JBattleships.game.getLocalization("GAME_SAVE"));
		btnSaveGame.addActionListener(e -> {
			//TODO: Add SaveDialog
			JBattleships.game.getDataManager().getPersistenceManager().saveGame("game_001.xml");
		});
		
		lowerSidePanel.add(new AlphaContainer(btnSaveGame));
		
		
		gameFieldPanel = new GameFieldPanel(game.getPlayers()[game.getActivePlayerInt()].getPlayerField(),500,50);
		gameFieldPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(gameFieldPanel, "cell 0 0,width :65%:,grow");
		
		lowerMainPanel = new JPanel();
		lowerMainPanel.setLayout(new BorderLayout());
		
		uperMainPanel = new JPanel();
		uperMainPanel.setLayout(new FlowLayout());
		
		JBSButton pbut = new JBSButton();
		pbut.setText(JBattleships.game.getLocalization("GAME_NEXT"));
		pbut.addActionListener(e -> {	
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
			if(selectedGameField>0){
				selectedGameField--;

			}else if(selectedGameField == 0){
				selectedGameField = game.getPlayers().length-1;
			}
			fieldNumber.setText(game.getPlayer(selectedGameField).getName());
			
			gameFieldPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
		});
		
		fieldNumber = new JLabel();
				
		fieldNumber.setText(game.getPlayer(selectedGameField).getName());
		
		uperMainPanel.add(new AlphaContainer(mbut));
		uperMainPanel.add(fieldNumber);
		uperMainPanel.add(new AlphaContainer(pbut));
		
		gameFieldPanel.add(uperMainPanel,BorderLayout.NORTH);
		
		gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
		
		JBattleships.game.getGameManager().addGameListener(new GameListener() {
			
			@Override
			public void fireStartedGame() {
				activePlayerlbl.setText(JBattleships.game.getLocalization("GAME_ACTIVE_PLAYER")  + " " + game.getActivePlayer().getName());
			}
			
			@Override
			public void fireEndedGame() {
				parent.swapContainer(parent.getMainPanel());
			}
		});
		
		if(game.getActivePlayer() instanceof JBSAIPlayer){
			JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
			ai.processRound(game);
			JBSCoreGame.ioQueue.insertInput("AI " + game.getActivePlayer().getName() + " ended its turn!", JBSCoreGame.MSG_LOGGER_KEY);
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
