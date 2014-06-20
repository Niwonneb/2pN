package de.htwg.se.tpn.model;

import java.awt.Point;
import java.util.List;

public interface GameFieldInterface {
	int getValue(int row,  int collumn);
	int getHeight();
	boolean mergeTiles(Direction direction);
	boolean moveTiles(Direction direction);
	boolean insertRandomNumberTiles(int count);
	void insertNumberTile(int chance, int row, int collumn);
	boolean insertRandomNumberTile();
	List<Point> getEmptyPlaces();
	boolean trymerge();
}
