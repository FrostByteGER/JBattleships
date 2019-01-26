/**
 * 
 */
package de.hsb.ismi.jbs.engine.players;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import de.hsb.ismi.jbs.engine.game.GameStatistics;

/**
 * @author Kevin-Laptop Kuegler
 * @version 1.00
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfileStatistics {
	
	// Games
	@XmlElement(name = "GamesWon")
	private int gamesWon = 0;
	@XmlElement(name = "GamesLost")
	private int gamesLost = 0;
	@XmlElement(name = "FlawlessWins")
	private int flawlessWins = 0;
	// Shots
	@XmlElement(name = "FiredShots")
	private int firedShots = 0;
	@XmlElement(name = "MissedShots")
	private int missedShots = 0;
	@XmlElement(name = "ShotsHit")
	private int shotsHit = 0;
	// Ships
	@XmlElement(name = "ShipsLost")
	private int shipsLost = 0;
	@XmlElement(name = "ShipsDestroyed")
	private int shipsDestroyed = 0;
	// Extra
	@XmlElement(name = "NavalMinesHits")
	private int navalMinesHits = 0;
	@XmlElement(name = "CoastalArtilleryHits")
	private int coastalArtilleryHits = 0;
	/**
	 * 
	 */
	public ProfileStatistics() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param gamesWon
	 * @param gamesLost
	 * @param flawlessWins
	 * @param firedShots
	 * @param missedShots
	 * @param shotsHit
	 * @param shipsLost
	 * @param shipsDestroyed
	 * @param navalMinesHits
	 * @param coastalArtilleryHits
	 */
	public ProfileStatistics(int gamesWon, int gamesLost, int flawlessWins, int firedShots, int missedShots,
			int shotsHit, int shipsLost, int shipsDestroyed, int navalMinesHits, int coastalArtilleryHits) {
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
		this.flawlessWins = flawlessWins;
		this.firedShots = firedShots;
		this.missedShots = missedShots;
		this.shotsHit = shotsHit;
		this.shipsLost = shipsLost;
		this.shipsDestroyed = shipsDestroyed;
		this.navalMinesHits = navalMinesHits;
		this.coastalArtilleryHits = coastalArtilleryHits;
	}


	

	/**
	 * @return the gamesWon
	 */
	public final int getGamesWon() {
		return gamesWon;
	}

	/**
	 * @param gamesWon the gamesWon to set
	 */
	public final void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}
	
	public final void incrementGamesWon(int inc){
		this.gamesWon += inc;
	}

	/**
	 * @return the gamesLost
	 */
	public final int getGamesLost() {
		return gamesLost;
	}

	/**
	 * @param gamesLost the gamesLost to set
	 */
	public final void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
	
	public final void incrementGamesLost(int inc){
		this.gamesLost += inc;
	}

	/**
	 * @return the flawlessWins
	 */
	public final int getFlawlessWins() {
		return flawlessWins;
	}

	/**
	 * @param flawlessWins the flawlessWins to set
	 */
	public final void setFlawlessWins(int flawlessWins) {
		this.flawlessWins = flawlessWins;
	}
	
	public final void incrementFlawlessWins(int inc){
		this.flawlessWins += inc;
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
	
	public final void incrementFiredShots(int inc){
		this.firedShots += inc;
	}

	/**
	 * @return the missedShots
	 */
	public final int getMissedShots() {
		return missedShots;
	}

	/**
	 * @param missedShots the missedShots to set
	 */
	public final void setMissedShots(int missedShots) {
		this.missedShots = missedShots;
	}
	
	public final void incrementMissedShots(int inc){
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
	
	public final void incrementShotsHit(int inc){
		this.shotsHit += inc;
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
	
	public final void incrementShipsLost(int inc){
		this.shipsLost += inc;
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
	
	public final void incrementShipsDestroyed(int inc){
		this.shipsDestroyed += inc;
	}

	/**
	 * @return the navalMinesHits
	 */
	public final int getNavalMinesHits() {
		return navalMinesHits;
	}

	/**
	 * @param navalMinesHits the navalMinesHits to set
	 */
	public final void setNavalMinesHits(int navalMinesHits) {
		this.navalMinesHits = navalMinesHits;
	}
	
	public final void incrementNavalMinesHit(int inc){
		this.navalMinesHits += inc;
	}

	/**
	 * @return the coastalArtilleryHits
	 */
	public final int getCoastalArtilleryHits() {
		return coastalArtilleryHits;
	}

	/**
	 * @param coastalArtilleryHits the coastalArtilleryHits to set
	 */
	public final void setCoastalArtilleryHits(int coastalArtilleryHits) {
		this.coastalArtilleryHits = coastalArtilleryHits;
	}
	
	public final void incrementCoastalArtilleryHits(int inc){
		this.coastalArtilleryHits += inc;
	}
	
	/**
	 * 
	 * @param gs
	 */
	public void addGameStatistics(GameStatistics gs){
		firedShots += gs.getFiredShots();
		shotsHit += gs.getShotsHit();
		missedShots += gs.getShotsMissed();
		shipsLost += gs.getShipsLost();
		shipsDestroyed += gs.getShipsDestroyed();
		navalMinesHits += gs.getNavalMinesHit();
		coastalArtilleryHits += gs.isCoastalArtilleryHit() ? 1 : 0;
		if(gs.isFlawlessWin()){
			flawlessWins++;
		}
		if(gs.isWin()){
			gamesWon++;
		}else{
			gamesLost++;
		}
	}

}
