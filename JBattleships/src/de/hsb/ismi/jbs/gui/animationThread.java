package de.hsb.ismi.jbs.gui;

public class animationThread extends Thread {

	private GameFieldPanel field;
	private int sleeptime;
	private boolean runit;
	
	public animationThread(GameFieldPanel field) {
		
		this.field = field;
		sleeptime = 200;
		runit = true;
		
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
						//TODO
					}
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			
			
		}
	}	
}
