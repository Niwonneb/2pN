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
		actionDir(new Direction.Left());
	}
	
	public void actionRight() {
		actionDir(new Direction.Right());
	}
	
	public void actionUp() {
		actionDir(new Direction.Up());
	}
	
	public void actionDown() {
		actionDir(new Direction.Down());
	}
	
	private void actionDir(Direction direction) {
		gamefield.insertRandomNumberTile();
		gamefield.moveTiles(direction);
		gamefield.mergeTiles(direction);
		gamefield.moveTiles(direction);
	}
}