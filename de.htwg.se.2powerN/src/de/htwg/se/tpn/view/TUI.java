package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;

import java.util.Scanner;


public class TUI{
	
	public static TpnController controller;
	private static Scanner inn;
	
	public TUI(){
		
		TUI.controller = new TpnController();
		
	}
	
	public static void main(String[] args) {

		inn = new Scanner(System.in);
		String dChar = inn.nextLine();
		directionChar.dirChar(controller, dChar);
	
	}


}