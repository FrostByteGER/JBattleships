/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.ai.JBSAIPlayer;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.GameListener;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.manager.RoundManager;
import de.hsb.ismi.jbs.engine.network.client.chat.ChatClient;
import de.hsb.ismi.jbs.engine.network.client.chat.ClientMessageListener;
import de.hsb.ismi.jbs.start.JBattleships;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import net.miginfocom.swing.MigLayout;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class GameFieldContainer2 extends JPanel {

	
	private JBSGUI parent;
	
	private GameSidePanel2 gameSidePanel;
	private JPanel sidePanel;
	private JPanel centerPanel;
	private JPanel lowerSidePanel;
	private GameFieldPanel gameFieldPanel;
	private JPanel lowerMainPanel;
	private JPanel uperMainPanel;
	private Game game;
	private JLabel fieldNumber;
	private JLabel activePlayerlbl;
	private RoundManager roundManager;
	private int selectedGameField;
	private JTextArea chat;
	private JButton btnExit;
	private JButton btnShoot;
	private JButton btnEndRound;
	private JButton btnSaveGame;
	private ChatPanel chatPanel;
	private ChatClient chatClient;
	
	/**
	 * Create the panel.
	 */
	public GameFieldContainer2(JBSGUI parent) {
		this.parent = parent;
		this.game = JBattleships.game.getGameManager().getGame();
		setLayout(new BorderLayout(0, 0));
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new MigLayout("", "[grow][grow]", "[grow]"));
				
		chatPanel = new ChatPanel(true);
		sidePanel = new JPanel();
		
		
		selectedGameField = 0;
		
		roundManager = JBattleships.game.getGameManager().getRoundManager();
		
		gameSidePanel = new GameSidePanel2(game.getPlayers()[game.getActivePlayerInt()]);
		centerPanel.add(sidePanel, "cell 1 0,width :35%:,grow");
		sidePanel.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		activePlayerlbl = new JLabel("Active Player: " + game.getActivePlayer().getName());
		activePlayerlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		sidePanel.add(activePlayerlbl, "cell 0 0,height :10%:,grow");
		sidePanel.add(gameSidePanel, "cell 0 1,height :65%:,grow");

		sidePanel.add(chatPanel, "cell 0 2,height :25%:,grow");
		
		btnExit = new JButton("Exit Game");
		btnExit.setActionCommand("exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.swapContainer(parent.getMainPanel());
			}
		});
		btnShoot = new JButton("Shoot");
		
		btnShoot.setActionCommand("shoot");
		btnShoot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "shoot"){
					
					if(game.getActivePlayerInt() == selectedGameField){
						chat.setText(chat.getText()+"\nDont shoot yourself");
					}else{
						if(gameSidePanel.getSelectedship().canShot()){
							roundManager.fireRound(game.getPlayer(selectedGameField), game.getActivePlayer(), gameSidePanel.getSelectedship(), gameFieldPanel.getSelectx(), gameFieldPanel.getSelecty(), gameFieldPanel.getDirection());
							roundManager.fireAnalyzeRound(game.getActivePlayer());
							roundManager.fireEndRound(game.getActivePlayer());
							JBSCore.msgLogger.addMessage("Round ended for Player: " + game.getActivePlayer().getName());
							roundManager.reset();
							game.nextPlayer();
							gameSidePanel.setPlayer(game.getActivePlayer());
							gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
							gameSidePanel.repaint();
							activePlayerlbl.setText("Active Player: " + game.getActivePlayer().getName());
							if(game.getActivePlayer() instanceof JBSAIPlayer){
								JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
								JBSShip last = ai.processRound(game);
								JBSCore.msgLogger.addMessage("AI " + game.getActivePlayer().getName() + " ended its turn!");
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
							chat.setText(chat.getText()+"\nCan't shoot");
						}
					}
				}
			}
		});
		
		btnEndRound = new JButton("End Round");
		btnEndRound.setActionCommand("pass");
		btnEndRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "pass"){
					
					roundManager.fireEndRound(game.getActivePlayer());
					JBSCore.msgLogger.addMessage("Round ended for Player: " + game.getActivePlayer().getName());
					roundManager.reset();
					game.nextPlayer();
					gameSidePanel.setPlayer(game.getActivePlayer());
					gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
					gameSidePanel.repaint();
					activePlayerlbl.setText("Active Player: " + game.getActivePlayer().getName());
					if(game.getActivePlayer() instanceof JBSAIPlayer){
						JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
						JBSShip last = ai.processRound(game);
						JBSCore.msgLogger.addMessage("AI " + game.getActivePlayer().getName() + " ended its turn!");
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
		});
		
		lowerSidePanel = new JPanel();
		
		lowerSidePanel.setLayout(new FlowLayout());
		lowerSidePanel.add(btnExit);
		lowerSidePanel.add(btnShoot);
		lowerSidePanel.add(btnEndRound);
		
		add(lowerSidePanel,BorderLayout.SOUTH);
		
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.setActionCommand("save");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("save")){
					try {
						JAXBContext jaxb = JAXBContext.newInstance(Game.class);
						Marshaller m = jaxb.createMarshaller();
						m.marshal(JBattleships.game.getGameManager().getGame(), new File("Data/Saves/testsave.xml"));
					} catch (JAXBException jaxbe) {
						jaxbe.printStackTrace();
					}
				}
			}
		});
		lowerSidePanel.add(btnSaveGame);
		
		
		gameFieldPanel = new GameFieldPanel(game.getPlayers()[game.getActivePlayerInt()].getPlayerField(),500,50);
		gameFieldPanel.setLayout(new BorderLayout(0, 0));
		centerPanel.add(gameFieldPanel, "cell 0 0,width :65%:,grow");

		
		chat = new JTextArea();
		chat.setText("Chat Text");
		
		lowerMainPanel = new JPanel();
		lowerMainPanel.setLayout(new BorderLayout());
		//lowerMainPanel.add(new JScrollPane(chat), BorderLayout.CENTER);
		
		//gameFieldPanel.add(lowerMainPanel, BorderLayout.SOUTH);
		

		
		uperMainPanel = new JPanel();
		uperMainPanel.setLayout(new FlowLayout());
		
		JButton pbut = new JButton();
		pbut.setText(game.getDataManager().getLocalizationManager().getLocalization("Next"));
		pbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				if(game.getPlayers().length-1>selectedGameField){
					selectedGameField++;

				}else if(game.getPlayers().length-1 == selectedGameField){
					selectedGameField = 0;
				}
				fieldNumber.setText(game.getPlayer(selectedGameField).getName());

				gameFieldPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
			}
		});
		
		JButton mbut = new JButton();
		mbut.setText(game.getDataManager().getLocalizationManager().getLocalization("Previous"));
		mbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGameField>0){
					selectedGameField--;

				}else if(selectedGameField == 0){
					selectedGameField = game.getPlayers().length-1;
				}
				fieldNumber.setText(game.getPlayer(selectedGameField).getName());
				
				gameFieldPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
			}
		});
		
		fieldNumber = new JLabel();
				
		fieldNumber.setText(game.getPlayer(selectedGameField).getName());
		
		uperMainPanel.add(mbut);
		uperMainPanel.add(fieldNumber);
		uperMainPanel.add(pbut);
		
		gameFieldPanel.add(uperMainPanel,BorderLayout.NORTH);
		
		gameFieldPanel.setGamefild(game.getActivePlayer().getPlayerField());
		
		JBattleships.game.generateChatServer();
		chatClient = new ChatClient();
		chatClient.addMessageListener(new ClientMessageListener() {

			@Override
			public void messageReceived(String message) {
				chatPanel.addTextToChat(message);
			}
			
			@Override
			public void connectionLost(String IP) {
				chatPanel.toggleChatInput();
				
			}
		});
		
		
		JBattleships.game.getGameManager().addGameListener(new GameListener() {
			
			@Override
			public void fireStartedGame() {
				activePlayerlbl.setText("Active Player: " + game.getActivePlayer().getName());
			}
			
			@Override
			public void fireEndedGame() {
				parent.swapContainer(parent.getMainPanel());
			}
		});
		
		if(game.getActivePlayer() instanceof JBSAIPlayer){
			JBSAIPlayer ai = (JBSAIPlayer) game.getActivePlayer();
			JBSShip last = ai.processRound(game);
			JBSCore.msgLogger.addMessage("AI " + game.getActivePlayer().getName() + " ended its turn!");
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
