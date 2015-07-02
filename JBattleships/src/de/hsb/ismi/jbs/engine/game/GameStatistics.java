/**
 * 
 */
package de.hsb.ismi.jbs.engine.game;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Kevin Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class GameStatistics {
	
	@XmlElement(name = "FiredShots")
	private int firedShots               = 0;
	@XmlElement(name = "MissedShots")
	private int missedShots              = 0;
	@XmlElement(name = "ShotsHit")
	private int shotsHit                 = 0;
	@XmlElement(name = "ShipsLost")
	private int shipsLost                = 0;
	@XmlElement(name = "ShipsDestroyed")
	private int shipsDestroyed           = 0;
	@XmlElement(name = "NavalMinesHit")
	private int navalMinesHit            = 0;
	@XmlElement(name = "NavalMinesUsed")
	private boolean navalMinesUsed       = false;
	@XmlElement(name = "CoastalArtilleryUsed")
	private boolean coastalArtilleryUsed = false;
	@XmlElement(name = "CoastalArtilleryHit")
	private boolean coastalArtilleryHit  = false;
	@XmlTransient
	private boolean flawlessWin          = false;
	@XmlTransient
	private boolean win                	 = false;
	
	/**
	 * 
	 */
	public GameStatistics() {

	}

	/**
	 * @return the firedShots
	 */
	public final int getFiredShots() {
		return firedShots;
	}

	/**
	 * @param firedShots the firedShots to set
	 */
	public final void setFiredShots(int firedShots) {
		this.firedShots = firedShots;
	}
	
	public final void increaseFiredShots(int inc){
		this.firedShots += inc;
	}

	/**
	 * @return the missedShots
	 */
	public final int getShotsMissed() {
		return missedShots;
	}

	/**
	 * @param missedShots the missedShots to set
	 */
	public final void setMissedShots(int missedShots) {
		this.missedShots = missedShots;
	}
	
	public final void increaseMissedShots(int inc){
		this.missedShots += inc;
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
	
	public final void increaseShotsHit(int inc){
		this.shotsHit += inc;
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

	public final void increaseNavalMinesHit(int inc){
		this.navalMinesHit += inc;
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
	
	public final void increaseDestroyedShips(int inc){
		this.shipsDestroyed += inc;
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

	/**
	 * @return the shipsLost
	 */
	public final int getShipsLost() {
		return shipsLost;
	}

	/**
	 * @param shipsLost the shipsLost to set
	 */
	public final void setShipsLost(int shipsLost) {
		this.shipsLost = shipsLost;
	}
	
	public final void increaseLostShips(int inc){
		this.shipsLost += inc;
	}

	/**
	 * @return the win
	 */
	public final boolean isWin() {
		return win;
	}

	/**
	 * @param win the win to set
	 */
	public final void setWin(boolean win) {
		this.win = win;
	}

}
