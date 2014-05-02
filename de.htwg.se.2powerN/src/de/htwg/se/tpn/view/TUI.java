package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;
import de.htwg.se.tpn.model.Direction;
import de.htwg.se.tpn.model.GameField;

import java.awt.event.*;

public class TUI implements KeyListener, ActionListener{
	
	public TpnController c;
	public int direction;
	
	public TUI(){
		
		c = new TpnController();	
	}
	
	public void getDirection() {
		if (this.direction == KeyEvent.VK_LEFT)
			c.actionLeft();
		else if (this.direction == KeyEvent.VK_RIGHT)
			c.actionRight();
		else if (this.direction == KeyEvent.VK_DOWN)
			c.actionDown();
		else if (this.direction == KeyEvent.VK_UP)
			c.actionUp();
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		this.direction = e.getKeyCode();
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
				
	}
}
