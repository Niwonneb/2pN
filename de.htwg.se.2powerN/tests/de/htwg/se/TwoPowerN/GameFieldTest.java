package de.htwg.se.TwoPowerN;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameFieldTest {
	
	GameField gamefield;

	@Before
	public void setUp() throws Exception {
		gamefield = new GameField(4);
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
