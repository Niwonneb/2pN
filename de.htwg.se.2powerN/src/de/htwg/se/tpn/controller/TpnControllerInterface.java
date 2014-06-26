package de.htwg.se.tpn.controller;

import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObservable;

public interface TpnControllerInterface extends IObservable{
	int getValue(int row, int column);
	void gameInit(int size, int inserts);
	int getSize();
	boolean actionLeft();
	boolean actionRight();
	boolean actionUp();
	boolean actionDown();
	void gamereset(int size);
	void insert(int chance, int row, int column);
	
	public static class NewFieldEvent extends Event {}
	public static class GameOverEvent extends Event {}
	public static class NewGameEvent extends Event {}
}
