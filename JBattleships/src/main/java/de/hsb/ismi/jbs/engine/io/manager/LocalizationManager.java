/**
 * 
 */
package de.hsb.ismi.jbs.engine.io.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import de.hsb.ismi.jbs.core.JBSCoreGame;
import de.hsb.ismi.jbs.engine.io.JBSParserException;
import de.hsb.ismi.jbs.engine.io.parser.LocalizationParser;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;

/**
 * @author jan Schult
 * @version 1.00
 */
public class LocalizationManager {
	
	private LocalizationParser parser;
	private HashMap<String, String> activeLanguageData;
	private String[] languageTable;
	
	/** Contains standard localizations */
	@Deprecated
	private HashMap<String, String> defaultLanguageData;
	private final String LANGUAGE_PATH = "Lang/";
	private final String LANGUAGE_TABLE_PATH = "Languages.cfg";
	private String activeLanguage;
	
	/**
	 * 
	 * @param language
	 */
	public LocalizationManager() {
		activeLanguage = "English";
		activeLanguageData = new HashMap<String, String>();
		parser = new LocalizationParser();
		languageTable = new String[]{"English"};
	}
	
	/**
	 * DEPRECATED, DO NOT USE!
	 * Loads the language with the current set LanguageName
	 * @return Indicates if an error occured during the loading. True if none was encountered.
	 */
	@Deprecated 
	public boolean loadLanguage(){
		try {
			activeLanguageData = parser.loadLanguage(JBSCoreGame.DATA_PATH + LANGUAGE_PATH+activeLanguage+".txt");
		} catch (IOException | JBSParserException e) {
			DebugLog.logError(e);
			return false;
		}
		return true;
	}
	
	public boolean loadLanguageTable(){
		File f = new File(JBSCoreGame.DATA_PATH + LANGUAGE_PATH + LANGUAGE_TABLE_PATH);
		if(f.exists()){
			try {
					languageTable = parser.parseLanguageTable(JBSCoreGame.DATA_PATH + LANGUAGE_PATH + LANGUAGE_TABLE_PATH);
					return true;
				} catch (FileNotFoundException e) {
					DebugLog.logError(e);
					return false;
				} catch (IOException e) {
					DebugLog.logError(e);
					return false;
				}
			}else{
				return false;
			}
	}
	
	/**
	 * Loads the language from the given languageName
	 * @param language The language you want to load
	 * @return Indicates if an error occured during the loading. True if none was encountered.
	 */
	public boolean loadLanguage(String language){
		try {
			activeLanguageData = parser.loadLanguage(JBSCoreGame.DATA_PATH + LANGUAGE_PATH + language + ".cfg");
			activeLanguage = language;
		} catch (IOException | JBSParserException e) {
			DebugLog.logError(e);
			return false;
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
		return key;
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
	@Deprecated
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

	/**
	 * @return the languageTable
	 */
	public final String[] getLanguageTable() {
		return languageTable;
	}
	
}
