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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		this.direction = e.getKeyCode();
		
		if (this.direction == KeyEvent.VK_LEFT)
			System.out.println("LEft");
		else if (this.direction == KeyEvent.VK_RIGHT)
			System.out.println("Right");
		else if (this.direction == KeyEvent.VK_DOWN)
			System.out.println("Down");
		else if (this.direction == KeyEvent.VK_UP)
			System.out.println("Up");
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
				
	}
}
