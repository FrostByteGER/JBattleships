/**
 * 
 */
package de.hsb.ismi.jbs.engine.core;

/**
 * 
 * @author Kevin Kuegler
 * @version 1.00
 */
public class GameStatistics {
	
	private int shotsFired = 0;
	private int shotsMissed = 0;
	private int shotsHit = 0;
	
	private boolean coastalArtilleryUsed = false;
	private boolean coastalArtilleryHit = false;
	
	private boolean navalMinesUsed = false;
	private int navalMinesHit = 0;
	
	private int shipsDestroyed = 0;
	private boolean flawlessWin = false;
	
	public GameStatistics() {
		//TODO: Add into game.
	}

	/**
	 * @return the shotsFired
	 */
	public final int getShotsFired() {
		return shotsFired;
	}

	/**
	 * @param shotsFired the shotsFired to set
	 */
	public final void setShotsFired(int shotsFired) {
		this.shotsFired = shotsFired;
	}

	/**
	 * @return the shotsMissed
	 */
	public final int getShotsMissed() {
		return shotsMissed;
	}

	/**
	 * @param shotsMissed the shotsMissed to set
	 */
	public final void setShotsMissed(int shotsMissed) {
		this.shotsMissed = shotsMissed;
	}

	/**
	 * @return the shotsHit
	 */
	public final int getShotsHit() {
		return shotsHit;
	}

	/**
	 * @param shotsHit the shotsHit to set
	 */
	public final void setShotsHit(int shotsHit) {
		this.shotsHit = shotsHit;
	}

	/**
	 * @return the coastalArtilleryUsed
	 */
	public final boolean isCoastalArtilleryUsed() {
		return coastalArtilleryUsed;
	}

	/**
	 * @param coastalArtilleryUsed the coastalArtilleryUsed to set
	 */
	public final void setCoastalArtilleryUsed(boolean coastalArtilleryUsed) {
		this.coastalArtilleryUsed = coastalArtilleryUsed;
	}

	/**
	 * @return the coastalArtilleryHit
	 */
	public final boolean isCoastalArtilleryHit() {
		return coastalArtilleryHit;
	}

	/**
	 * @param coastalArtilleryHit the coastalArtilleryHit to set
	 */
	public final void setCoastalArtilleryHit(boolean coastalArtilleryHit) {
		this.coastalArtilleryHit = coastalArtilleryHit;
	}

	/**
	 * @return the navalMinesUsed
	 */
	public final boolean isNavalMinesUsed() {
		return navalMinesUsed;
	}

	/**
	 * @param navalMinesUsed the navalMinesUsed to set
	 */
	public final void setNavalMinesUsed(boolean navalMinesUsed) {
		this.navalMinesUsed = navalMinesUsed;
	}

	/**
	 * @return the navalMinesHit
	 */
	public final int getNavalMinesHit() {
		return navalMinesHit;
	}

	/**
	 * @param navalMinesHit the navalMinesHit to set
	 */
	public final void setNavalMinesHit(int navalMinesHit) {
		this.navalMinesHit = navalMinesHit;
	}

	/**
	 * @return the shipsDestroyed
	 */
	public final int getShipsDestroyed() {
		return shipsDestroyed;
	}

	/**
	 * @param shipsDestroyed the shipsDestroyed to set
	 */
	public final void setShipsDestroyed(int shipsDestroyed) {
		this.shipsDestroyed = shipsDestroyed;
	}

	/**
	 * @return the flawlessWin
	 */
	public final boolean isFlawlessWin() {
		return flawlessWin;
	}

	/**
	 * @param flawlessWin the flawlessWin to set
	 */
	public final void setFlawlessWin(boolean flawlessWin) {
		this.flawlessWin = flawlessWin;
	}

}
