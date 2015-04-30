/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.util.HashMap;

import de.hsb.ismi.jbs.engine.io.parser.LocalizationParser;

/**
 * @author jan Schult
 *
 */
public class LocalizationManager {
	
	private LocalizationParser paser;
	private HashMap<String, String> aktiv;
	private final String LANGUAGE_PATH = "Data/Lang/";
	private String aktiveLanguageName;
	
	public LocalizationManager(String language) {
		aktiveLanguageName = language;
		aktiv = new HashMap<String, String>();
		paser = new LocalizationParser();
		paser.loadLanguage(LANGUAGE_PATH+aktiveLanguageName+".txt", aktiv);
	}
	
	public String getWord(String key){
		
		if(aktiv.containsKey(key)){
			return aktiv.get(key);
		}
		return key;
	}
	
	public void changeAktivLanguage(String language){
		if(language != aktiveLanguageName){
			paser.loadLanguage(LANGUAGE_PATH+language+".txt", aktiv);
		}
		
		
	}
	
}
