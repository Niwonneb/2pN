package de.htwg.se.tpn.controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

import de.htwg.se.tpn.TpNModule;
import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.DirectionInterface;
import de.htwg.se.tpn.model.GameFieldFactory;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.util.observer.Observable;

public class TpnController extends Observable implements TpnControllerInterface {
	private GameFieldInterface gamefield;
	private DirectionInterface lastDirection;
	private int inserts;
	private boolean hasMoved;
	
	@Inject private GameFieldFactory gameFieldFactory;

	@Inject
	public TpnController(@Assisted("size") int size, @Assisted("inserts") int inserts) {
		gameInit(size, inserts);
	}

	public final void gameInit(int size, int inserts) {
		Injector injector = Guice.createInjector(new TpNModule());
		injector.injectMembers(this);
		gamefield = gameFieldFactory.create(size);
		
		this.inserts = inserts;
		
		for (int i = 0; i < inserts; i++) {
			gamefield.insertRandomNumberTile();
		}
		notifyObservers(new NewGameEvent());
	}
	
	public void gamereset(int size) {
		Injector injector = Guice.createInjector(new TpNModule());
		injector.injectMembers(this);
		gamefield = gameFieldFactory.create(size);
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
		gamefield.insertRandomNumberTiles(inserts);
		lastDirection = direction;
		notifyObservers(new NewFieldEvent());
		return true;
	}
}