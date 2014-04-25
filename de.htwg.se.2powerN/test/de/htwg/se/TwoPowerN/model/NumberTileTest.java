package de.htwg.se.TwoPowerN.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.TwoPowerN.model.NumberTile;

public class NumberTileTest {

	NumberTile numberTile;
	
	@Before
	public void setUp() throws Exception {
		numberTile = new NumberTile();
	}

	@Test
	public void doubleValuetest() {
		numberTile.doubleValue();
		assertEquals(4, numberTile.getValue());
		numberTile.doubleValue();
		assertEquals(8, numberTile.getValue());
		numberTile.doubleValue();
		assertEquals(16, numberTile.getValue());
		numberTile.doubleValue();
		assertEquals(32, numberTile.getValue());
	}

}
