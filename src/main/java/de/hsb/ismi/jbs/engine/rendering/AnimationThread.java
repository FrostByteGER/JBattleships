package de.hsb.ismi.jbs.engine.rendering;

import java.util.ArrayList;

import de.hsb.ismi.jbs.engine.actors.JBSActorComponent;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.start.JBattleships;

public class AnimationThread extends Thread {

	private ArrayList<JBSActorComponent> extra;
	private int sleeptime;
	private boolean runit;
	
	public AnimationThread(int size) {
		
		sleeptime = 150;
		runit = true;
		extra = new ArrayList<JBSActorComponent>();

		JBattleships.game.getDataManager().getResourceManager().resizeAllAnimations(size, size);
	}
	
	public void setRunIt(boolean runit){
		this.runit = runit;
	}
	
	@Override
	public void run() {
		while(runit){
			try {
				sleep(sleeptime);
				for(JBSActorComponent actorcom : extra){
					actorcom.nextImage();
				}
			} catch (InterruptedException e) {
				DebugLog.logError(e);
				break;
			}
		}
	}
	
	public void addActorCommponent(JBSActorComponent actorcom){
		this.extra.add(actorcom);
	}
	
}
