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
	
 	public void mergeTile(int line, int direction){
 		assert(direction == LEFT || direction == RIGHT || direction == DOWN || direction == UP);
 		
 		int xChange = 0, yChange = 0;
 		int xStart = 0, yStart = 0;
 		
 		
 		switch (direction) {
 		case LEFT:
 			yStart = line;
 			xChange = 1;
 			break;
 		case RIGHT:
 			xStart = height - 1;
 			yStart = line;
 			xChange = -1;
 			break;
 		case UP:
 			xStart = line;
 			yChange = 1;
 			break;
 		case DOWN:
 			xStart = line;
 			yStart = height - 1;
 			yChange = -1;
 		}
 		
 		int x = xStart;
 		int y = yStart;
 		int xNext;
 		int yNext;
 		
 		for (int i = 0; i < height; ++i) {
 			xNext = x + yChange;
 			yNext = y + xChange;
			if (grid[y][x] != null && grid[yNext][xNext] != null
				&& grid[y][x].getValue() == grid[yNext][xNext].getValue()) {
				grid[y][x].doubleValue();
				grid[yNext][xNext] = null;
			} 
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
	 * insert a new NumberTiles to (ycord,xcord) if it's empty
	 * @param chance percentage (0-100) of spawning a 4 
	 */
	protected boolean insertNumberTile(int chance, int ycord, int xcord){
		if (grid[ycord][xcord] == null) {					// place is empty
			Random rand = new Random();
			int random = rand.nextInt(101);					// create random 1-100
			NumberTile newTile = new NumberTile();
			if (random <= chance)							// chance of creating a 4
				newTile.doubleValue();
			grid[ycord][xcord] = newTile;
			return true;
		}
		return false;
	}
	
	public void insertRandomNumberTile(int count) {
		Random rand = new Random();
		int created = 0;
		while (created < count) {
			int xcord = rand.nextInt(height);
			int ycord = rand.nextInt(height);
			if (insertNumberTile(20, ycord, xcord))
				created++;
		}
	}
}
