package de.htwg.se.tpn.model;

public interface GameFieldInterface {
	int getValue(int row,  int collumn);
	boolean mergeTiles(Direction direction);
	boolean moveTiles(Direction direction);
	boolean insertRandomNumberTile();
	boolean trymerge();
}
