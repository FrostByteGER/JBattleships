package de.hsb.ismi.jbs.gui;

import java.util.Random;

public class animationThread extends Thread {

	private GameFieldPanel field;
	private int sleeptime;
	private boolean runit;
	private Random r;
	
	public animationThread(GameFieldPanel field,int size) {
		
		this.field = field;
		sleeptime = 200;
		runit = true;
		r = new Random();
		for(int i = 0 ; i < field.getGamefild().getSize() ; i++){
			for(int j = 0 ; j < field.getGamefild().getSize() ; j++){
				field.getGamefild().getField(i, j).getComponents().resize(size);
				field.getGamefild().getField(i, j).getComponents().setImagecount(r.nextInt(10));
			}
		}
		
	}
	
	public void setRunIt(boolean runit){
		this.runit = runit;
	}
	
	@Override
	public void run() {
		super.run();
		while(runit){
			try {
				sleep(sleeptime);
								
				for(int i = 0 ; i < field.getGamefild().getSize() ; i++){
					for(int j = 0 ; j < field.getGamefild().getSize() ; j++){
						
						if(field.getGamefild().getField(i, j).getComponents().getImagecount() == 0 && r.nextInt(20)==10){
							field.getGamefild().getField(i, j).getComponents().startAnimation(r.nextInt(10));
						}else{
							field.getGamefild().getField(i, j).getComponents().nextImage();
						}					
					}
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			
			
		}
	}	
}
