package de.htwg.se.tpn.controller;
import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;

public class TpnController {
	private GameField gamefield;
	private static final int FIELDSIZE = 4;
	
	public TpnController() {
		gameInit();
	}
	
	public final void gameInit() {
		gamefield = new GameField(FIELDSIZE);
		gamefield.insertRandomNumberTile();
		gamefield.insertRandomNumberTile();
	}
	
	public int getValue(int row, int collumn) {
		return gamefield.getValue(row, collumn);
	}
	
	public void actionLeft() {
		actionDir(Direction.LEFT);
	}
	
	public void actionRight() {
		actionDir(Direction.RIGHT);
	}
	
	public void actionUp() {
		actionDir(Direction.UP);
	}
	
	public void actionDown() {
		actionDir(Direction.DOWN);
	}
	
	private void actionDir(int direction) {
		gamefield.insertRandomNumberTile();
		Direction d = new Direction(direction);
		gamefield.moveTiles(d);
		gamefield.mergeTiles(d);
		gamefield.moveTiles(d);
	}
}