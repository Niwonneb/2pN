package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;
import de.htwg.se.tpn.model.*;

import java.util.Scanner;


public class TUI{
	
	public static TpnController controller;
	public static GameField gameField;
	private static Scanner inn;
	private static Tile tile;
	
	public TUI(){
		
		TUI.controller = new TpnController();
		TUI.gameField = new GameField(4);
		TUI.tile = new NumberTile();
		inn = new Scanner(System.in);
		
		
		
	}
	
	public static void main(String[] args) {

		
		
		System.out.println("Hello");
		int dChar;
		dChar = inn.nextInt();
		System.out.println(dChar);
		directionChar.dirChar(controller, dChar);
		gameField.insertRandomNumberTile();
		gameField.insertRandomNumberTile();
		System.out.println(tile.getValue());
	
	}

	


}