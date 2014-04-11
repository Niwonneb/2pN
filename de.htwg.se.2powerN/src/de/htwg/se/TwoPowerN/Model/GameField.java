package de.htwg.se.TwoPowerN.Model;
import java.util.Random;

public final class GameField {
	
	protected Tile[][] grid;
	protected int height;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int UP = 3;
	
	public GameField(int height){
		this.grid = new Tile[height][height];
		this.height = height;						
	} 
	
	/*
	 * merges the Tiles in 'direction' in the line 'index'
	 */
 	public void mergeTile(int index, int direction){
 		assert(direction == LEFT || direction == RIGHT || direction == DOWN || direction == UP);
 		
 		int collumnStep = 0, rowStep = 0;
 		int collumnStart = 0, rowStart = 0;
 		
 		
 		switch (direction) {
 		case LEFT:
 			rowStart = index;
 			collumnStep = 1;
 			break;
 		case RIGHT:
 			collumnStart = height - 1;
 			rowStart = index;
 			collumnStep = -1;
 			break;
 		case UP:
 			collumnStart = index;
 			rowStep = 1;
 			break;
 		case DOWN:
 			collumnStart = index;
 			rowStart = height - 1;
 			rowStep = -1;
 		}
 		
 		int column = collumnStart;
 		int row = rowStart;
 		int cNext;					// r := row 	c := column
 		int rNext;
 		
 		for (int i = 0; i < height - 1; ++i) {
 			cNext = column + collumnStep;
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
	 * Case 1: left
	 * Case 2: right
	 * Case 3: down
	 * Case 4: up
	 */
	public void moveTile(int direction){
		
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
