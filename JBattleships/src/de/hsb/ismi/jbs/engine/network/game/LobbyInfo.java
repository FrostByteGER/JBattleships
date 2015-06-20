/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game;

import java.io.Serializable;

import de.hsb.ismi.jbs.engine.core.JBSPlayer;

/**
 * @author Kevin Kuegler
 * @version 1.00
 */
public class LobbyInfo implements Serializable{

	private int fieldSize = 10;
	private int destroyers = 1;
	private int frigates = 2;
	private int corvettes = 3;
	private int submarines = 4;
	private boolean useCoastalArtillery = false;
	private boolean useNavalMines = false;
	
	private JBSPlayer[] connectedPlayers = new JBSPlayer[0];
	
	/**
	 * 
	 */
	public LobbyInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param fieldSize
	 * @param destroyers
	 * @param frigates
	 * @param corvettes
	 * @param submarines
	 * @param useCoastalArtillery
	 * @param useNavalMines
	 */
	public LobbyInfo(int fieldSize, int destroyers, int frigates, int corvettes, int submarines, boolean useCoastalArtillery, boolean useNavalMines) {
		this.fieldSize = fieldSize;
		this.destroyers = destroyers;
		this.frigates = frigates;
		this.corvettes = corvettes;
		this.submarines = submarines;
		this.useCoastalArtillery = useCoastalArtillery;
		this.useNavalMines = useNavalMines;
	}

	/**
	 * @param fieldSize
	 * @param destroyers
	 * @param frigates
	 * @param corvettes
	 * @param submarines
	 * @param useCoastalArtillery
	 * @param useNavalMines
	 * @param connectedPlayers
	 */
	public LobbyInfo(int fieldSize, int destroyers, int frigates, int corvettes, int submarines, boolean useCoastalArtillery, boolean useNavalMines, JBSPlayer[] connectedPlayers) {
		this.fieldSize = fieldSize;
		this.destroyers = destroyers;
		this.frigates = frigates;
		this.corvettes = corvettes;
		this.submarines = submarines;
		this.useCoastalArtillery = useCoastalArtillery;
		this.useNavalMines = useNavalMines;
		this.connectedPlayers = connectedPlayers;
	}

	/**
	 * @return the fieldSize
	 */
	public final int getFieldSize() {
		return fieldSize;
	}

	/**
	 * @param fieldSize the fieldSize to set
	 */
	public final void setFieldSize(int fieldSize) {
		this.fieldSize = fieldSize;
	}

	/**
	 * @return the destroyers
	 */
	public final int getDestroyers() {
		return destroyers;
	}

	/**
	 * @param destroyers the destroyers to set
	 */
	public final void setDestroyers(int destroyers) {
		this.destroyers = destroyers;
	}

	/**
	 * @return the frigates
	 */
	public final int getFrigates() {
		return frigates;
	}

	/**
	 * @param frigates the frigates to set
	 */
	public final void setFrigates(int frigates) {
		this.frigates = frigates;
	}

	/**
	 * @return the corvettes
	 */
	public final int getCorvettes() {
		return corvettes;
	}

	/**
	 * @param corvettes the corvettes to set
	 */
	public final void setCorvettes(int corvettes) {
		this.corvettes = corvettes;
	}

	/**
	 * @return the submarines
	 */
	public final int getSubmarines() {
		return submarines;
	}

	/**
	 * @param submarines the submarines to set
	 */
	public final void setSubmarines(int submarines) {
		this.submarines = submarines;
	}

	/**
	 * @return the useCoastalArtillery
	 */
	public final boolean useCoastalArtillery() {
		return useCoastalArtillery;
	}

	/**
	 * @param useCoastalArtillery the useCoastalArtillery to set
	 */
	public final void setUseCoastalArtillery(boolean useCoastalArtillery) {
		this.useCoastalArtillery = useCoastalArtillery;
	}

	/**
	 * @return the useNavalMines
	 */
	public final boolean useNavalMines() {
		return useNavalMines;
	}

	/**
	 * @param useNavalMines the useNavalMines to set
	 */
	public final void setUseNavalMines(boolean useNavalMines) {
		this.useNavalMines = useNavalMines;
	}

	/**
	 * @return the connectedPlayers
	 */
	public final JBSPlayer[] getConnectedPlayers() {
		return connectedPlayers;
	}

	/**
	 * @param connectedPlayers the connectedPlayers to set
	 */
	public final void setConnectedPlayers(JBSPlayer[] connectedPlayers) {
		this.connectedPlayers = connectedPlayers;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LobbyInfo | Destroyers: " + destroyers + " | Frigates: "+ frigates + 
			   " | Corvettes: " + corvettes + " | Submarines: " + submarines + 
			   " | Fieldsize: " + fieldSize + " | Naval Mines: " + useNavalMines + 
			   " | Coastal Artillery: " + useCoastalArtillery + " | Players: " + connectedPlayers.length;
	}
	
	
	
	
}
