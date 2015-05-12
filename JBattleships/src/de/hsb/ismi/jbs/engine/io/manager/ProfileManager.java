package de.hsb.ismi.jbs.engine.io.manager;


import java.util.HashMap;

import de.hsb.ismi.jbs.engine.io.parser.OptionsParser;
import de.hsb.ismi.jbs.engine.io.parser.ProfileParser;
import de.hsb.ismi.jbs.engine.core.JBSProfile;

public class ProfileManager {
	
	private ProfileParser parser;
	private HashMap<String, String[]> profileData;
	private HashMap<String, Float> temp;
	private HashMap<String, String> achievements;
	private final String Profile_PATH = "Data/Profiles/";
	private String profileName;
	
	public ProfileManager(){
		parser = new ProfileParser();
		profileData = new HashMap<>();
		
		//parser.loadProfile(Profile_PATH+"testProfile"+".txt", temp);
	}
	

	
	public JBSProfile loadProfile(String name){
		return null;
	}
	
	
	public boolean saveProfile(String ID){
		return false;
	}
	
	
	public boolean saveAllProfiles(){
		return false;
	}
	
	
	public JBSProfile getProfileById(int id){
		//same as loadProfile???
		//IDs are better as Integer, also easier to use for Arrays
		// also why no vector?
		return null;
	}
	
	
	public JBSProfile[] getProfilesByName(String[] name){
		// -> you'd need an array that contains all (2,3,4,5,...?) players
		return null;
	}
	
	public JBSProfile[] getProfilesById(int[] id){
		return null;
	}
}
