package de.htwg.se.tpn.model;

import java.awt.Point;
import java.util.List;

public interface GameFieldInterface {
    int getValue(int row, int collumn);

    int getHeight();

    boolean mergeTiles(Direction direction);

    boolean moveTiles(Direction direction);

    boolean insertRandomTiles(int count);

    void insertTile(int chance, int row, int collumn);

    boolean insertRandomTile();

    List<Point> getEmptyPlaces();

    boolean trymerge();
}
