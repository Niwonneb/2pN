package de.htwg.se.tpn.controller;
import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.DirectionInterface;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.util.observer.Observable;

public class TpnController extends Observable implements TpnControllerInterface {
	private GameFieldInterface gamefield;
	private DirectionInterface lastDirection;
	private boolean hasMoved;

	public TpnController(int size, int inserts) {
		gameInit(size, inserts);
	}

	public final void gameInit(int size, int inserts) {
		gamefield = new GameField(size);
		for (int i = 0; i < inserts; i++) {
			gamefield.insertRandomNumberTile();
		}
		notifyObservers(new NewGameEvent());
	}
	
	public int getSize() {
		return gamefield.getHeight();
	}

	public int getValue(int row, int column) {
		return gamefield.getValue(row, column);
	}
	
	public void insert(int chance, int row, int column) {
		gamefield.insertNumberTile(chance, row, column);
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
			notifyObservers(new GameOverEvent());
			return false;
		}
		if (!hasMoved && direction.equals(lastDirection)) {
			return false;
		}
		gamefield.insertRandomNumberTile();
		lastDirection = direction;
		notifyObservers(new NewFieldEvent());
		return true;
	}
}