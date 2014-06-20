package de.htwg.se.tpn.controller;
import java.util.Observable;
import java.util.Observer;

import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.DirectionInterface;
import de.htwg.se.tpn.model.GameFieldInterface;

public class TpnController  extends Observable implements TpnControllerInterface {
	private GameFieldInterface gamefield;
	private DirectionInterface lastDirection;
	private boolean hasMoved;
	
	public TpnController(int size, int inserts, Observer o) {
		gameInit(size, inserts);
		this.addObserver(o);
	}

	protected final void gameInit(int size, int inserts) {
		gamefield = new GameField(size);
		for (int i = 0; i < inserts; i++) {
			gamefield.insertRandomNumberTile();
		}
	}

	public int getValue(int row, int collumn) {
		return gamefield.getValue(row, collumn);
	}
	
	public void insert(int chance, int row, int collumn) {
		gamefield.insertNumberTile(chance, row, collumn);
	}

	public boolean actionLeft() {
		return actionDir(new Direction.Left());
	}

	public boolean actionRight() {
		return actionDir(new Direction.Right());
	}

	public boolean actionUp() {
		return actionDir(new Direction.Up());
	}

	public boolean actionDown() {
		return actionDir(new Direction.Down());
	}

	private boolean actionDir(Direction direction) {
		hasMoved = false;

		hasMoved = gamefield.moveTiles(direction);
		hasMoved = gamefield.mergeTiles(direction) || hasMoved;
		hasMoved = gamefield.moveTiles(direction) || hasMoved;

		if (gamefield.getEmptyPlaces().isEmpty() && !gamefield.trymerge()) {
			setChanged();
			notifyObservers();
			return false;
		}
		if (!hasMoved && direction.equals(lastDirection)) {
			return false;
		}
		gamefield.insertRandomNumberTile();
		lastDirection = direction;
		return true;
	}
}