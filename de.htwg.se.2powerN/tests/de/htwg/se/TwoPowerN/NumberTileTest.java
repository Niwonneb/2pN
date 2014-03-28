package de.htwg.se.TwoPowerN;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	
	@Test
	public void getValuetest() {
		
	}

}
