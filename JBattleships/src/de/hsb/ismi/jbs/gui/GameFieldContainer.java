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

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSCorvette;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSFrigate;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSGameType;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSSubmarine;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.io.manager.DataManager;

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
		
		mainPanel = new GameFieldPanel(game.getGameField()[0],500,50);
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
				if(game.getGameField().length-1>selectedGameField){
					selectedGameField++;
					fieldNumber.setText(String.valueOf(selectedGameField));
					
					mainPanel.setGamefild(game.getGameField()[selectedGameField]);
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
					
					mainPanel.setGamefild(game.getGameField()[selectedGameField]);
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
		
		for(JBSShip ship : game.getPlayers()[game.getActivPlayer()].getShips()){
		//	uperSiedPanel.addShip(ship);
		}
	}
	
	public void loadShips(int player){
		
		for(JBSShip ship : game.getPlayers()[player].getShips()){
		//	uperSiedPanel.addShip(ship);
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 1000, 800);
		
		JBSPlayer[] p = new JBSPlayer[2];
		p[0] = new JBSPlayer();
		p[1] = new JBSPlayer();
		
		JBSGameField[] fi = new JBSGameField[2];
		fi[0] = new JBSGameField(p[0],12);
		fi[1] = new JBSGameField(p[1],12);
		
		DataManager dm = new DataManager();
		
		JBSShip s = new JBSDestroyer(dm);
		
		s.setHealth(3);
		s.setCooldown(2);
		
		p[0].addShip(s);
		p[0].addShip(new JBSCorvette(dm));
		p[0].addShip(new JBSFrigate(dm));
		p[0].addShip(new JBSSubmarine(dm));
		p[0].addShip(new JBSDestroyer(dm));
		p[0].addShip(new JBSCorvette(dm));
		p[0].addShip(new JBSFrigate(dm));
		p[0].addShip(new JBSSubmarine(dm));
		p[0].addShip(new JBSDestroyer(dm));
		p[0].addShip(new JBSCorvette(dm));
		p[0].addShip(new JBSFrigate(dm));
		p[0].addShip(new JBSSubmarine(dm));
		
		p[1].addShip(new JBSCorvette(dm));
		
		p[0].getShips().get(0).setPositon(0, 0, Direction.NORTH);
		
		fi[0].setShip(p[0].getShips().get(0));
		
		p[1].getShips().get(0).setPositon(0, 0, Direction.WEST);
		
		fi[1].setShip(p[1].getShips().get(0));
		
		Game game = new Game(JBSGameType.GAME_LOCAL, fi, p);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new GameFieldContainer(game));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}
