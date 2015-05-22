/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class ScreenDeviceManager {

	private GraphicsEnvironment ge;
	private GraphicsDevice currentAdapter;
	private final int supportedBitDepth = 32;
	private final Resolution minResolution = new Resolution(800, 600);
	@Deprecated
	private final Resolution maxResolution = new Resolution(1920, 1080);
	
	/**
	 * 
	 */
	public ScreenDeviceManager() {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		currentAdapter = ge.getDefaultScreenDevice();
	}
	
	/**
	 * Returns the active GraphicsDevice(Monitor)
	 * @return The active GraphicsDevice
	 */
	public GraphicsDevice getActiveDisplayAdapter(){
		return currentAdapter;
	}
	
	/**
	 * Returns all supported DisplayModes regardless of their refreshratio
	 * @return The DisplayModes
	 */
	public DisplayMode[] getSupportedDisplayModes(){
		return currentAdapter.getDisplayModes();
	}
	
	/**
	 * Returns all supported DisplayModes that match the given refreshrates
	 * @param supportedRefreshRates The supported refreshrates
	 * @return The DisplayModes
	 */
	public DisplayMode[] getSupportedDisplayModes(int[] supportedRefreshRates){
		ArrayList<DisplayMode> supportedModes = new ArrayList<>(0);
		for(DisplayMode m : currentAdapter.getDisplayModes()){
			for(int ref : supportedRefreshRates){
				if(ref == m.getRefreshRate() && supportedBitDepth == m.getBitDepth() && (m.getWidth() >= minResolution.getWidth() && m.getHeight() >= minResolution.getHeight())){
					supportedModes.add(m);
				}
			}
		}
		DisplayMode[] modes = supportedModes.toArray(new DisplayMode[supportedModes.size()]);
		modes = new LinkedHashSet<>(Arrays.asList(modes)).toArray(new DisplayMode[supportedModes.size()]);
		return modes;
	}
	
	/**
	 * Returns the current DisplayMode(Resolution)
	 * @return The current DisplayMode
	 */
	public DisplayMode getCurrentDisplayMode(){
		return currentAdapter.getDisplayMode();
	}
	
	/**
	 * Returns a readable String of the DisplayModes width and height.
	 * @param m DisplayMode you want to generate the String from
	 * @return The readable String
	 */
	public static String DisplayModeToString(DisplayMode m, boolean withRefresh){
		if(withRefresh){
			return m.getWidth() + "x" + m.getHeight() + "@" + m.getRefreshRate();
		}else{
			return m.getWidth() + "x" + m.getHeight();
		}
		
	}
	
	/**
	 * 
	 * @param m
	 * @param withRefresh
	 * @return
	 */
	public static String[] DisplayModesToString(DisplayMode[] m, boolean withRefresh){
		String[] stringModes = new String[m.length];
		for(int i = 0; i < m.length; i++){
			DisplayMode mode = m[i];
			if(withRefresh){
				stringModes[i] = ScreenDeviceManager.DisplayModeToString(mode, true);
			}else{
				stringModes[i] = ScreenDeviceManager.DisplayModeToString(mode, false);
			}
		}
		return stringModes;
	}
	
	/**
	 * Tries to find the given Resolution inside the DisplayMode array
	 * @param r
	 * @param m
	 * @return The index. -1 if none found.
	 */
	public static int findResolutionInDisplayModes(Resolution r, DisplayMode[] m){
		int index = -1;
		for(int i = 0;i < m.length;i++){
			if(r.toString().equals(DisplayModeToString(m[i], false))){
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Tries to find the given Resolution inside the String array
	 * @param r
	 * @param m
	 * @return The index. -1 if none found.
	 */
	public static int findResolutionInDisplayModes(Resolution r, String[] m){
		int index = -1;
		for(int i = 0;i < m.length;i++){
			if(r.toString().equals(m[i])){
				index = i;
			}
		}
		return index;
	}
	
	/*
	public static void main(String[] args) {
		ScreenDeviceManager rm = new ScreenDeviceManager();
		DisplayMode[] modes = rm.getSupportedDisplayModes(new int[]{60});
		for(DisplayMode m : modes){
			System.out.println(DisplayModeToString(m, true));
		}
	}
	*/

}
