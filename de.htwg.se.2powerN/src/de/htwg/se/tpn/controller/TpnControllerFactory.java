package de.htwg.se.tpn.controller;

import com.google.inject.assistedinject.Assisted;

public interface TpnControllerFactory {
	TpnControllerInterface create(@Assisted("size") int size, @Assisted("inserts") int inserts);
}
