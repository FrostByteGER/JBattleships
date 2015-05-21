package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.core.JBSPlayer;
import de.hsb.ismi.jbs.engine.core.JBSShip;

public class GameSidePanel2 extends JPanel {
	
	private JBSPlayer player;
	private Color buttonColor;
	private Color textColor;
	private Color cooldownColor;
	private Color healthColor;
	private Color dethColor;
	private int height;
	private int width;
	private int buttonheight;
	private int buttonwidth;
	private int nameheightoffset;
	private int namewidthoffset;
	
	public GameSidePanel2(JBSPlayer player) {
		this.player = player;
		buttonColor = Color.LIGHT_GRAY;
		textColor = Color.BLACK;
		cooldownColor = Color.BLUE;
		healthColor = Color.GREEN;
		dethColor = Color.RED;
		
		setBackground(Color.GRAY);
		
		height = getSize().height;
		width = getSize().width;
		
		nameheightoffset = 20;
		namewidthoffset = 10;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		height = getSize().height;
		width = getSize().width;
		
		buttonheight = height/player.getShips().size();
		buttonwidth = width/2; // TODO offset
		
		for(int i = 0 ; i < player.getShips().size() ; i++){		
			printButton(g, 0, i*buttonheight, player.getShips().get(i));	
		}
	}
	
	private void printButton(Graphics g, int x, int y, JBSShip ship) {
		
		g.setColor(buttonColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(textColor);
		g.drawString(ship.getName(), x+namewidthoffset, y+nameheightoffset);
		
		if(ship.isAlife()){
			g.setColor(healthColor);
			g.fillRect(x+buttonwidth, y, (int)(buttonwidth*getHealthP(ship)), buttonheight/2);
		}else{
			g.setColor(dethColor);
			g.fillRect(x+buttonwidth, y, buttonwidth, buttonheight/2);
		}

		g.setColor(cooldownColor);
		g.fillRect(x+buttonwidth, y+buttonheight/2, (int)(buttonwidth*getCooldownP(ship)), buttonheight/2);
	}
	
	private float getHealthP(JBSShip ship){
		return (((float)ship.getHealth())/((float)ship.getLength()));
	}
	
	private float getCooldownP(JBSShip ship){
		return (((float)ship.getCooldown())/((float)ship.getCooldownLimit()));
	}
	
	
}
