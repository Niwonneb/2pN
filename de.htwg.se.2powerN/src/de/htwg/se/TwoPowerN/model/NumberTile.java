package de.htwg.se.TwoPowerN.model;


public class NumberTile implements Tile {

	private int value;
	
	public NumberTile() {
		value = 2;
	}
	public void doubleValue() {
		value *= 2;
	}
	public int getValue() {
		return value;
	}
}