package de.htwg.se.tpn.controller;

import org.junit.Before;
import org.junit.Test;

public class TpnControllerTest {
TpnController c = new TpnController();

	@Before
	public void setUp() throws Exception {
		c.gameInit();
	}

	@Test
	public void testactionLeft() {
		c.actionLeft();
	}

	@Test
	public void testactionRight() {
		c.actionRight();
	}

	@Test
	public void testactionUp() {
		c.actionUp();
	}

	@Test
	public void testactionDown() {
		c.actionDown();
	}
}
