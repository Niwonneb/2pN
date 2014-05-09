package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;

import java.util.Scanner;

public class TUI {

	private static final int TILESIZE = 7;
	
	private TpnController controller;
	private static Scanner inn;
	
	public TUI() {
		controller = new TpnController();
		controller.gameInit();
		inn = new Scanner(System.in);
		printField(4);
	}
	
	protected void printField(int height) {
		printSeperator(height);
		for (int row = 0; row < height; row++) {
			printNumbers(height, row);
			printSeperator(height);
		}
	}
	
	private void printSeperator(int height) {
		for (int i = 0; i < height; i++) {
			System.out.print("+");
			for (int j = 0; j < TILESIZE; j++)
				System.out.print("-");
		}
		System.out.println("+");
	}
	
	private void printNumbers(int height, int row) {
		printemptyTileLine(height);
		printTileLine(row, height);
		printemptyTileLine(height);
		
	}
	
	private void printemptyTileLine(int height) {
		for (int collumn = 0; collumn < height; collumn++) {
			printemptyTileSection();
		}
		System.out.println("|");
	}
	
	private void printemptyTileSection() {
		System.out.print("|");
		for (int i = 0; i < TILESIZE; i++) {
			System.out.print(" ");
		}
	}
	
	private void printTileLine(int row, int height) {
		int valueLength = 0;
		
		for (int collumn = 0; collumn < height; collumn++) {

			int value = controller.getValue(row, collumn);
			if (value == 0) {
				printemptyTileSection();
				continue;
			}
			
			String strValue = String.valueOf(value);
			valueLength = strValue.length();
			double spaces = (TILESIZE - valueLength);
			int spacesBefore = (int) Math.ceil(spaces / 2);
			int spacesAfter = (int) Math.floor(spaces / 2);

			System.out.print("|");
			for (int i = 0; i < spacesBefore; i++) {
				System.out.print(" ");
			}
			
			System.out.print(strValue);
			
			for (int i = 0; i < spacesAfter; i++) {
				System.out.print(" ");
			}
		}
			
		System.out.println("|");
	}
}