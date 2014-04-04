package de.htwg.se.TwoPowerN;

public class NumberTile implements Tile {

	private int value;
	
	public NumberTile() {
		value = 2;
	}
	public NumberTile doubleValue() {
		value *= 2;
		return this;
	}
	public int getValue() {
		return value;
	}
}
