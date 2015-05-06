/**
 * 
 */
package de.hsb.ismi.jbs.start;

import de.hsb.ismi.jbs.engine.core.Direction;
import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.JBSDestroyer;
import de.hsb.ismi.jbs.engine.core.JBSGameField;
import de.hsb.ismi.jbs.engine.core.JBSPlayer;

/**
 * This Class may be used to test anything you like.
 * @author Kevin Kuegler
 * @version 1.00
 */
public class TestingClass {

	/**
	 * 
	 */
	public TestingClass() {
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Game g = new Game();
		
		JBSGameField f = new JBSGameField(new JBSPlayer(),16);
		
		JBSDestroyer ship = new JBSDestroyer(g.getDataManager());
		
		ship.setPositon(0, 0, Direction.EAST);
		
		System.out.println(f.shipCanBePlaced(ship));
		
		System.out.println(f.setShip(ship));
		
		ship.checkHealth();
		System.out.println(ship.getHealth());
		
		for(int i = 0  ; i < 10 ; i++){
			f.shootField(4, i);
		}
		
		ship.checkHealth();
		System.out.println(ship.getHealth());
		
		f.printField();
		
		
		
		
		/*
		Game g = new Game();
		
		JBSGameField field = new JBSGameField(new JBSPlayer(),10);
		
		JBSDestroyer d = new JBSDestroyer(g.getDataManager());
		
		d.setPositon(0, 0, Direction.NORTH);
		
		System.out.println(field.setShip(d));
		*/
		//LocalizationManager ma = new LocalizationManager("default.txt");			
		
		//OptionsManager om = new OptionsManager();
		//om.loadOptions();

		/*
		OptionsParser op = new OptionsParser();
		ArrayList<String> data;
		try {
			data = op.parseOptions("Data/Config/settings.cfg");
			for(String s : data){
				System.out.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
