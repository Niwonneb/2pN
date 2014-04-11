package de.htwg.se.TwoPowerN.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.TwoPowerN.Model.GameField;

public class GameFieldTest {
	
	GameField gamefield;

	@Before
	public void setUp() throws Exception {
		gamefield = new GameField(4);
	}

	@Test
	public void testMergeTile() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(0, 1, 0);
		gamefield.insertNumberTile(0, 3, 0);
		
		gamefield.mergeTile(0, GameField.UP);
		assertEquals(4, gamefield.grid[0][0].getValue());
		// 4 at (0,0); 2 at (3,0)
		
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(100, 0, 1);
		gamefield.insertNumberTile(0, 0, 3);
		
		gamefield.mergeTile(0, GameField.LEFT);
		assertEquals(8, gamefield.grid[0][0].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0)
		
		gamefield.insertNumberTile(100, 1, 3);
		gamefield.insertNumberTile(100, 2, 3);
		
		gamefield.mergeTile(3, GameField.DOWN);
		assertEquals(8, gamefield.grid[2][3].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0); 8 at (2,3)
		
		gamefield.insertNumberTile(100, 2, 0);
		gamefield.insertNumberTile(100, 2, 1);
		
		gamefield.mergeTile(2, GameField.RIGHT);
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
		gamefield.insertRandomNumberTile(15);
		int tilecount = getTilesInGameField();
		assertEquals(15, tilecount);
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
