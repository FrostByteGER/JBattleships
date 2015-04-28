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
	private final String LANGUAGE_PATH = "Lang/";
	private String aktiveLanguageName;
	
	public LocalizationManager(String language) {
		aktiveLanguageName = language;
		
		paser.loadLanguage(aktiveLanguageName, aktiv);
	}
	
	public String getWord(String key){
		return aktiv.get(key);
	}
	
	public void changeAktivLanguage(String language){
		if(language != aktiveLanguageName){
			paser.loadLanguage(language, aktiv);
		}
		
		
	}
	
}
