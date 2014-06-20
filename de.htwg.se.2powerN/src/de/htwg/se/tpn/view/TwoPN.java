package de.htwg.se.tpn.view;

import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerInterface;

public final class TwoPN {
	private TwoPN() {
	}

	public static void main(String[] args) {
		TpnControllerInterface controller = new TpnController(8, 2);
		new GUI(controller);
		new TUI(controller);
	}
}