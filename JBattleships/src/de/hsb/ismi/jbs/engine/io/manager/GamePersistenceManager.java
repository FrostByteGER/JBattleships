/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.hsb.ismi.jbs.engine.core.Game;
import de.hsb.ismi.jbs.engine.core.manager.GameManager;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class GamePersistenceManager {
	
	public static final String GAME_SAVE_PATH = "Data/Saves/";

	/**
	 * 
	 */
	public GamePersistenceManager() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public boolean saveGame(String path){
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Game.class);
			Marshaller m = jaxb.createMarshaller();
			m.marshal(JBattleships.game.getGameManager().getGame(), new File(GAME_SAVE_PATH + path));
		} catch (JAXBException jaxbe) {
			jaxbe.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean loadGame(String path){
		try {
			JAXBContext jaxb = JAXBContext.newInstance(Game.class);
			Unmarshaller um = jaxb.createUnmarshaller();
			Object o = um.unmarshal(new File(path));
			if(o instanceof Game){
				Game game = (Game) o;
				GameManager gm = JBattleships.game.generateGame();
				gm.setGame(game);
			}else{
				return false;
			}
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
