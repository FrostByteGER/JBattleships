package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.core.JBSActor;

public class GameFieldPanel extends JPanel {
	
	private JBSActor[][] filds;
	private int gridSize;
	private int fildSize;
	
	private int xofset;
	private int yofset;
	
	private Color gridColor;
	
	public GameFieldPanel(int size) {
		//TODO SIZE
		setSize(new Dimension(size,size));
		setMinimumSize(new Dimension(size,size) );
		setBackground(Color.BLUE);
		
		gridColor = Color.RED;
		
		fildSize = 600;
		xofset = 0;
		yofset = 0;
		
		
		//TODO test
		filds = new JBSActor[16][17];
		
		for(int i = 0 ; i < 16 ; i++){
			for(int j = 0 ; j < 16 ; j++){
				filds[i][j] = new JBSActor();
			}
		}
		//TEST
	}

	/**
	 * @param filds the filds to set
	 */
	public void setFilds(JBSActor[][] filds) {
		this.filds = filds;
	}

	private void drawGrid(Graphics g){
		
		gridSize = fildSize/filds.length;
		xofset = (getSize().width-fildSize)/2;
		yofset = (getSize().height-fildSize)/2;
		
		g.setColor(gridColor);
		
		for (int i = 1 ; i < filds.length ; i++){
			g.drawLine(xofset, yofset+i*gridSize,xofset+fildSize, yofset+i*gridSize);
			g.drawLine(xofset+i*gridSize, yofset, xofset+i*gridSize, yofset+fildSize);
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(xofset, yofset, fildSize, fildSize);
		drawGrid(g);
	}

	/**
	 * @return the gridColor
	 */
	public Color getGridColor() {
		return gridColor;
	}

	/**
	 * @param gridColor the gridColor to set
	 */
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
	}
	
	
	
}
