package de.htwg.se.tpn.controller;

import java.util.Observer;

import de.htwg.se.tpn.model.Direction;

public interface TpnControllerInterface {

	
	int getValue(int row, int collumn);
	void actionLeft();
	void actionRight();
	void actionUp();
	void actionDown();
}
