package de.htwg.se.TwoPowerN.Model;
import java.util.Random;

public class GameField {
	
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
 		
 		if (direction == LEFT || direction == RIGHT)
 			;
 		else if (direction == DOWN || direction == UP)
 			;
 
 		for (int j = 0; j < height; ++j) {
			if (grid [i + x][j ] == grid [i][j]) {
				grid[i][j - 1].doubleValue();
				grid [i][j] = null;
			} 
		}
 	}
// 		switch (direction) {
// 		case 0:
// 			int i = 0;
// 			while (i < height) {
// 				for (int j = 1; j < height + 1; ++j) {
// 					if (grid [i][j - 1] == grid [i][j]) {
// 						grid[i][j - 1].doubleValue();
// 						grid [i][j] = null;
// 					} 
// 				}
// 				++i;
// 			}
// 			break;
// 			
// 		case 1:
// 			i = 0;
// 			while (i < height + 1) {
// 				for (int j = height - 1; j < -1; --j) {
// 					if (grid [i][j + 1] == null && grid [i][j] != null) {
// 						grid[i][j + 1].doubleValue();
// 						grid [i][j] = null;
// 					} 
// 				}
// 				++i;
// 			}
// 			break;
// 			
// 		case 2:
// 			i = 0;
// 			while (i < height + 1) {
// 				for (int j = 0; j < height + 1; ++j) {
// 					if (grid [j - 1][i] == null && grid [j][i] != null) {
// 						grid[j - 1][i].doubleValue();
// 						grid [j][i] = null;
// 					}
// 				}
// 				++i;
// 			}
// 			break;
// 			
// 		case 3:
// 			i = 0;
// 			while (i < height + 1) {
// 				for (int j = height - 1; j < -1; --j) {
// 					if (grid [j + 1][i] == null && grid [j][i] != null) {
// 						grid[j + 1][i].doubleValue();
// 						grid [j][i] = null;
// 					}
// 				}
// 				++i;
// 			}
// 			break;
// 		}
// 	}
	
	

	/*
	 * The method moves the tiles according to the given direction.
	 * Case 1: left
	 * Case 2: right
	 * Case 3: down
	 * Case 4: up
	 */
	public void moveTile(int direction){
		
	}
//		switch (direction) {
//			case 0:
//				int i = 0;
//				while (i < height + 1) {
//					for (int j = 1; j < height +1; ++j) {
//						if (grid [i][j - 1] == null && grid [i][j] != null) {
//							grid[i][j - 1] = grid [i][j];
//							grid [i][j] = null;
//						} else if (grid [i][j - 1] == null && grid [i][j] == null && j < height) {
//							grid[i][j - 1] = grid [i][j + 1];
//							grid [i][j + 1] = null;
//						}
//					}
//					++i;
//				}
//				break;
//				
//			case 1:
//				i = 0;
//				while (i < height + 1) {
//					for (int j = height - 1; j < -1; --j) {
//						if (grid [i][j + 1] == null && grid [i][j] != null) {
//							grid[i][j + 1] = grid [i][j];
//							grid [i][j] = null;
//						} else if (grid [i][j + 1] == null && grid [i][j] == null & j > 0) {
//							grid[i][j - 1] = grid [i][j + 1];
//							grid [i][j + 1] = null;
//						}
//					}
//					++i;
//				}
//				break;
//				
//			case 2:
//				i = 0;
//				while (i < height + 1) {
//					for (int j = 0; j < height + 1; ++j) {
//						if (grid [j - 1][i] == null && grid [j][i] != null) {
//							grid[j - 1][i] = grid [j][i];
//							grid [j][i] = null;
//						} else if (grid [j + 1][i] == null && grid [j][i] == null & j < height) {
//							grid[j - 1][i] = grid [j + 1][i];
//							grid [j - 1][i] = null;
//						}
//					}
//					++i;
//				}
//				break;
//				
//			case 3:
//				i = 0;
//				while (i < height + 1) {
//					for (int j = height - 1; j < -1; --j) {
//						if (grid [j + 1][i] == null && grid [j][i] != null) {
//							grid[j + 1][i] = grid [j][i];
//							grid [j][i] = null;
//						} else if (grid [j + 1][i] == null && grid [j][i] == null & j > 0) {
//							grid[j - 1][i] = grid [j + 1][i];
//							grid [j + 1][i] = null;
//						}
//					}
//					++i;
//				}
//				break;
//		}
//			
//	}
	
	
	/*
	 * insert a new NumberTiles to (ycord,xcord) if it's empty
	 * @param chance percentage (0-100) of spawning a 4 
	 */
	public boolean insertNumberTile(int chance, int ycord, int xcord){
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
