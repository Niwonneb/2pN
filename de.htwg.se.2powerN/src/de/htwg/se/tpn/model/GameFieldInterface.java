package de.htwg.se.tpn.model;

import java.awt.Point;
import java.util.List;

public interface GameFieldInterface {
	int getValue(int row,  int collumn);
	boolean mergeTiles(Direction direction);
	boolean moveTiles(Direction direction);
	void insertNumberTile(int chance, int row, int collumn);
	boolean insertRandomNumberTile();
	List<Point> getEmptyPlaces();
	boolean trymerge();
}
