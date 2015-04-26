package de.hsb.ismi.jbs.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.UUID;

import javax.swing.JSplitPane;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSActor;
import de.hsb.ismi.jbs.engine.core.JBSCorvette;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSFrigate;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSSubmarine;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;

public class GameFieldContainer extends JPanel {
	
	private GameSidePanel uperSiedPanel;
	private JPanel lowerSidePanel;
	private JPanel uperMainPanel;
	private JPanel lowerMainPanel;
	
	private Game game;
	
	
	public GameFieldContainer(Game game) {
		
		this.game = game;
		init();
	}
	
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		add(splitPane);
		
		uperSiedPanel = new GameSidePanel();
		
		splitPane.setRightComponent(uperSiedPanel);
		
		uperMainPanel = new GameFieldPanel();
		splitPane.setLeftComponent(uperMainPanel);
		uperMainPanel.setLayout(new BorderLayout(0, 0));
		
		lowerMainPanel = new JPanel();
		uperMainPanel.add(lowerMainPanel, BorderLayout.SOUTH);
		
		loadShips();
		
	}
	
	public void loadShips(){
		
		// TODO need Activ player
		for(JBSShip ship : game.getPlayers()[game.getActivPlayer()].getShips()){
			uperSiedPanel.addShip(ship);
		}
	}
	
	public void loadShips(int player){
		
		// TODO need Activ player
		for(JBSShip ship : game.getPlayers()[player].getShips()){
			uperSiedPanel.addShip(ship);
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(100, 100, 600, 500);
		
		Game game = new Game();
		
		JBSPlayer[] players = new JBSPlayer[2];
		
		players[0] = new JBSPlayer();
		players[0].addShip(new JBSDestroyer());
		players[0].addShip(new JBSCorvette());
		players[0].addShip(new JBSFrigate());
		players[0].addShip(new JBSSubmarine());
		players[0].addShip(new JBSDestroyer());
		players[0].addShip(new JBSCorvette());
		players[0].addShip(new JBSFrigate());
		players[0].addShip(new JBSSubmarine());
		players[0].addShip(new JBSDestroyer());
		players[0].addShip(new JBSCorvette());
		players[0].addShip(new JBSFrigate());
		players[0].addShip(new JBSSubmarine());
		
		game.setPlayers(players);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new GameFieldContainer(game));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
}
