package de.htwg.se.tpn.model;

import java.awt.Point;
import java.util.List;

public interface GameFieldInterface {
	int getValue(int row,  int collumn);
	boolean mergeTiles(Direction direction);
	void moveTiles(Direction direction);
	boolean insertRandomNumberTile();
	void trymerge();

}
