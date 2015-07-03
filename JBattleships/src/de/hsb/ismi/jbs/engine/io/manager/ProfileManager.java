package de.hsb.ismi.jbs.engine.io.manager;


import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import de.hsb.ismi.jbs.engine.players.JBSProfile;
import de.hsb.ismi.jbs.engine.utility.debug.DebugLog;

public class ProfileManager {
	
	private ArrayList<JBSProfile> profiles = new ArrayList<>();
	
	public static final String PROFILE_PATH      = "Data/Profiles/";
	public static final String PROFILE_EXTENSION = ".xml";
	
	private int activeProfile = 0;
	
	/**
	 * 
	 */
	public ProfileManager(){

	}
	

	/**
	 * Loads the profile with the specified name. Returns false if the profile was not found or an error occured.
	 * @param name 
	 * @return
	 */
	public boolean loadProfile(String name, boolean asActive){
		try {
			DebugLog.logInfo(name);
			JAXBContext jaxb = JAXBContext.newInstance(JBSProfile.class);
			Unmarshaller um = jaxb.createUnmarshaller();
			Object o = um.unmarshal(new File(PROFILE_PATH + name + PROFILE_EXTENSION));
			if(o instanceof JBSProfile){
				JBSProfile p = (JBSProfile) o;
				profiles.add(p);
				if(asActive){
					activeProfile = profiles.size() - 1;
				}
			}else{
				return false;
			}
		} catch (JAXBException e) {
			DebugLog.logError(e);
			return false;
		}
		return true;
	}
	
	/**
	 * Saves the profile with the given name. Returns false if the profile was not found or an error occured.
	 * @param name
	 * @return
	 */
	public boolean saveProfile(String name){
		JBSProfile p = getProfileByName(name);
		if(p == null){
			return false;
		}
		try {
			JAXBContext jaxb = JAXBContext.newInstance(JBSProfile.class);
			Marshaller m = jaxb.createMarshaller();
			m.marshal(p, new File(PROFILE_PATH + p.getName() + PROFILE_EXTENSION));
			return true;
		} catch (JAXBException e) {
			DebugLog.logError(e);
			return false;
		}
	}
	
	/**
	 * Saves the active profile. Returns false if the profile was not found or an error occured.
	 * @return
	 */
	public boolean saveActiveProfile(){
		return saveProfile(profiles.get(activeProfile).getName());
	}
	
	public JBSProfile loadDefaultProfile(){
		JBSProfile p = new JBSProfile("default");
		profiles.add(p);
		return p;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public JBSProfile getProfileByName(String name){
		for(JBSProfile p : profiles){
			if(p.getName().equals(name)){
				return p;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public JBSProfile getActiveProfile(){
		return profiles.get(activeProfile);
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setActiveProfile(int index){
		if(index < 0 || index >= profiles.size()){
			throw new IllegalArgumentException("PLACEHOLDER");
		}else{
			activeProfile = index;
		}
	}
}
