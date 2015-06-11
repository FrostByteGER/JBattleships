/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Jan Schult
 *
 */
public class JBSCorvette extends JBSShip {

	/**
	 * 
	 */
	public JBSCorvette() {
		super(1, 0, 3, JBSDamageType.DAMAGE_SMALL);
		setName(JBattleships.game.getDataManager().getLocalizationManager().getLocalization("Corvette"));
		addShipPart(new JBSActor("JBSCorvette"));	
		addShipPart(new JBSActor("JBSCorvette"));
		addShipPart(new JBSActor("JBSCorvette"));
		
		for(JBSActor a : getShipActors()){
			a.getComponents().setStatikimage(JBattleships.game.getDataManager().getResourceManager().getTexture("boattest.png"));
		}
		
	}

}
