package de.htwg.se.TwoPowerN.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.TwoPowerN.Model.NumberTile;

public class NumberTileTest {

	NumberTile numberTile;
	
	@Before
	public void setUp() throws Exception {
		numberTile = new NumberTile();
	}

	@Test
	public void doubleValuetest() {
		assertEquals(4, numberTile.doubleValue().getValue());
		assertEquals(8, numberTile.doubleValue().getValue());
		assertEquals(16, numberTile.doubleValue().getValue());
		assertEquals(32, numberTile.doubleValue().getValue());
	}

}
