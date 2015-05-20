package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.JBSGameField;

public class GameFieldPanel extends JPanel {
	
	private JBSGameField gamefild;
	
	private int gridSize;
	private int fildSize;
	
	private int xofset;
	private int yofset;
	
	private Color gridColor;
	private Color selectColor;
	private boolean isSelected;
	private int selectx;
	private int selecty;
	
	private Direction direction; 
	
	private JPanel panel = this;
	
	public GameFieldPanel(JBSGameField fild ,int size) {
		
		this.gamefild = fild;
		gridColor = Color.RED;
		selectColor = new Color(100,100,100,100);
		
		isSelected = false;
		selectx = 0;
		selecty = 0;
		
		direction = Direction.NORTH;
		
		//TODO SIZE
		setSize(new Dimension(size,size));
		setMinimumSize(new Dimension(size,size) );
		setBackground(Color.CYAN);
		
		fildSize = 600;
		xofset = 0;
		yofset = 0;
		
		//TODO test
		//TEST
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				isSelected = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				isSelected = true;				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton() == 3){
					if(direction == Direction.NORTH){
						direction = Direction.EAST;
					}else if(direction == Direction.EAST){
						direction = Direction.SOUTH;
					}else if(direction == Direction.SOUTH){
						direction = Direction.WEST;
					}else if(direction == Direction.WEST){
						direction = Direction.NORTH;
					}
				}
				
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(isSelected){
					if(selectx != ((e.getX()-xofset)-((e.getX()-xofset)%gridSize))/gridSize ||
							selecty != ((e.getY()-yofset)-((e.getY()-yofset)%gridSize))/gridSize){
						panel.repaint();
					}
					
					if(((e.getX()-xofset)-((e.getX()-xofset)%gridSize))/gridSize < fild.getSize()){
						selectx = ((e.getX()-xofset)-((e.getX()-xofset)%gridSize))/gridSize;
					}
					if(((e.getY()-yofset)-((e.getY()-yofset)%gridSize))/gridSize < fild.getSize()){
						selecty = ((e.getY()-yofset)-((e.getY()-yofset)%gridSize))/gridSize;
					}
				}
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
	}
	
	

	/**
	 * @return the fild
	 */
	public JBSGameField getGamefild() {
		return gamefild;
	}

	/**
	 * @param fild the fild to set
	 */
	public void setGamefild(JBSGameField fild) {
		this.repaint();
		this.gamefild = fild;
	}

	private void drawGrid(Graphics g){
		
		gridSize = fildSize/gamefild.getSize();
		xofset = (getSize().width-fildSize)/2;
		yofset = (getSize().height-fildSize)/2;
		
		for(int i = 0 ; i < gamefild.getSize() ; i++){
			for(int j = 0 ; j < gamefild.getSize() ; j++ ){
				if(gamefild.isFieldWater(i, j)){
					g.setColor(Color.BLUE);				
					g.fillRect(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize-1, gridSize-1);
					
				}else if(gamefild.isFieldWaterHit(i, j)){
					g.setColor(Color.BLUE);
					g.fillRect(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize-1, gridSize-1);
				}else{
					g.setColor(Color.GRAY);
					g.fillRect(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize-1, gridSize-1);
				}
				if(gamefild.getField(i, j).isHit()){
					g.setColor(Color.RED);
					g.drawLine(gridSize*i+xofset+1, gridSize*j+yofset+1, gridSize*(i+1)+xofset-1, gridSize*(j+1)+yofset-1);
					g.drawLine(gridSize*i+xofset+1, gridSize*(j+1)+yofset+1, gridSize*(i+1)+xofset-1, gridSize*j+yofset-1);
				}
				
			}
		}
		
		if(isSelected){
			g.setColor(selectColor);		
			g.fillRect(selectx*gridSize+xofset, selecty*gridSize+yofset, gridSize, gridSize);
			
			g.setColor(Color.WHITE);			
			if(direction == Direction.NORTH){
				g.drawString("^", selectx*gridSize+xofset+gridSize/2, selecty*gridSize+yofset+gridSize/2);
				panel.repaint();
			}else if(direction == Direction.EAST){
				g.drawString(">", selectx*gridSize+xofset+gridSize/2, selecty*gridSize+yofset+gridSize/2);
				panel.repaint();
			}else if(direction == Direction.SOUTH){
				g.drawString("v", selectx*gridSize+xofset+gridSize/2, selecty*gridSize+yofset+gridSize/2);
				panel.repaint();
			}else if(direction == Direction.WEST){
				g.drawString("<", selectx*gridSize+xofset+gridSize/2, selecty*gridSize+yofset+gridSize/2);
				panel.repaint();
			}
		}
		
		g.setColor(gridColor);
		
		for (int i = 0 ; i < gamefild.getSize()+1 ; i++){
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

	/**
	 * @return the selectColor
	 */
	public Color getSelectColor() {
		return selectColor;
	}

	/**
	 * @param selectColor the selectColor to set
	 */
	public void setSelectColor(Color selectColor) {
		this.selectColor = selectColor;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @return the selectx
	 */
	public int getSelectx() {
		return selectx;
	}

	/**
	 * @return the selecty
	 */
	public int getSelecty() {
		return selecty;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}
}
