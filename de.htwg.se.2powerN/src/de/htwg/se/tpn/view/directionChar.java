package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;


public class directionChar {
	
	public static void dirChar(final TpnController controller, final String directionChar) {
	
	if (directionChar == "a")
		controller.actionLeft();
	else if (directionChar == "d")
		controller.actionRight();
	else if (directionChar ==  "w")
		controller.actionUp();
	else if (directionChar == "s")
		controller.actionDown();
	}
	

}
