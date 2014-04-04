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
		int tilecount = getTilesInGameField();
		assertEquals(2, tilecount);
		gamefield.insertNumberTile(2);
		tilecount = getTilesInGameField();
		assertEquals(4, tilecount);
		
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
