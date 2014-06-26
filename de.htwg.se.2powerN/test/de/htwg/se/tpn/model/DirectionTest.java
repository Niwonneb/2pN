package de.htwg.se.tpn.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testEquals() {
		Direction right1 = new Direction.Right();
		Direction right2 = new Direction.Right();
		Direction left = new Direction.Left();
		Object o = new Object();
		Direction nulld = null;
		
		assertEquals(true, right1.equals(right2));
		assertEquals(false, right1.equals(left));
		assertEquals(false, right1.equals(nulld));
		assertEquals(false, right1.equals(o));

		assertEquals(right1.hashCode(), right1.hashCode());
	}

}
