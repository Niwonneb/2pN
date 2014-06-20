package de.htwg.se.tpn.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;

public class GameFieldTest {
	
	GameField gamefield;
	private static final int INITIALSIZE = 4; 

	@Before
	public void setUp() throws Exception {
		gamefield = new GameField(INITIALSIZE);
	}
	
	@Test
	public void testMoveTiles() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(100, 1, 0);
		gamefield.insertNumberTile(100, 2, 2);
		gamefield.insertNumberTile(100, 1, 3);
		
		gamefield.moveTiles(new Direction.Down());
		
		assertEquals(4, gamefield.getGrid()[3][0].getValue());
		assertEquals(2, gamefield.getGrid()[2][0].getValue());
		assertEquals(4, gamefield.getGrid()[3][2].getValue());
		assertEquals(4, gamefield.getGrid()[3][3].getValue());
		// 2 at (2,0); 4 at (3,0); 4 at (3,2); 4 at (3,3)
	}
	
	@Test
	public void testMergeTiles() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(0, 1, 0);
		gamefield.insertNumberTile(0, 3, 0);
		gamefield.insertNumberTile(100, 2, 2);
		gamefield.insertNumberTile(100, 3, 2);
		
		gamefield.mergeTiles(new Direction.Up());
		assertEquals(4, gamefield.getGrid()[0][0].getValue());
		assertEquals(2, gamefield.getGrid()[3][0].getValue());
		assertEquals(8, gamefield.getGrid()[2][2].getValue());
		// 4 at (0,0); 2 at (3,0); 8 at (2,2)
	}

	@Test
	public void testMoveTile() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(100, 1, 0);
		
		gamefield.moveTile(0, new Direction.Down());
		
		assertEquals(4, gamefield.getGrid()[3][0].getValue());
		assertEquals(2, gamefield.getGrid()[2][0].getValue());
		// 2 at (2,0); 4 at (3,0)
		
		gamefield.insertNumberTile(0, 3, 2);
		
		gamefield.moveTiles(new Direction.Right());
		
		assertEquals(2, gamefield.getGrid()[2][3].getValue());
		assertEquals(4, gamefield.getGrid()[3][2].getValue());
		assertEquals(2, gamefield.getGrid()[3][3].getValue());
		// 2 at (2,3); 2 at (3,3); 4 at (3,2)
		
		gamefield.moveTiles(new Direction.Up());
		
		assertEquals(4, gamefield.getGrid()[0][2].getValue());
		// 2 at (0,3); 2 at (1,3); 4 at (0,2)
		
		gamefield.moveTiles(new Direction.Left());
		
		assertEquals(4, gamefield.getGrid()[0][0].getValue());
		assertEquals(2, gamefield.getGrid()[0][1].getValue());
		// 2 at (0,1); 2 at (1,0); 4 at (0,0)
	}
	
	@Test
	public void testMergeTile() {
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(0, 1, 0);
		gamefield.insertNumberTile(0, 3, 0);
		
		gamefield.mergeTile(0, new Direction.Up());
		assertEquals(4, gamefield.getGrid()[0][0].getValue());
		// 4 at (0,0); 2 at (3,0)
		
		gamefield.insertNumberTile(100, 0, 1);
		gamefield.insertNumberTile(0, 0, 3);
		
		gamefield.mergeTile(0, new Direction.Left());
		assertEquals(8, gamefield.getGrid()[0][0].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0)
		
		gamefield.insertNumberTile(100, 1, 3);
		gamefield.insertNumberTile(100, 2, 3);
		
		gamefield.mergeTile(3, new Direction.Down());
		assertEquals(8, gamefield.getGrid()[2][3].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0); 8 at (2,3)
		
		gamefield.insertNumberTile(100, 2, 0);
		gamefield.insertNumberTile(100, 2, 1);
		
		gamefield.mergeTile(2, new Direction.Right());
		assertEquals(8, gamefield.getGrid()[2][1].getValue());
		// 8 at (0,0); 2 at (0,3); 2 at (3,0); 8 at (2,3); 8 at (2,1)
	}
	
	@Test
	public void testInsertNumberTile() {
		gamefield.insertNumberTile(0, 0, 0);
		int value = gamefield.getGrid()[0][0].getValue();
		assertEquals(2, value);
		
		gamefield.insertNumberTile(0, 3, 0);
		value = gamefield.getGrid()[3][0].getValue();
		assertEquals(2, value);
		
		gamefield.insertNumberTile(100, 0, 3);
		value = gamefield.getGrid()[0][3].getValue();
		assertEquals(4, value);
		
		gamefield.insertNumberTile(100, 3, 3);
		value = gamefield.getGrid()[3][3].getValue();
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
		for (int i = 3; i <= 4 * 4; i++) {
			insert = gamefield.insertRandomNumberTile();
		}
		assertEquals(false, insert);
	}
	
	private int getTilesInGameField(){
		int tilecount = 0;
		for (int i = 0; i < gamefield.getHeight(); ++i) {
			for (int j = 0; j < gamefield.getHeight(); ++j) {
				if (gamefield.getGrid()[i][j] != null)
					tilecount++;
			}		
		}
		return tilecount;
	}
	
	@Test
	public void testgetValue() {
		assertEquals(0, gamefield.getValue(0, 0));
		gamefield.insertNumberTile(0, 0, 0);
		assertEquals(2, gamefield.getValue(0, 0));
	}
	
	@Test
	public void testTryMerge() {
		assertEquals(false, gamefield.trymerge());
		
		gamefield.insertNumberTile(0, 0, 0);
		gamefield.insertNumberTile(0, 1, 0);
		
		assertEquals(true, gamefield.trymerge());
	}
}
