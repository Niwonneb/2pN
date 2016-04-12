package de.htwg.se.tpn.model;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.tpn.model.Tile;

import static org.junit.Assert.assertEquals;

public class TileTest {

	Tile Tile;
	
	@Before
	public void setUp() throws Exception {
		Tile = new Tile();
	}

	@Test
	public void doubleValuetest() {
		Tile.doubleValue();

		assertEquals(4, Tile.getValue());
		Tile.doubleValue();
		assertEquals(8, Tile.getValue());
		Tile.doubleValue();
		assertEquals(16, Tile.getValue());
		Tile.doubleValue();
		assertEquals(32, Tile.getValue());
	}

}
