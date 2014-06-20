package de.htwg.se.tpn.controller;

import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

public class TpnControllerTest implements Observer{
TpnController c = new TpnController(4, this);

	@Before
	public void setUp() throws Exception {
		c.gameInit(4);
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
	
	@Test
	public void testgetValue() {
		c.getValue(0, 0);
	}

	@Override
	public void update(Observable o, Object arg) {
	}
}
