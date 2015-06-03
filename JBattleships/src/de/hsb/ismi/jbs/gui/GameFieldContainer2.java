/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSProfile;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.engine.core.manager.RoundManager;
import de.hsb.ismi.jbs.engine.rendering.Resolution;
import de.hsb.ismi.jbs.engine.rendering.ScreenMode;
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
	private RoundManager roundManager;
	private int selectedGameField;
	private JTextArea chat;
	private JButton btnExit;
	private JButton btnShoot;
	private JButton btnEndRound;
	private JButton btnSaveGame;
	private ChatPanel chatPanel;
	
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
		
		sidePanel.add(gameSidePanel, "cell 0 0,height :75%:,grow");

		sidePanel.add(chatPanel, "cell 0 1,height :25%:,grow");
		
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
							
				
							
							roundManager.fireEndRound();
							
							
							
							//game.setActivePlayer((game.getActivePlayer()+1)%game.getPlayers().length);
												
							gameSidePanel.setPlayer(game.getPlayer(game.getActivePlayerInt()));
							gameFieldPanel.setGamefild(game.getPlayer(game.getActivePlayerInt()).getPlayerField());
							gameSidePanel.repaint();
						
						}else{
							chat.setText(chat.getText()+"\nCan´t shoot");
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
					
					game.getPlayer(game.getActivePlayerInt()).decreaseCooldownAll();
					roundManager.fireEndRound();	
					gameSidePanel.repaint();
										
					
					gameSidePanel.setPlayer(game.getPlayer(game.getActivePlayerInt()));
					gameFieldPanel.setGamefild(game.getPlayer(game.getActivePlayerInt()).getPlayerField());
						
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
		
		gameFieldPanel.setGamefild(game.getPlayer(game.getActivePlayerInt()).getPlayerField());
		
		
		
	}
	
	public static void main(String[] args) {
		
		JBSCore.msgLogger = new MessageLogger(JBSCore.DEBUG);
		// Sets the Position so the MessageLogger is a bit better visible
		JBSCore.msgLogger.getFrame().setLocation(10, 10);

		JBSCore core = JBattleships.game;
		core.generateGame();
		GameManager pre = core.getGameManager();
		
		pre.addPlayer(new JBSPlayer(new JBSProfile()));
		pre.addPlayer(new JBSPlayer(new JBSProfile()));
		
		pre.setDestroyerCount(1);	
		pre.setCorvetteCount(4);
		pre.setFrigateCount(3);
		pre.setSubmarineCount(3);
		
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1000, 800);
		
		Game game = pre.createGame(JBSGameType.GAME_LOCAL, 16);
		////
		//TODO: Change to own thread, otherwise, blocks GUI thread!!!
		//TODO: Possibly fixed, might check for thread-bugs
		JOptionPane.showMessageDialog(f, "Be careful, Game-Thread might be buggy!");
		pre.startGame();
		////
		game.getPlayers()[0].getShips().get(0).setPositon(0, 0, Direction.SOUTH);
		game.getPlayers()[0].getPlayerField().setShip(game.getPlayers()[0].getShips().get(0));
		game.getPlayers()[0].getShips().get(0).setHealth(3);
		game.getPlayers()[0].getShips().get(0).setCooldown(2);
		game.getPlayers()[1].getShips().get(0).setPositon(0, 0, Direction.SOUTH);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new GameFieldContainer2(new JBSGUI(new Resolution(0,0),ScreenMode.MODE_BORDERLESS)));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
