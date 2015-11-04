package de.htwg.se.tpn.view;

import com.google.inject.Inject;
import de.htwg.se.tpn.controller.*;
import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObserver;

import java.util.Scanner;

public class TUI implements IObserver{

	private static final int TILESIZE = 7;
	
	private TpnControllerInterface controller;
	private boolean end;
	
	@Inject
	public TUI(TpnControllerInterface controller) {
		this.controller = controller;
		controller.addObserver(this);
		printField(controller.getSize());
		end = false;
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
	
	public final void processInput(String input) {
		if (end) {
			return;
		}

		switch (input) {
		case "a":
			controller.actionLeft();
			break;
		case "d":
			controller.actionRight();
			break;
		case "w":
			controller.actionUp();
			break;
		case "s":
			controller.actionDown();
			break;
		default:
			println("Please type w, a, s or d to play");
		}
	}
	
	private void println(String str) {
		print(str + "\n");
	}
	
	private void print(String str) {
		System.out.print(str);
	}

	@Override
	public void update(Event e) {
		if (e instanceof TpnControllerInterface.NewFieldEvent) {
			printField(controller.getSize());
		} else if (e instanceof TpnControllerInterface.GameOverEvent && !end) {
			println("Game Over");
			end = true;
		} else if (e instanceof TpnControllerInterface.NewGameEvent) {
			println("New Game");
			end = false;
			printField(controller.getSize());
		}
	}
}