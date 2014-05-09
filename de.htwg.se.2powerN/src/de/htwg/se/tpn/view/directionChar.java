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
	 * If j, then the program will terminate.
	 * @param controller the object from TpnController
	 * @param dirChar the movement commands
	 */
	public static void dirChar(final TpnController controller, final int directionChar) {
	
	if (directionChar == 4)
		controller.actionLeft();
	else if (directionChar == 6)
		controller.actionRight();
	else if (directionChar ==  8)
		controller.actionUp();
	else if (directionChar == 2)
		controller.actionDown();
	else if (directionChar == 69)
		System.exit(0);
	}
	

}
