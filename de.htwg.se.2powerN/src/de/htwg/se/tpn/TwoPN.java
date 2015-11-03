package de.htwg.se.tpn;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerFactory;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.view.GUI;
import de.htwg.se.tpn.view.TUI;

public final class TwoPN {
	@Inject
	private TpnControllerFactory controllerfactory;
	private static final int FIELDSIZE = 4;
	private TpnControllerInterface controller;
	private static TwoPN instance = null;

	public TwoPN() {
		controller = new TpnController(FIELDSIZE, 1);
		new GUI(controller);
		new TUI(controller);
	}

	public static TwoPN getInstance() {
		if (instance == null) {
			Injector injector = Guice.createInjector(new TpNModule());
			instance = injector.getInstance(TwoPN.class);
		}
		return instance;
	}

	public TpnControllerInterface getController() {
		return this.controller;
	}

	public static void main(String[] args) {
		TwoPN game = getInstance();
	}
}