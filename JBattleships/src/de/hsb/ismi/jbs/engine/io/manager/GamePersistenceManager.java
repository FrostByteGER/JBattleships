/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.hsb.ismi.jbs.engine.game.Game;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;
import de.hsb.ismi.jbs.start.JBattleships;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public class GamePersistenceManager {
	
	public static final String GAME_SAVE_PATH = "Data/Saves/";
	public static final String GAME_SAVE_EXTENSION = ".xml";
	private HashMap<String, Game> saveGames = new HashMap<>();

	/**
	 * 
	 */
	public GamePersistenceManager() {
		
	}
	
	
	/**
	 * 
	 * @param path
	 * @return
	 */
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

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Game> loadGames(){
		try {
			List<File> files = Files.walk(Paths.get(GAME_SAVE_PATH)).filter((p) -> !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(GAME_SAVE_EXTENSION)).map(Path::toFile).collect(Collectors.toList());
			for(File f : files){
				Game g = null;
				try {
					DebugLog.logInfo("Trying to load Game: " + f.getName() + "...");
					JAXBContext jaxb = JAXBContext.newInstance(Game.class);
					Unmarshaller um = jaxb.createUnmarshaller();
					Object o = um.unmarshal(new File(GAME_SAVE_PATH + f.getName()));
					if(o instanceof Game){
						g = (Game) o;
						// Skip loading this game to prevent errors if a game with the same name already exists.
						String name = f.getName().substring(0, f.getName().lastIndexOf("."));
						if(saveGames.get(name) != null){
							DebugLog.logWarning("Game " + f.getName() + " already exists!");
							continue;
						}
						saveGames.put(name, g);
						DebugLog.logInfo("Successfully loaded Game: " + f.getName());
					}else{
						DebugLog.logWarning("Loading Game: " + f.getName() + " failed! File is not a game.");
					}
				} catch (JAXBException e) {
					DebugLog.logWarning("Loading Game: " + f.getName() + " failed! File is not a XML.");
					DebugLog.logError(e);
				}
			}
			return saveGames;
		} catch (IOException e) {
			DebugLog.logError(e);
			return null;
		}
	}



	/**
	 * @return the saveGames
	 */
	public final HashMap<String, Game> getSaveGames() {
		return saveGames;
	}



	/**
	 * @param saveGames the saveGames to set
	 */
	public final void setSaveGames(HashMap<String, Game> saveGames) {
		this.saveGames = saveGames;
	}
	
	public final Game getSaveGame(String name){
		return saveGames.get(name);
	}
	
	public final boolean deleteSaveGame(String name){
		DebugLog.logInfo("Trying to delete Save: " + name + "...");
		Game g = saveGames.get(name);
		if(g != null){
			saveGames.remove(g);
			boolean b = new File(GAME_SAVE_PATH + name + GAME_SAVE_EXTENSION).delete();
			if(b){
				DebugLog.logInfo("Successfully deleted save: " + name);
			}else{
				DebugLog.logWarning("Error deleting save: " + name);
			}
			return b;
		}else{
			return false;
		}
	}

}
