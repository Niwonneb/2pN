package de.htwg.se.tpn.model;

import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.awt.Point;

public class GameField implements GameFieldInterface {

    private Tile[][] grid;
    private int height;
    private static final int DOUBLECHANCE = 20;
    private static final int PERCMAX = 100;

    public GameField() {

    }

    public GameField(int height) {
        this.grid = new Tile[height][height];
        this.height = height;
    }

    protected Tile[][] getGrid() {
        return grid;
    }

    public int getHeight() {
        return height;
    }

    public int getValue(int row, int collumn) {
        if (grid[row][collumn] == null) {
            return 0;
        }
        return grid[row][collumn].getValue();
    }

    public boolean mergeTiles(Direction direction) {
        boolean merged = false;
        for (int i = 0; i < height; i++) {
            merged = mergeTile(i, direction) || merged;
        }
        return merged;
    }

    /*
     * merges the Tiles in 'direction' in the line 'index'
     */
    protected boolean mergeTile(int index, Direction direction) {
        direction.setStarts(index, height);
        boolean merged = false;

        int column = direction.getcStart();
        int row = direction.getrStart();
        int columnStep = direction.getcStep();
        int rowStep = direction.getrStep();
        // r := row 	c := column
        int cNext;
        int rNext;

        for (int i = 0; i < height - 1; ++i) {
            cNext = column + columnStep;
            rNext = row + rowStep;
            if (grid[row][column] != null && grid[rNext][cNext] != null
                    && grid[row][column].getValue() == grid[rNext][cNext].getValue()) {

                merged = true;
                grid[row][column].doubleValue();
                grid[rNext][cNext] = null;
            }
            column = cNext;
            row = rNext;
        }

        return merged;
    }

    public boolean moveTiles(Direction direction) {
        boolean moved = false;
        for (int i = 0; i < height; i++) {
            moved = moveTile(i, direction) || moved;
        }
        return moved;
    }

    /*
     * The method moves the tiles according to the given direction.
     */
    protected boolean moveTile(int index, Direction direction) {
        direction.setStarts(index, height);

        int columnStart = direction.getcStart();
        int rowStart = direction.getrStart();
        int columnStep = direction.getcStep();
        int rowStep = direction.getrStep();
        // r := row 	c := column
        int cNext, column;
        int rNext, row;

        boolean movedatall = false;
        boolean moved;
        do {
            moved = false;
            column = columnStart;
            row = rowStart;

            for (int i = 0; i < height - 1; ++i) {
                cNext = column + columnStep;
                rNext = row + rowStep;
                if (grid[row][column] == null && grid[rNext][cNext] != null) {
                    grid[row][column] = grid[rNext][cNext];
                    grid[rNext][cNext] = null;
                    moved = true;
                    movedatall = true;
                }
                column = cNext;
                row = rNext;
            }
        } while (moved);
        return movedatall;
    }

    /*
     * insert a new Tiles to (row,column) if it's empty
     * @param chance percentage (0-100) of spawning a 4
     */
    public void insertTile(int chance, int row, int collumn) {
        Random rand = new Random();
        // --------------------------------------------- create random 1-100
        int random = rand.nextInt(PERCMAX) + 1;
        Tile newTile = new Tile();
        // --------------------------------------------- chance of creating a 4
        if (random <= chance) {
            newTile.doubleValue();
        }
        grid[row][collumn] = newTile;
    }

    public boolean insertRandomTiles(int count) {
        for (int i = 0; i < count; i++) {
            if (!insertRandomTile()) {
                return false;
            }
        }
        return true;
    }

    public boolean insertRandomTile() {
        Random rand = new Random();
        List<Point> emptyPlaces = getEmptyPlaces();
        if (emptyPlaces.isEmpty()) {
            return false;
        }
        int idx = rand.nextInt(emptyPlaces.size());
        Point insertPlace = emptyPlaces.get(idx);
        insertTile(DOUBLECHANCE, (int) insertPlace.getY(),
                (int) insertPlace.getX());
        return true;
    }

    public List<Point> getEmptyPlaces() {
        LinkedList<Point> emptyPlaces = new LinkedList<Point>();
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < height; ++x) {
                if (grid[y][x] == null) {
                    emptyPlaces.add(new Point(x, y));
                }
            }
        }
        return emptyPlaces;
    }

    public boolean trymerge() {
        Tile[][] original = cloneGrid();
        boolean merged = false;

        merged = mergeTiles(new Direction.Left())
                || mergeTiles(new Direction.Right())
                || mergeTiles(new Direction.Up())
                || mergeTiles(new Direction.Down());
        grid = original;
        return merged;
    }

    public String getId() {
        return null;
    }

    private Tile[][] cloneGrid() {
        Tile[][] original = new Tile[height][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == null) {
                    original[i][j] = null;
                    continue;
                }
                try {
                    original[i][j] = (Tile) grid[i][j].clone();
                } catch (CloneNotSupportedException e) {
                    System.out.println("Fatal Error");
                    System.exit(1);
                }
            }
        }
        return original;
    }
}
