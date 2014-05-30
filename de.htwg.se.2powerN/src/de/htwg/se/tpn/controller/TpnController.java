package de.htwg.se.tpn.controller;
import java.util.Observer;

import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;

public class TpnController implements TpnControllerInterface {
	private GameField gamefield;
	
	public TpnController(int size, Observer o) {
		gameInit(size);
		gamefield.addObserver(o);
	}
	
	protected final void gameInit(int size) {
		gamefield = new GameField(size);
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
		boolean inserted = false;
		gamefield.moveTiles(direction);
		gamefield.mergeTiles(direction);
		gamefield.moveTiles(direction);
		inserted = gamefield.insertRandomNumberTile();
		gamefield.moveTiles(direction);
		if (!inserted) {
			gamefield.trymerge();
		}
	}
}