package de.htwg.se.tpn.controller;
import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;

public class TpnController {
	GameField gamefield;
	
	public TpnController() {
		gameInit();
	}
	
	public void gameInit() {
		gamefield = new GameField(4);
		gamefield.insertRandomNumberTile();
		gamefield.insertRandomNumberTile();
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