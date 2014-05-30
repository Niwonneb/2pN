package de.htwg.se.tpn.model;
import java.util.Observable;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.awt.Point;

public final class GameField extends Observable implements GameFieldInterface{
	
	private Tile[][] grid;
	private int height;
	private static final int DOUBLECHANCE = 20;
	private static final int PERCMAX = 100;
	
	public GameField(int height) {
		this.grid = new Tile[height][height];
		this.height = height;
	} 
	
	protected Tile[][] getGrid() {
		return grid;
	}
	
	protected int getHeight() {
		return height;
	}
	
	public int getValue(int row,  int collumn) {
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

	public void moveTiles(Direction direction) {
		for (int i = 0; i < height; i++) {
			moveTile(i, direction);
		}
	}

	/*
	 * The method moves the tiles according to the given direction.
	 */
	protected void moveTile(int index, Direction direction) {
 		direction.setStarts(index, height);
 		
 		int columnStart = direction.getcStart();
 		int rowStart = direction.getrStart();
 		int columnStep = direction.getcStep();
 		int rowStep = direction.getrStep();
 		// r := row 	c := column
 		int cNext, column;
 		int rNext, row;
 		
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
				}
				column = cNext;
				row = rNext;
	 		}
		} while (moved);
	}
	
	/*
	 * insert a new NumberTiles to (row,column) if it's empty
	 * @param chance percentage (0-100) of spawning a 4 
	 */
	protected void insertNumberTile(int chance, int row, int column) {
		Random rand = new Random();
		// --------------------------------------------- create random 1-100
		int random = rand.nextInt(PERCMAX + 1);
		NumberTile newTile = new NumberTile();
		// --------------------------------------------- chance of creating a 4
		if (random <= chance) {
			newTile.doubleValue();
		}
		grid[row][column] = newTile;
	}
	
	public boolean insertRandomNumberTile() {
		Random rand = new Random();
		List<Point> emptyPlaces = getEmptyPlaces();
		if (emptyPlaces.isEmpty()) {
			return false;
		}
		int idx = rand.nextInt(emptyPlaces.size());
		Point insertPlace = emptyPlaces.get(idx);
		insertNumberTile(DOUBLECHANCE, (int) insertPlace.getY(),
							 (int) insertPlace.getX());
		return true;
	}
	
	protected List<Point> getEmptyPlaces() {
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
	
	public void trymerge() {
		Tile[][] gridoriginal = grid;
		boolean merged = false;
		
		merged = mergeTiles(new Direction.Left()); 
		merged = merged || mergeTiles(new Direction.Right());
		merged = merged || mergeTiles(new Direction.Up());
		merged = merged || mergeTiles(new Direction.Down());
		grid = gridoriginal;
		if (!merged) {
			setChanged();
			notifyObservers();
		}
	}
}
