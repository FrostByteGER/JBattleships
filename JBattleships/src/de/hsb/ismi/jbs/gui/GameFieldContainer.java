package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import de.frostbyteger.messagelogger.MessageLogger;
import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSProfile;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.engine.core.manager.RoundManager;
import de.hsb.ismi.jbs.start.JBattleships;

public class GameFieldContainer extends JPanel {
	
	private GameSidePanel2 centerSiedPanel;
	private JPanel sidePanel;
	private JPanel lowerSidePanel;
	private GameFieldPanel mainPanel;
	private JPanel lowerMainPanel;
	private JPanel uperMainPanel;
	private Game game;
	private JLabel fieldNumber;
	private RoundManager roundManager;
	private int selectedGameField;
	private JTextArea chat;
	private JButton shoot;
	private JButton pass;
	private JLabel save;
	private JButton end;
	
	public GameFieldContainer(Game game) {
		
		this.game = game;
		init();
	}
	
	private void init() {
		
		selectedGameField = 0;
		
		// change ?
		roundManager = JBattleships.game.getGameManager().getRoundManager();
		
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());
		
		centerSiedPanel = new GameSidePanel2(game.getPlayers()[game.getActivePlayerInt()]);
		sidePanel.add(centerSiedPanel, BorderLayout.CENTER);
		
		shoot = new JButton("Shoot");
		
		shoot.setActionCommand("shoot");
		shoot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "shoot"){
					
					if(game.getActivePlayerInt() == selectedGameField){
						chat.setText(chat.getText()+"\nDont shoot yourself");
					}else{
					
						if(centerSiedPanel.getSelectedship().canShot()){
							
							roundManager.fireRound(game.getPlayer(selectedGameField), game.getActivePlayer(), centerSiedPanel.getSelectedship(), mainPanel.getSelectx(), mainPanel.getSelecty(), mainPanel.getDirection());
							
				
							
							// needs to be in teh Round Manager TODO
							roundManager.fireEndRound(game.getActivePlayer());
							
							centerSiedPanel.repaint();

							
							//game.setActivePlayer((game.getActivePlayer()+1)%game.getPlayers().length);
												
							centerSiedPanel.setPlayer(game.getPlayer(game.getActivePlayerInt()));
							mainPanel.setGamefild(game.getPlayer(game.getActivePlayerInt()).getPlayerField());
						
						}else{
							chat.setText(chat.getText()+"\nCan´t shoot");
						}
					
					}
					
				}
			}
		});
		
		pass = new JButton("Pass");
		pass.setActionCommand("pass");
		pass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "pass"){
					
					game.getPlayer(game.getActivePlayerInt()).decreaseCooldownAll();
						
					centerSiedPanel.repaint();
					
					game.setActivePlayerInt((game.getActivePlayerInt()+1)%game.getPlayers().length);
											
					centerSiedPanel.setPlayer(game.getPlayer(game.getActivePlayerInt()));
					mainPanel.setGamefild(game.getPlayer(game.getActivePlayerInt()).getPlayerField());
						
				}
				
			}
		});
		
		lowerSidePanel = new JPanel();
		
		lowerSidePanel.setLayout(new FlowLayout());
		lowerSidePanel.add(shoot);
		lowerSidePanel.add(pass);
		
		sidePanel.add(lowerSidePanel,BorderLayout.SOUTH);
		
		splitPane.setRightComponent(sidePanel);
		
		mainPanel = new GameFieldPanel(game.getPlayers()[game.getActivePlayerInt()].getPlayerField(),500,50);
		splitPane.setLeftComponent(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		chat = new JTextArea();
		chat.setText("Chat Text");
		
		lowerMainPanel = new JPanel();
		lowerMainPanel.setLayout(new BorderLayout());
		lowerMainPanel.add(new JScrollPane(chat), BorderLayout.CENTER);
		
		mainPanel.add(lowerMainPanel, BorderLayout.SOUTH);
		

		
		uperMainPanel = new JPanel();
		uperMainPanel.setLayout(new FlowLayout());
		
		JButton pbut = new JButton();
		pbut.setText(JBattleships.game.getDataManager().getLocalizationManager().getLocalization("Plus"));
		pbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				if(game.getPlayers().length-1>selectedGameField){
					selectedGameField++;

				}else if(game.getPlayers().length-1 == selectedGameField){
					selectedGameField = 0;
				}
				fieldNumber.setText(game.getPlayer(selectedGameField).getName()+selectedGameField);

				mainPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
			}
		});
		
		JButton mbut = new JButton();
		mbut.setText(JBattleships.game.getDataManager().getLocalizationManager().getLocalization("Minus"));
		mbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGameField>0){
					selectedGameField--;

				}else if(selectedGameField == 0){
					selectedGameField = game.getPlayers().length-1;
				}
				fieldNumber.setText(game.getPlayer(selectedGameField).getName()+selectedGameField);
				
				mainPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
			}
		});
		
		fieldNumber = new JLabel();
		
		fieldNumber.setText(String.valueOf(selectedGameField));
		
		uperMainPanel.add(mbut);
		uperMainPanel.add(fieldNumber);
		uperMainPanel.add(pbut);
		
		mainPanel.add(uperMainPanel,BorderLayout.NORTH);
		
	}

	public static void main(String[] args) {

		new JBSCoreGame(true);
		
		GameManager pre = new GameManager();
		
		pre.addPlayer(new JBSPlayer(new JBSProfile()));
		pre.addPlayer(new JBSPlayer(new JBSProfile()));
		
		pre.setDestroyerCount(1);	
		pre.setCorvetteCount(4);
		pre.setFrigateCount(3);
		pre.setSubmarineCount(3);
		
		JBSCoreGame core = new JBSCoreGame(true);
		
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1000, 800);
		
		Game game = pre.createGame(JBSGameType.GAME_LOCAL, 16);
		
		game.getPlayers()[0].getShips().get(0).setPositon(0, 0, Direction.SOUTH);
		game.getPlayers()[0].getPlayerField().setShip(game.getPlayers()[0].getShips().get(0));
		game.getPlayers()[0].getShips().get(0).setHealth(3);
		game.getPlayers()[0].getShips().get(0).setCooldown(2);
		game.getPlayers()[1].getShips().get(0).setPositon(0, 0, Direction.SOUTH);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new GameFieldContainer(game));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}
