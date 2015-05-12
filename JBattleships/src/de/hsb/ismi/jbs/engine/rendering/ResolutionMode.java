/**
 * 
 */
package de.hsb.ismi.jbs.engine.rendering;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
public enum ResolutionMode {
	MODE_FULLSCREEN("Fullscreen"),
	MODE_BORDERLESS("Borderless"),
	MODE_WINDOWED("Windowed");
	
	private final String text;
	
	private ResolutionMode(String text){
		this.text = text;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}
}
