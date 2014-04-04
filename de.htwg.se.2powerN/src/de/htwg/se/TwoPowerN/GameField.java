package de.htwg.se.TwoPowerN;
import java.util.Random;

public class GameField {
	
	protected Tile[][] grid;
	protected int height;
	
	public GameField(int height){
		this.grid = new Tile[height][height];
		this.height = height;
		insertNumberTile(2);
	}
	
	public void mergeTile(int line,int direction){
	}
	
	public void moveTile(int line, int direction){
	}
	
	public void insertNumberTile(int number){
		int created= 0;
		while (created < number) {
			Random rand = new Random();
			int xcord = rand.nextInt(height + 1);
			int ycord = rand.nextInt(height + 1);
			
			if (grid[xcord][ycord] == null) {					// random place is empty
				int chance = rand.nextInt(101);					// create random 1-100
				NumberTile newTile = new NumberTile();
				if (chance <= 20)								// chance of creating a 4
					newTile.doubleValue();
				grid[xcord][ycord] = newTile;
				created++;
			}
		}
	}
}
