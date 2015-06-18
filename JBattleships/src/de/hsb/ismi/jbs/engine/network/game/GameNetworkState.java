/**
 * 
 */
package de.hsb.ismi.jbs.engine.network.game;

/**
 * Representation of a network-game.
 * @author Kevin Kuegler
 * @version 1.00
 */
public enum GameNetworkState {
	LOBBY_PRE_CREATED,
	LOBBY_CREATED,
	LOBBY_WAITING,
	LOBBY_NOT_READY,
	LOBBY_READY,
	LOBBY_SHIPS_SELECTING,
	LOBBY_SHIPS_SELECTED,
	GAME_STARTED,
	GAME_ABORTED,
	GAME_ENDED,
	GAME_WAITING,
	GAME_TURN,
	GAME_TURN_FINISHED
}
