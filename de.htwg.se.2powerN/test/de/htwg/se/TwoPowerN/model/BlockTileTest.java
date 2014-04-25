package de.htwg.se.TwoPowerN.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.TwoPowerN.model.BlockTile;

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
