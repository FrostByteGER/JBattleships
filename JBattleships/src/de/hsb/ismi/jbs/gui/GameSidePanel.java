package de.hsb.ismi.jbs.gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import de.hsb.ismi.jbs.engine.core.JBSShip;

public class GameSidePanel extends JPanel {
	public GameSidePanel() {
		init();
	}
	
	private void init() {
		
		setLayout(new GridLayout(0,1));
		
	}
	
	public void addShip(JBSShip ship){
		add(new ShipButton(ship));
	}
	
	public void removeShips(){
		removeAll();
	}
	
	public class ShipButton extends JPanel {
		
		private JBSShip ship;
		private JButton shipButton;
		private ShipStatLabel stats;
		
		public ShipButton(JBSShip ship) {
			this.ship = ship;
		
			shipButton = new JButton(ship.getName());
			stats = new ShipStatLabel(ship);
			
			shipButton.setText(shipButton.getText()+" "+ship.getCooldown()+"/"+ship.getCooldownLimit()+" "+ship.getHealth()+"/"+ship.getLength());
			
			setLayout(new GridLayout());
			add(shipButton);
			add(stats);
		}
	}
}
