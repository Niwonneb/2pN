package de.htwg.se.TwoPowerN;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BlockTileTest {
	
	BlockTile blockTile;

	@Before
	public void setUp() throws Exception {
		blockTile = new BlockTile();
	}

	@Test
	public void testgetValue() {
		assertEquals(-1, blockTile.getValue());
	}
}
