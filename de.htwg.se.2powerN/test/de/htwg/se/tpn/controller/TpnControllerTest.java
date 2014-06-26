package de.htwg.se.tpn.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TpnControllerTest {
	TpnController c;

	@Before
	public void setUp() throws Exception {
		c = new TpnController(2, 1);
		c.gamereset(2);
	}

	@Test
	public void testactionLeft() {
		c.insert(0, 0, 1);
		assertEquals(true, c.actionLeft());
		c.insert(100, 0, 1);
		c.insert(0, 1, 0);
		c.insert(100, 1, 1);
		assertEquals(false, c.actionLeft());
		c.insert(0, 0, 0);
		c.insert(100, 0, 1);
		c.insert(100, 1, 0);
		c.insert(0, 1, 1);
		c.actionLeft();
	}

	@Test
	public void testactionRight() {
		c.insert(0, 1, 0);
		assertEquals(true, c.actionRight());
		c.insert(0, 0, 1);
		c.insert(100, 1, 0);
		c.insert(100, 0, 0);
		assertEquals(false, c.actionRight());
		c.insert(0, 0, 0);
		c.insert(100, 0, 1);
		c.insert(100, 1, 0);
		c.insert(0, 1, 1);
		c.actionRight();
	}

	@Test
	public void testactionUp() {
		c.insert(0, 1, 0);
		assertEquals(true, c.actionUp());
		c.insert(0, 0, 1);
		c.insert(100, 1, 0);
		c.insert(100, 1, 1);
		assertEquals(false, c.actionUp());
		c.insert(0, 0, 0);
		c.insert(100, 0, 1);
		c.insert(100, 1, 0);
		c.insert(0, 1, 1);
		c.actionUp();
	}

	@Test
	public void testactionDown() {
		c.insert(0, 0, 0);
		assertEquals(true, c.actionDown());
		c.insert(100, 0, 1);
		c.insert(100, 0, 0);
		c.insert(0, 1, 1);
		assertEquals(false, c.actionDown());
		c.insert(0, 0, 0);
		c.insert(100, 0, 1);
		c.insert(100, 1, 0);
		c.insert(0, 1, 1);
		c.actionDown();
	}
	
	@Test
	public void testgetValue() {
		assertEquals(0, c.getValue(0, 0));
	}
	
	@Test
	public void testgetSize() {
		assertEquals(2, c.getSize());
	}
}
