package de.htwg.se.tpn.view;
import de.htwg.se.tpn.controller.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class TUI implements Observer{

	private static final int TILESIZE = 7;
	
	private TpnController controller;
	private boolean end;
	private static Scanner inn;
	
	public TUI(int fieldsize) {
		controller = new TpnController(fieldsize, this);
		inn = new Scanner(System.in);
		end = false;
		printField(fieldsize);
		readInput(fieldsize);
	}
	
	protected final void printField(int height) {
		printSeperator(height);
		for (int row = 0; row < height; row++) {
			printNumbers(height, row);
			printSeperator(height);
		}
	}
	
	private void printSeperator(int height) {
		for (int i = 0; i < height; i++) {
			print("+");
			for (int j = 0; j < TILESIZE; j++) {
				print("-");
			}
		}
		println("+");
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
		println("|");
	}
	
	private void printemptyTileSection() {
		print("|");
		for (int i = 0; i < TILESIZE; i++) {
			print(" ");
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

			print("|");
			for (int i = 0; i < spacesBefore; i++) {
				print(" ");
			}
			
			print(strValue);
			
			for (int i = 0; i < spacesAfter; i++) {
				print(" ");
			}
		}
			
		println("|");
	}
	
	protected final void readInput(int height) {
		String direction = "";
		while (!end) {
			println("Give the new direction");
			direction = inn.next();
			
			switch (direction) {
			case "4":
				if (controller.actionLeft()) {
					printField(height);
				}
				break;
			case "6":
				if (controller.actionRight()) {
					printField(height);
				}
				break;
			case "8":
				if (controller.actionUp()) {
					printField(height);
				}
				break;
			case "2":
				if (controller.actionDown()) {
					printField(height);
				}
				break;
			case "q":
				println("Game finished");
				System.exit(0);
			default:
				println("Please press only 2, 4, 6 or 8 to play or q to quit");
			}
		}
		
	}
	
	private void println(String str) {
		print(str + "\n");
	}
	
	private void print(String str) {
		System.out.print(str);
	}

	@Override
	public void update(Observable o, Object arg) {
		println("Game over");
		end = true;
	}
}