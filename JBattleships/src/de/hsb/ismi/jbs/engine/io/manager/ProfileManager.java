package de.hsb.ismi.jbs.engine.io.manager;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public JBSProfile loadProfile(String name, boolean asActive){
		JBSProfile p = null;
		if(getProfileByName(name) != null){
			DebugLog.logInfo("Profile " + name + " already exists!");
			return p;
		}
		try {
			DebugLog.logInfo("Trying to load Profile: " + name + "...");
			JAXBContext jaxb = JAXBContext.newInstance(JBSProfile.class);
			Unmarshaller um = jaxb.createUnmarshaller();
			Object o = um.unmarshal(new File(PROFILE_PATH + name + PROFILE_EXTENSION));
			if(o instanceof JBSProfile){
				p = (JBSProfile) o;
				profiles.add(p);
				DebugLog.logInfo("Successfully loaded Profile: " + name);
				if(asActive){
					activeProfile = profiles.size() - 1;
				}
			}else{
				return p;
			}
		} catch (JAXBException e) {
			DebugLog.logError(e);
			return p;
		}
		return p;
	}
	
	/**
	 * 
	 * @return
	 */
	public JBSProfile[] loadProfiles(){
		try {
			List<File> files = Files.walk(Paths.get(PROFILE_PATH)).filter((p) -> !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(PROFILE_EXTENSION)).map(Path::toFile).collect(Collectors.toList());
			ArrayList<JBSProfile> profiles = new ArrayList<>(files.size());
			for(File f : files){
				JBSProfile p = null;
				try {
					DebugLog.logInfo("Trying to load Profile: " + f.getName() + "...");
					JAXBContext jaxb = JAXBContext.newInstance(JBSProfile.class);
					Unmarshaller um = jaxb.createUnmarshaller();
					Object o = um.unmarshal(new File(PROFILE_PATH + f.getName()));
					if(o instanceof JBSProfile){
						p = (JBSProfile) o;
						// Skip loading this profile to prevent errors if a profile with the same name already exists.
						if(getProfileByName(p.getName()) != null){
							DebugLog.logWarning("Profile " + p.getName() + " already exists!");
							continue;
						}
						profiles.add(p);
						DebugLog.logInfo("Successfully loaded Profile: " + p.getName());
					}else{
						DebugLog.logWarning("Loading Profile: " + f.getName() + " failed! File is not a profile.");
					}
				} catch (JAXBException e) {
					DebugLog.logWarning("Loading Profile: " + f.getName() + " failed! File is not a XML.");
					DebugLog.logError(e);
				}
			}
			this.profiles.addAll(profiles);
			return profiles.toArray(new JBSProfile[profiles.size()]);
		} catch (IOException e) {
			DebugLog.logError(e);
			//Returns default profile
			return new JBSProfile[] {loadDefaultProfile()};
		}
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
	
	/**
	 * Loads the default profile. If a profile with the name "default" already exists, it takes it as the default profile.
	 * @return The created or loaded default-profile.
	 */
	public JBSProfile loadDefaultProfile(){
		int index = getProfileIndex(getProfileByName("default"));
		if(index != -1){
			activeProfile = index;
			return getProfileByIndex(index);
		}else{
			JBSProfile p = new JBSProfile("default");
			profiles.add(p);
			return p;
		}
		
	}
	
	/**
	 * Returns the index of the given profile. -1 if none was found.
	 * @param p The profile you want the index of.
	 * @return The index. -1 of none was found.
	 */
	public int getProfileIndex(JBSProfile p){
		return profiles.indexOf(p);
	}
	
	/**
	 * Returns the profile from the given index. Can be <b>null!</b>
	 * @param index The index of the profile.
	 * @return The profile at the specified index.
	 */
	public JBSProfile getProfileByIndex(int index){
		return profiles.get(index);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteProfile(String name){
		DebugLog.logInfo("Trying to delete Profile: " + name + "...");
		JBSProfile p = getProfileByName(name);
		if(p != null){
			profiles.remove(p);
			boolean b = new File(PROFILE_PATH + name + PROFILE_EXTENSION).delete();
			if(b){
				DebugLog.logInfo("Successfully deleted Profile: " + name);
			}else{
				DebugLog.logWarning("Error deleting Profile: " + name);
			}
			return b;
		}else{
			return false;
		}
	}
	
	/**
	 * Checks the current profiles for the given name. Returns the profile if one with the specified name was found, otherwise null.
	 * <b> Ignores case!</b>
	 * @param name
	 * @return
	 */
	public JBSProfile getProfileByName(String name){
		for(JBSProfile p : profiles){
			if(p.getName().equalsIgnoreCase(name)){
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
	 * @return
	 */
	public int getActiveProfileIndex(){
		return activeProfile;
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


	/**
	 * @return the profiles
	 */
	public final ArrayList<JBSProfile> getProfiles() {
		return profiles;
	}
	
	/**
	 * Adds the given profile to the profile-array.
	 * @param p The profile to add.
	 * @return True if profile was added. False if the profile already exists.
	 */
	public boolean addProfile(JBSProfile p){
		if(getProfileIndex(p) == -1 && getProfileByName(p.getName()) == null){
			profiles.add(p);
			return true;
		}else{
			return false;
		}
	}
}
