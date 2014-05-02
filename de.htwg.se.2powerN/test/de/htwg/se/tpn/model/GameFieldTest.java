package de.htwg.se.tpn.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;

public class GameFieldTest {
	
	GameField gamefield;

	@Before
	public void setUp() throws Exception {
		gamefield = new GameField(4);
	}
	
	@Test
	public void testMoveTiles() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(100, 1, 0);
		gamefield.insertNumberTile(100, 2, 2);
		gamefield.insertNumberTile(100, 1, 3);
		
		gamefield.moveTiles(new Direction(Direction.DOWN));
		
		assertEquals(4, gamefield.grid[3][0].getValue());
		assertEquals(2, gamefield.grid[2][0].getValue());
		assertEquals(4, gamefield.grid[3][2].getValue());
		assertEquals(4, gamefield.grid[3][3].getValue());
		// 2 at (2,0); 4 at (3,0); 4 at (3,2); 4 at (3,3)
	}
	
	@Test
	public void testMergeTiles() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(0, 1, 0);
		gamefield.insertNumberTile(0, 3, 0);
		gamefield.insertNumberTile(100, 2, 2);
		gamefield.insertNumberTile(100, 3, 2);
		
		gamefield.mergeTiles(new Direction(Direction.UP));
		assertEquals(4, gamefield.grid[0][0].getValue());
		assertEquals(2, gamefield.grid[3][0].getValue());
		assertEquals(8, gamefield.grid[2][2].getValue());
		// 4 at (0,0); 2 at (3,0); 8 at (2,2)
	}

	@Test
	public void testMoveTile() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(100, 1, 0);
		
		gamefield.moveTile(0, new Direction(Direction.DOWN));
		
		assertEquals(4, gamefield.grid[3][0].getValue());
		assertEquals(2, gamefield.grid[2][0].getValue());
		// 2 at (2,0); 4 at (3,0)
		
		gamefield.insertNumberTile(0, 3, 2);
		
		gamefield.moveTile(3, new Direction(Direction.RIGHT));
		
		assertEquals(2, gamefield.grid[3][3].getValue());
		assertEquals(4, gamefield.grid[3][2].getValue());
		// 2 at (2,0); 2 at (3,3); 4 at (3,2)
		
		gamefield.moveTile(2, new Direction(Direction.UP));
		
		assertEquals(4, gamefield.grid[0][2].getValue());
		// 2 at (2,0); 2 at (3,3); 4 at (0,2)
		
		gamefield.moveTile(1, new Direction(Direction.LEFT));
		
		assertEquals(4, gamefield.grid[0][2].getValue());
		assertEquals(2, gamefield.grid[3][3].getValue());
		// 2 at (2,0); 2 at (3,3); 4 at (0,2)
	}
	
	@Test
	public void testMergeTile() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(0, 1, 0);
		gamefield.insertNumberTile(0, 3, 0);
		
		gamefield.mergeTile(0, new Direction(Direction.UP));
		assertEquals(4, gamefield.grid[0][0].getValue());
		// 4 at (0,0); 2 at (3,0)
		
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(100, 0, 1);
		gamefield.insertNumberTile(0, 0, 3);
		
		gamefield.mergeTile(0, new Direction(Direction.LEFT));
		assertEquals(8, gamefield.grid[0][0].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0)
		
		gamefield.insertNumberTile(100, 1, 3);
		gamefield.insertNumberTile(100, 2, 3);
		
		gamefield.mergeTile(3, new Direction(Direction.DOWN));
		assertEquals(8, gamefield.grid[2][3].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0); 8 at (2,3)
		
		gamefield.insertNumberTile(100, 2, 0);
		gamefield.insertNumberTile(100, 2, 1);
		
		gamefield.mergeTile(2, new Direction(Direction.RIGHT));
		assertEquals(8, gamefield.grid[2][1].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0); 8 at (2,3); 8 at (2,1)
	}
	
	@Test
	public void testInsertNumberTile() {
		gamefield.insertNumberTile(0, 0, 0);
		int value = gamefield.grid[0][0].getValue();
		assertEquals(2, value);
		
		gamefield.insertNumberTile(0, 3, 0);
		value = gamefield.grid[3][0].getValue();
		assertEquals(2, value);
		
		gamefield.insertNumberTile(100, 0, 3);
		value = gamefield.grid[0][3].getValue();
		assertEquals(4, value);
		
		gamefield.insertNumberTile(100, 3, 3);
		value = gamefield.grid[3][3].getValue();
		assertEquals(4, value);
		
		int tilecount = getTilesInGameField();
		assertEquals(4, tilecount);
	}
	
	@Test
	public void testInsertRandomNumberTile() {
		gamefield.insertRandomNumberTile();
		gamefield.insertRandomNumberTile();
		gamefield.insertRandomNumberTile();
		int tilecount = getTilesInGameField();
		assertEquals(3, tilecount);
		boolean insert = true;
		for (int i = 3; i < 4 * 4; i++) {
			insert = gamefield.insertRandomNumberTile();
		}
		assertEquals(false, insert);
	}
	
	private int getTilesInGameField(){
		int tilecount = 0;
		for (int i = 0; i < gamefield.height; ++i) {
			for (int j = 0; j < gamefield.height; ++j) {
				if (gamefield.grid[i][j] != null)
					tilecount++;
			}		
		}
		return tilecount;
	}
}
