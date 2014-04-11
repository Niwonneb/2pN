package de.htwg.se.TwoPowerN.Model;
import java.util.Random;

public class GameField {
	
	protected Tile[][] grid;
	protected int height;
	
	public GameField(int height){
		this.grid = new Tile[height][height];
		this.height = height;
	}
	
	public void mergeTile(int line,int direction){
	}
	

	public void moveTile(int direction){
	}
	
	
	/*
	 * insert a new NumberTiles to (xcord,ycord) if it's empty
	 * @param chance percentage (0-100) of spawning a 4 
	 */
	public boolean insertNumberTile(int chance, int xcord, int ycord){
		if (grid[xcord][ycord] == null) {					// place is empty
			Random rand = new Random();
			int random = rand.nextInt(101);					// create random 1-100
			NumberTile newTile = new NumberTile();
			if (random <= chance)							// chance of creating a 4
				newTile.doubleValue();
			grid[xcord][ycord] = newTile;
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
			if (insertNumberTile(20, xcord, ycord))
				created++;
		}
	}
}
