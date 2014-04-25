package de.htwg.se.TwoPowerN.model;
import java.util.Random;

public final class GameField {
	
	protected Tile[][] grid;
	protected int height;
	
	public GameField(int height){
		this.grid = new Tile[height][height];
		this.height = height;
	} 
	
	/*
	 * merges the Tiles in 'direction' in the line 'index'
	 */
 	public void mergeTile(int index, Direction direction){
 		direction.setStarts(index, height);
 		
 		int column = direction.getcStart();
 		int row = direction.getrStart();
 		int columnStep = direction.getcStep();
 		int rowStep = direction.getrStep();
 		int cNext;					// r := row 	c := column
 		int rNext;
 		
 		for (int i = 0; i < height - 1; ++i) {
 			cNext = column + columnStep;
 			rNext = row + rowStep;
			if (grid[row][column] != null && grid[rNext][cNext] != null
				&& grid[row][column].getValue() == grid[rNext][cNext].getValue()) {
				
				grid[row][column].doubleValue();
				grid[rNext][cNext] = null;
			}
			column = cNext;
			row = rNext;
		}
 	}
	
	

	/*
	 * The method moves the tiles according to the given direction.
	 */
	public void moveTile(int index, Direction direction){
 		direction.setStarts(index, height);
 		
 		int columnStart = direction.getcStart();
 		int rowStart = direction.getrStart();
 		int columnStep = direction.getcStep();
 		int rowStep = direction.getrStep();
 		int cNext, column;					// r := row 	c := column
 		int rNext, row;
 		
 		boolean moved = false;
 		do {

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
	protected boolean insertNumberTile(int chance, int row, int column){
		if (grid[row][column] == null) {					// place is empty
			Random rand = new Random();
			int random = rand.nextInt(101);					// create random 1-100
			NumberTile newTile = new NumberTile();
			if (random <= chance)							// chance of creating a 4
				newTile.doubleValue();
			grid[row][column] = newTile;
			return true;
		}
		return false;
	}
	
	public void insertRandomNumberTile(int count) {
		Random rand = new Random();
		int created = 0;
		while (created < count) {
			int column = rand.nextInt(height);
			int row = rand.nextInt(height);
			if (insertNumberTile(20, row, column))
				created++;
		}
	}
}
