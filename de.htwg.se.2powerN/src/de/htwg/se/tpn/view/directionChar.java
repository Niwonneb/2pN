package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;

/*
 * This class has a simple method,
 * in order to have the direction and to have the main Programm
 * be not the complex
 */
public class directionChar {
	
	/*
	 * It just gives direction.
	 * @param controller the object from TpnController
	 * @param dirChar the movement commands
	 */
	public static void dirChar(final TpnController controller, final char directionChar) {
	
	if (directionChar == 'a')
		controller.actionLeft();
	else if (directionChar == 'd')
		controller.actionRight();
	else if (directionChar ==  'w')
		controller.actionUp();
	else if (directionChar == 's')
		controller.actionDown();
	}
	

}
