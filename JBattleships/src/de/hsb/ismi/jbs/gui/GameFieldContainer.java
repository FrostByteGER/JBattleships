package de.hsb.ismi.jbs.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSplitPane;
import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;

public class GameFieldContainer extends JPanel {
	
	private GameSidePanel2 uperSiedPanel;
	private JPanel lowerSidePanel;
	private GameFieldPanel mainPanel;
	private JPanel lowerMainPanel;
	private JPanel uperMainPanel;
	private Game game;
	private JLabel fieldNumber;
	
	private int selectedGameField;
	private int selectedShip;
	
	public GameFieldContainer(Game game) {
		
		this.game = game;
		init();
	}
	
	private void init() {
		
		selectedGameField = 0;
		
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		uperSiedPanel = new GameSidePanel2(game.getPlayers()[0]);
		
		splitPane.setRightComponent(uperSiedPanel);
		
		mainPanel = new GameFieldPanel(game.getPlayers()[0].getPlayerField(),500,50);
		splitPane.setLeftComponent(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		lowerMainPanel = new JPanel();
		mainPanel.add(lowerMainPanel, BorderLayout.SOUTH);
		
		uperMainPanel = new JPanel();
		uperMainPanel.setLayout(new FlowLayout());
		
		JButton pbut = new JButton();
		pbut.setText(game.getDataManager().getLocalizationManager().getLocalization("Plus"));
		pbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				if(game.getPlayers().length-1>selectedGameField){
					selectedGameField++;
					fieldNumber.setText(String.valueOf(selectedGameField));
					
					mainPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
				}
			}
		});
		
		JButton mbut = new JButton();
		mbut.setText(game.getDataManager().getLocalizationManager().getLocalization("Minus"));
		mbut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedGameField>0){
					selectedGameField--;
					fieldNumber.setText(String.valueOf(selectedGameField));
					
					mainPanel.setGamefild(game.getPlayer(selectedGameField).getPlayerField());
				}			
			}
		});
		
		fieldNumber = new JLabel();
		
		fieldNumber.setText(String.valueOf(selectedGameField));
		
		uperMainPanel.add(mbut);
		uperMainPanel.add(fieldNumber);
		uperMainPanel.add(pbut);
		
		mainPanel.add(uperMainPanel,BorderLayout.NORTH);
		
		loadShips();
		
	}
	
	public void loadShips(){
		
		for(JBSShip ship : game.getPlayers()[game.getActivePlayer()].getShips()){
		//	uperSiedPanel.addShip(ship);
		}
	}
	
	public void loadShips(int player){
		
		for(JBSShip ship : game.getPlayers()[player].getShips()){
		//	uperSiedPanel.addShip(ship);
		}
	}
	
	public static void main(String[] args) {
		
		GameManager pre = new GameManager();
		
		pre.addPlayer(new JBSPlayer());
		pre.addPlayer(new JBSPlayer());
		
		pre.setDestroyerCount(1);	
		pre.setCorvetteCount(4);
		pre.setFrigateCount(3);
		pre.setSubmarineCount(3);
		
		JBSCore core = new JBSCore();
		
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1000, 800);
		
		Game game = pre.createGame(JBSGameType.GAME_LOCAL, 16);
		
		game.getPlayers()[0].getShips().get(0).setPositon(0, 0, Direction.SOUTH);
		game.getPlayers()[0].getPlayerField().setShip(game.getPlayers()[0].getShips().get(0));
		game.getPlayers()[0].getShips().get(0).setHealth(3);
		game.getPlayers()[0].getShips().get(0).setCooldown(2);
		game.getPlayers()[1].getShips().get(0).setPositon(0, 0, Direction.WEST);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new GameFieldContainer(game));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}
