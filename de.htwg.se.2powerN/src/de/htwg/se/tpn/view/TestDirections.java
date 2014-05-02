package de.htwg.se.tpn.view;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.htwg.se.tpn.controller.*;

public class TestDirections {

	private static KeyEvent e;

	public static void main(String[] args) {
		

		System.out.println("Give the value");
		TUI c = new TUI();
		e = null;
		e.getKeyCode();
		c.keyPressed(e);
		
		
	}

}
