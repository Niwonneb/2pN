package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;

import java.util.Scanner;


public class TUI{
	
	public static TpnController controller;
	private static Scanner inn;
	
	public TUI(){
		
		TUI.controller = new TpnController();
		inn = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) {

		char dChar = inn.next().charAt(0);
		directionChar.dirChar(controller, dChar);
	
	}


}