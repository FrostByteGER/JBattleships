/**
 * 
 */
package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.Box.Filler;

import de.hsb.ismi.jbs.engine.core.JBSShip;

/**
 * @author Jan Schult
 *
 */
public class ShipStatLabel extends JLabel {

	private JBSShip ship = null;
	private Color health = new Color(1f-getHealthP(), getHealthP(), 0f);
	private Color cooldown = new Color(1f-getCooldownP(),1f-getCooldownP(),1f);
	
	public ShipStatLabel(JBSShip ship) {
		this.ship =ship;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(health);
		g.fillRect(0, 0,(int)(getWidth()*((float)ship.getHealth()/(float)ship.getLength())), getHeight()/2);
		g.setColor(cooldown);
		g.fillRect(0 , getHeight()/2, (int)(getWidth()*((float)ship.getCooldown()/(float)ship.getCooldownLimit())), getHeight());
	}
	
	public void updateColor(){
		health = new Color(1F-getHealthP(), getHealthP(), 0F);
		cooldown = new Color(1F-getCooldownP(),1F-getCooldownP(),1F);
	}
	
	private float getHealthP(){
		return (((float)ship.getHealth())/((float)ship.getLength()));
	}
	
	private float getCooldownP(){
		return (((float)ship.getCooldown())/((float)ship.getCooldownLimit()));
	}
	
}
