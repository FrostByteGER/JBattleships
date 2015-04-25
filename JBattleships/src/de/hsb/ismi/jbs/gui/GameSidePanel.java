package de.hsb.ismi.jbs.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.core.JBSCorvette;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSFrigate;
import de.hsb.ismi.jbs.engine.core.JBSShip;
import de.hsb.ismi.jbs.engine.core.JBSSubmarine;

public class GameSidePanel extends JPanel {
	public GameSidePanel() {
		init();
	}
	
	private void init() {
		
		//TODO test
		
		JBSFrigate f = new JBSFrigate();
		JBSDestroyer f2 = new JBSDestroyer();
		JBSFrigate f3 = new JBSFrigate();
		JBSDestroyer f4 = new JBSDestroyer();
		JBSFrigate f5 = new JBSFrigate();
		JBSDestroyer f6 = new JBSDestroyer();
		
		setLayout(new GridLayout(0,1));
		add(new ShipButton(f));
		add(new ShipButton(f2));
		add(new ShipButton(f3));
		add(new ShipButton(f4));
		add(new ShipButton(f5));
		add(new ShipButton(f6));
	}
	
	public class ShipButton extends JPanel {
		
		private JBSShip ship;
		private JButton shipButton;
		
		
		public ShipButton(JBSShip ship) {
			this.ship = ship;
		
			shipButton = new JButton(ship.getName());
			
			shipButton.setText(shipButton.getText()+" "+ship.getCooldown()+"/"+ship.getCooldownLimit()+" "+ship.getHealth()+" "+ship.getLength());
			
			setLayout(new BorderLayout());
			add(shipButton ,BorderLayout.CENTER);
		}
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		GameSidePanel t = new GameSidePanel();
		f.setContentPane(t);
		f.setVisible(true);
		
	}
	
}
