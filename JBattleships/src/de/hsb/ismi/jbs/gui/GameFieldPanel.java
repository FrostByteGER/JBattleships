package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import de.hsb.ismi.jbs.engine.core.JBSGameField;

public class GameFieldPanel extends JPanel {
	
	private JBSGameField fild;
	
	private int gridSize;
	private int fildSize;
	
	private int xofset;
	private int yofset;
	
	private Color gridColor;
	
	public GameFieldPanel(JBSGameField fild ,int size) {
		
		this.fild = fild;
		
		//TODO SIZE
		setSize(new Dimension(size,size));
		setMinimumSize(new Dimension(size,size) );
		setBackground(Color.CYAN);
		
		gridColor = Color.RED;
		
		fildSize = 600;
		xofset = 0;
		yofset = 0;
		
		//TODO test
		//TEST
	}
	
	

	/**
	 * @return the fild
	 */
	public JBSGameField getFild() {
		return fild;
	}

	/**
	 * @param fild the fild to set
	 */
	public void setFild(JBSGameField fild) {
		this.fild = fild;
	}

	private void drawGrid(Graphics g){
		
		gridSize = fildSize/fild.getSize();
		xofset = (getSize().width-fildSize)/2;
		yofset = (getSize().height-fildSize)/2;
		
		for(int i = 0 ; i < fild.getSize() ; i++){
			for(int j = 0 ; j < fild.getSize() ; j++ ){
				if(fild.isFieldWater(i, j)){
					g.setColor(Color.BLUE);				
					g.fillRect(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize-1, gridSize-1);
					
				}else if(fild.isFieldWaterHit(i, j)){
					g.setColor(Color.BLUE);
					g.fillRect(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize-1, gridSize-1);
				}else{
					g.setColor(Color.GRAY);
					g.fillRect(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize-1, gridSize-1);
				}
				if(fild.getField(i, j).isHit()){
					g.setColor(Color.RED);
					g.drawLine(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize*(i+1)+xofset-1, gridSize*(j+1)+yofset-1);
					g.drawLine(gridSize*i+xofset+1, gridSize*(j+1)+yofset+1, gridSize*(i+1)+xofset-1, gridSize*j+yofset-1);
				}
				
			}
		}
		
		g.setColor(gridColor);
		
		for (int i = 0 ; i < fild.getSize()+1 ; i++){
			g.drawLine(xofset, yofset+i*gridSize,xofset+fildSize, yofset+i*gridSize);
			g.drawLine(xofset+i*gridSize, yofset, xofset+i*gridSize, yofset+fildSize);
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.LIGHT_GRAY);
		//g.fillRect(xofset, yofset, fildSize, fildSize);
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
