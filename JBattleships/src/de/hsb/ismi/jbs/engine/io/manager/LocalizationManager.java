/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.IOException;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCore;
import de.hsb.ismi.jbs.engine.io.JBSParserException;
import de.hsb.ismi.jbs.engine.io.parser.LocalizationParser;

/**
 * @author jan Schult
 * @version 1.00
 */
public class LocalizationManager {
	
	private LocalizationParser parser;
	private HashMap<String, String> activeLanguageData;
	private HashMap<String, String> defaultLanguageData;
	private final String LANGUAGE_PATH = "Lang/";
	private String activeLanguage;
	
	/**
	 * 
	 * @param language
	 */
	public LocalizationManager() {
		activeLanguage = null; //TODO: Maybe change to english or default, since it makes sense to have a default language loaded
		activeLanguageData = new HashMap<String, String>();
		parser = new LocalizationParser();
		initDefaultLanguageData(); //TODO: Remove
	}
	
	/**
	 * DEPRECATED, DO NOT USE!
	 * Loads the language with the current set LanguageName
	 * @return Indicates if an error occured during the loading. True if none was encountered.
	 */
	@Deprecated 
	public boolean loadLanguage(){
		try {
			activeLanguageData = parser.loadLanguage(JBSCore.DATA_PATH + LANGUAGE_PATH+activeLanguage+".txt");
		} catch (IOException | JBSParserException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Loads the language from the given languageName
	 * @param language The language you want to load
	 * @return Indicates if an error occured during the loading. True if none was encountered.
	 */
	public boolean loadLanguage(String language){
		if(!activeLanguage.toLowerCase().equals(language.toLowerCase())){
			activeLanguage = language;
			try {
				activeLanguageData = parser.loadLanguage(JBSCore.DATA_PATH + LANGUAGE_PATH+activeLanguage+".txt");
			} catch (IOException | JBSParserException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getLocalization(String key){
		if(activeLanguageData.containsKey(key)){
			return activeLanguageData.get(key);
		}
		return key; //TODO: Change to null?
	}
	
	/**
	 * TODO: Maybe move to JBSCore
	 */
	@Deprecated
	private void initDefaultLanguageData(){
		defaultLanguageData = new HashMap<>();
	}

	/**
	 * @return the activeLanguageData
	 */
	public final HashMap<String, String> getActiveLanguageData() {
		return activeLanguageData;
	}

	/**
	 * @return the defaultLanguageData
	 */
	public final HashMap<String, String> getDefaultLanguageData() {
		return defaultLanguageData;
	}

	/**
	 * @return the lANGUAGE_PATH
	 */
	public final String getLanguagePath() {
		return LANGUAGE_PATH;
	}

	/**
	 * @return the activeLanguage
	 */
	public final String getActiveLanguage() {
		return activeLanguage;
	}
	
}
