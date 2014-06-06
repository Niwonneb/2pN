package de.htwg.se.tpn.controller;

public interface TpnControllerInterface {
	int getValue(int row, int collumn);
	boolean actionLeft();
	boolean actionRight();
	boolean actionUp();
	boolean actionDown();
}
