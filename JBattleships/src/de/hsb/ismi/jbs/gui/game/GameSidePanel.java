package de.hsb.ismi.jbs.gui.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.actors.ships.JBSCorvette;
import de.hsb.ismi.jbs.engine.actors.ships.JBSDestroyer;
import de.hsb.ismi.jbs.engine.actors.ships.JBSFrigate;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShip;
import de.hsb.ismi.jbs.engine.actors.ships.JBSSubmarine;
import de.hsb.ismi.jbs.engine.players.JBSPlayer;
import de.hsb.ismi.jbs.start.JBattleships;

public class GameSidePanel extends JPanel {
	
	private JBSPlayer player;
	private Color buttonColor;
	private Color textColor;
	private Color cooldownColor;
	private Color healthColor;
	private Color dethColor;
	private Color selectedColor;
	private Color hoverColor;
	private int height;
	private int width;
	private int buttonheight;
	private int buttonwidth;
	private int nameheightoffset;
	private int namewidthoffset;
	private boolean isSelected;
	private int selectedship;
	private int hovership;
	private int hovery = 0;
	
	private GameSidePanel panel;
	
	public GameSidePanel(JBSPlayer player) {
				
		this.player = player;
		buttonColor = Color.LIGHT_GRAY;
		textColor = Color.BLACK;
		cooldownColor = Color.BLUE;
		healthColor = Color.GREEN;
		dethColor = Color.RED;
		selectedColor = new Color(100,100,100,100);
		hoverColor = new Color(100,100,100,50);
		
		setBackground(Color.GRAY);
		
		height = getSize().height;
		width = getSize().width;
		
		nameheightoffset = 20;
		namewidthoffset = 10;
		
		panel  = this;
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(isSelected){
					if(hovery != (e.getY()-(e.getY()%buttonheight))/buttonheight){
						hovery = (e.getY()-(e.getY()%buttonheight))/buttonheight;
						panel.repaint();
					}							
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				isSelected = false;
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				isSelected = true;
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == 1){
					selectedship = hovery;
					panel.repaint();
				}
			}
		});
		
		
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
		
		g.setColor(selectedColor);
		g.fillRect(0, selectedship*buttonheight, buttonwidth, buttonheight);
		
		g.setColor(hoverColor);
		g.fillRect(0, hovery*buttonheight, buttonwidth, buttonheight);
		
	}
	
	private void printButton(Graphics g, int x, int y, JBSShip ship) {
		
		g.setColor(buttonColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(textColor);
		String name = "";
		if(ship instanceof JBSDestroyer){
			name = JBattleships.game.getLocalization("GAME_DESTROYER");
		}else if(ship instanceof JBSFrigate){
			name = JBattleships.game.getLocalization("GAME_FRIGATE");
		}else if(ship instanceof JBSCorvette){
			name = JBattleships.game.getLocalization("GAME_CORVETTE");
		}else if(ship instanceof JBSSubmarine){
			name = JBattleships.game.getLocalization("GAME_SUBMARINE");
		}
		g.drawString(name, x+namewidthoffset, y+nameheightoffset);
		
		if(ship.isAlive()){
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

	public Color getButtonColor() {
		return buttonColor;
	}

	public void setButtonColor(Color buttonColor) {
		this.buttonColor = buttonColor;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the cooldownColor
	 */
	public Color getCooldownColor() {
		return cooldownColor;
	}

	/**
	 * @param cooldownColor the cooldownColor to set
	 */
	public void setCooldownColor(Color cooldownColor) {
		this.cooldownColor = cooldownColor;
	}

	/**
	 * @return the healthColor
	 */
	public Color getHealthColor() {
		return healthColor;
	}

	/**
	 * @param healthColor the healthColor to set
	 */
	public void setHealthColor(Color healthColor) {
		this.healthColor = healthColor;
	}

	/**
	 * @return the dethColor
	 */
	public Color getDethColor() {
		return dethColor;
	}

	/**
	 * @param dethColor the dethColor to set
	 */
	public void setDethColor(Color dethColor) {
		this.dethColor = dethColor;
	}

	/**
	 * @return the selectedColor
	 */
	public Color getSelectedColor() {
		return selectedColor;
	}

	/**
	 * @param selectedColor the selectedColor to set
	 */
	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	/**
	 * @return the hoverColor
	 */
	public Color getHoverColor() {
		return hoverColor;
	}

	/**
	 * @param hoverColor the hoverColor to set
	 */
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

	/**
	 * @return the player
	 */
	public JBSPlayer getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(JBSPlayer player) {
		this.player = player;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * @return the selectedship
	 */
	public int getSelectedshipInt() {
		return selectedship;
	}
	
	public JBSShip getSelectedship(){
		return player.getShips().get(selectedship);
	}
	
	/**
	 * @param selectedship the selectedship to set
	 */
	public void setSelectedship(int selectedship) {
		this.selectedship = selectedship;
	}
}
