package de.hsb.ismi.jbs.gui.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.actors.JBSActor;
import de.hsb.ismi.jbs.engine.actors.JBSActorComponent;
import de.hsb.ismi.jbs.engine.actors.ships.JBSShipActor;
import de.hsb.ismi.jbs.engine.game.Direction;
import de.hsb.ismi.jbs.engine.game.JBSGameField;
import de.hsb.ismi.jbs.engine.rendering.AnimationThread;

/**
 * @author Jan Schult
 *
 * 
 *
 */
public class GameFieldPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1845450912510850006L;
	
	// aktuelles Spielfeld
	private JBSGameField gamefield;
	
	// größe von einem Teil des Feldes
	private int gridsize;
	// gesamt größe des Feldes in pix
	private int fieldsize;
	
	// ofset damit das Feld in der Mitte ist
	private int xoffset;
	private int yoffset;
	
	// Farbe des Grids (ist gerade Unsichtbar)
	private Color gridColor;
	
	// Vareablen für die Selectionsfunction
	private Color selectColor;
	private Color hoverColor;
	private boolean isSelected;
	private boolean showSelection;
	private int hoverx;
	private int hovery;
	private int selectx;
	private int selecty;
	private Direction direction; 
	
	// macht alle Shiffe sichtbar / unsichtbar
	private boolean showships = false;
	
	// kümmert sich um die Animation
	private AnimationThread animationthread;
	
	private ArrayList<GameFieldActionListener> listeners;

	private JBSShipActor tempship;
	
	// wir für den Wasserhintergrund verwendet
	private JBSActor water = new JBSActor();
	
	public GameFieldPanel(JBSGameField fild ,int fieldsize ,int size) {
		
		water.setComponents(new JBSActorComponent(new String[]{"watertest64.png"}));
		
		this.gamefield = fild;
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
		setOpaque(false);
		
		this.fieldsize = fieldsize;
		xoffset = 0;
		yoffset = 0;
					
		animationthread = new AnimationThread(fieldsize/gamefield.getSize());
		animationthread.addActorCommponent(water.getComponents());
		
		animationthread.start();
		
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
					if(hoverx != ((e.getX()-xoffset)-((e.getX()-xoffset)%gridsize))/gridsize ||
							hovery != ((e.getY()-yoffset)-((e.getY()-yoffset)%gridsize))/gridsize){
						GameFieldPanel.this.repaint();
					}
					
					if(((e.getX()-xoffset)-((e.getX()-xoffset)%gridsize))/gridsize < fild.getSize() && 
							((e.getX()-xoffset)-((e.getX()-xoffset)%gridsize))/gridsize >= 0){
						hoverx = ((e.getX()-xoffset)-((e.getX()-xoffset)%gridsize))/gridsize;
					}
					if(((e.getY()-yoffset)-((e.getY()-yoffset)%gridsize))/gridsize < fild.getSize() &&
							((e.getY()-yoffset)-((e.getY()-yoffset)%gridsize))/gridsize >= 0){
						hovery = ((e.getY()-yoffset)-((e.getY()-yoffset)%gridsize))/gridsize;
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
		return gamefield;
	}

	/**
	 * @param fild the fild to set
	 */
	public void setGamefild(JBSGameField fild) {
		this.repaint();
		this.gamefield = fild;
	}

	private void drawGrid(Graphics g){
		
		gridsize = fieldsize/gamefield.getSize();
		xoffset = (getSize().width-fieldsize)/2;
		yoffset = (getSize().height-fieldsize)/2;
		
		for(int i = 0 ; i < gamefield.getSize() ; i++){
			for(int j = 0 ; j < gamefield.getSize() ; j++ ){
				
				g.drawImage(water.getComponents().getImage(), gridsize*i+xoffset, gridsize*j+yoffset, null);
				
				if(gamefield.isFieldWater(i, j)){
					//g.setColor(Color.BLUE);				
					//g.fillRect(gridsize*i+xoffset+1, gridsize*j+yoffset+1, gridsize-1, gridsize-1);
					
					//g.drawImage(gamefield.getField(i, j).getComponents().getImage(), gridsize*i+xoffset, gridsize*j+yoffset, null);
					
				}else if(gamefield.isFieldWaterHit(i, j)){
					//g.drawImage(gamefield.getField(i, j).getComponents().getImage(), gridsize*i+xoffset, gridsize*j+yoffset, null);
					//g.setColor(Color.BLUE);
					//g.fillRect(gridsize*i+xoffset+1, gridsize*j+yoffset+1, gridsize-1, gridsize-1);
				}else{
					if(showships || JBSCoreGame.DEBUG_MODE){
						
						g.drawImage(gamefield.getField(i, j).getComponents().getImage(), gridsize*i+xoffset, gridsize*j+yoffset, null);
						
					}else if(gamefield.getField(i, j).isHit()){
						tempship = (JBSShipActor)gamefield.getField(i, j);
						
						if(tempship.getParent().isAlive()){
							g.setColor(Color.GRAY);
							g.fillRect(gridsize*i+xoffset, gridsize*j+yoffset, gridsize, gridsize);
						}else{
							g.drawImage(gamefield.getField(i, j).getComponents().getImage(), gridsize*i+xoffset, gridsize*j+yoffset, null);
						}
					}
				}
				if(gamefield.getField(i, j).isHit()){
					
					g.setColor(Color.RED);
					g.drawLine(gridsize*i+xoffset+1, gridsize*j+yoffset+1, gridsize*(i+1)+xoffset-1, gridsize*(j+1)+yoffset-1);
					g.drawLine(gridsize*i+xoffset+1, gridsize*(j+1)+yoffset+1, gridsize*(i+1)+xoffset-1, gridsize*j+yoffset-1);
					
				}	
			}
		}
				
		if(isSelected){
			g.setColor(hoverColor);		
			g.fillRect(hoverx*gridsize+xoffset, hovery*gridsize+yoffset, gridsize, gridsize);
			
			g.setColor(Color.WHITE);			
			if(direction == Direction.NORTH){
				g.drawString("^", hoverx*gridsize+xoffset+gridsize/2, hovery*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.EAST){
				g.drawString(">", hoverx*gridsize+xoffset+gridsize/2, hovery*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.SOUTH){
				g.drawString("v", hoverx*gridsize+xoffset+gridsize/2, hovery*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.WEST){
				g.drawString("<", hoverx*gridsize+xoffset+gridsize/2, hovery*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}
		}
		if(showSelection){
			g.setColor(selectColor);		
			g.fillRect(selectx*gridsize+xoffset, selecty*gridsize+yoffset, gridsize, gridsize);
			
			g.setColor(Color.WHITE);			
			if(direction == Direction.NORTH){
				g.drawString("^", selectx*gridsize+xoffset+gridsize/2, selecty*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.EAST){
				g.drawString(">", selectx*gridsize+xoffset+gridsize/2, selecty*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.SOUTH){
				g.drawString("v", selectx*gridsize+xoffset+gridsize/2, selecty*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}else if(direction == Direction.WEST){
				g.drawString("<", selectx*gridsize+xoffset+gridsize/2, selecty*gridsize+yoffset+gridsize/2);
				GameFieldPanel.this.repaint();
			}		
		}

		
		g.setColor(gridColor);
		
		for (int i = 0 ; i < gamefield.getSize()+1 ; i++){
			g.drawLine(xoffset, yoffset+i*gridsize,xoffset+fieldsize, yoffset+i*gridsize);
			g.drawLine(xoffset+i*gridsize, yoffset, xoffset+i*gridsize, yoffset+fieldsize);
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
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

	/**
	 * @return the showships
	 */
	public boolean isShowships() {
		return showships;
	}

	/**
	 * @param showships the showships to set
	 */
	public void setShowships(boolean showships) {
		this.showships = showships;
	}
	
	
}
