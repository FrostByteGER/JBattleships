package de.hsb.ismi.jbs.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.JBSGameField;

public class GameFieldPanel extends JPanel {
	
	private JBSGUI parent;
	private JBSGameField gamefild;
	
	private int gridsize;
	private int fildsize;
	
	private int xofset;
	private int yofset;
	
	private Color gridColor;
	private Color selectColor;
	private Color hoverColor;
	private boolean isSelected;
	private boolean showSelection;
	private int hoverx;
	private int hovery;
	private int selectx;
	private int selecty;
	
	private Direction direction; 
	
	private ArrayList<GameFieldActionListener> listeners;
	
	private animationThread animationthread;
		
	public GameFieldPanel(JBSGameField fild ,int fieldsize ,int size) {
		
		this.gamefild = fild;
		listeners = new ArrayList<>(0);
		gridColor = new Color(0,0,0,0);;
		selectColor = new Color(100,100,100,100);
		hoverColor = new Color(100,100,100,50);
		isSelected = false;
		showSelection = true;
		hoverx = 0;
		hovery = 0;
		
		direction = Direction.NORTH;
		
		//TODO SIZE
		setSize(new Dimension(size,size));
		setMinimumSize(new Dimension(size,size) );
		setBackground(Color.CYAN);
		
		this.fildsize = fieldsize;
		xofset = 0;
		yofset = 0;
					
		animationthread = new animationThread(this,fieldsize/gamefild.getSize());
		
		animationthread.start();
		
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
				}else if(arg0.getButton() == 1){
					selectx = hoverx;
					selecty = hovery;
					fireListeners();
				}
				
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if(isSelected){
					if(hoverx != ((e.getX()-xofset)-((e.getX()-xofset)%gridsize))/gridsize ||
							hovery != ((e.getY()-yofset)-((e.getY()-yofset)%gridsize))/gridsize){
						GameFieldPanel.this.repaint();
					}
					
					if(((e.getX()-xofset)-((e.getX()-xofset)%gridsize))/gridsize < fild.getSize() && 
							((e.getX()-xofset)-((e.getX()-xofset)%gridsize))/gridsize >= 0){
						hoverx = ((e.getX()-xofset)-((e.getX()-xofset)%gridsize))/gridsize;
					}
					if(((e.getY()-yofset)-((e.getY()-yofset)%gridsize))/gridsize < fild.getSize() &&
							((e.getY()-yofset)-((e.getY()-yofset)%gridsize))/gridsize >= 0){
						hovery = ((e.getY()-yofset)-((e.getY()-yofset)%gridsize))/gridsize;
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
		
		gridsize = fildsize/gamefild.getSize();
		xofset = (getSize().width-fildsize)/2;
		yofset = (getSize().height-fildsize)/2;
		
		for(int i = 0 ; i < gamefild.getSize() ; i++){
			for(int j = 0 ; j < gamefild.getSize() ; j++ ){
				if(gamefild.isFieldWater(i, j)){
					//g.setColor(Color.BLUE);				
					//g.fillRect(gridsize*i+xofset+1, gridsize*j+yofset+1, gridsize-1, gridsize-1);
					
					g.drawImage(gamefild.getField(i, j).getComponents().getImage(), gridsize*i+xofset, gridsize*j+yofset, null);
					
				}else if(gamefild.isFieldWaterHit(i, j)){
					g.drawImage(gamefild.getField(i, j).getComponents().getImage(), gridsize*i+xofset, gridsize*j+yofset, null);
					//g.setColor(Color.BLUE);
					//g.fillRect(gridsize*i+xofset+1, gridsize*j+yofset+1, gridsize-1, gridsize-1);
				}else{
					g.setColor(Color.GRAY);
					g.fillRect(gridsize*i+xofset, gridsize*j+yofset, gridsize, gridsize);
				}
				if(gamefild.getField(i, j).isHit()){
					
					g.setColor(Color.RED);
					g.drawLine(gridsize*i+xofset+1, gridsize*j+yofset+1, gridsize*(i+1)+xofset-1, gridsize*(j+1)+yofset-1);
					g.drawLine(gridsize*i+xofset+1, gridsize*(j+1)+yofset+1, gridsize*(i+1)+xofset-1, gridsize*j+yofset-1);
					
				}	
			}
		}
		
		if(isSelected){
			g.setColor(hoverColor);		
			g.fillRect(hoverx*gridsize+xofset, hovery*gridsize+yofset, gridsize, gridsize);
			
			g.setColor(Color.WHITE);			
			if(direction == Direction.NORTH){
				g.drawString("^", hoverx*gridsize+xofset+gridsize/2, hovery*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.EAST){
				g.drawString(">", hoverx*gridsize+xofset+gridsize/2, hovery*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.SOUTH){
				g.drawString("v", hoverx*gridsize+xofset+gridsize/2, hovery*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.WEST){
				g.drawString("<", hoverx*gridsize+xofset+gridsize/2, hovery*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}
		}
		if(showSelection){
			g.setColor(selectColor);		
			g.fillRect(selectx*gridsize+xofset, selecty*gridsize+yofset, gridsize, gridsize);
			
			g.setColor(Color.WHITE);			
			if(direction == Direction.NORTH){
				g.drawString("^", selectx*gridsize+xofset+gridsize/2, selecty*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.EAST){
				g.drawString(">", selectx*gridsize+xofset+gridsize/2, selecty*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.SOUTH){
				g.drawString("v", selectx*gridsize+xofset+gridsize/2, selecty*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.WEST){
				g.drawString("<", selectx*gridsize+xofset+gridsize/2, selecty*gridsize+yofset+gridsize/2);
				GameFieldPanel.this.repaint();
			}		
		}

		
		g.setColor(gridColor);
		
		for (int i = 0 ; i < gamefild.getSize()+1 ; i++){
			g.drawLine(xofset, yofset+i*gridsize,xofset+fildsize, yofset+i*gridsize);
			g.drawLine(xofset+i*gridsize, yofset, xofset+i*gridsize, yofset+fildsize);
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
	 * @return the showSelection
	 */
	public boolean isShowSelection() {
		return showSelection;
	}

	/**
	 * @param showSelection the showSelection to set
	 */
	public void setShowSelection(boolean showSelection) {
		this.showSelection = showSelection;
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
	 * Adds the given ActionListener to the array.
	 * @param gfal
	 */
	public void addGameFieldActionListener(GameFieldActionListener gfal){
		listeners.add(gfal);
	}
	
	/**
	 * Notifies all added listeners.
	 */
	private void fireListeners(){
		for(GameFieldActionListener gfal : listeners){
			gfal.clickFired(this);
		}
	}
}
