package de.htwg.se.tpn;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.htwg.se.tpn.controller.TpnControllerFactory;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.view.GUI;
import de.htwg.se.tpn.view.TUI;
import de.htwg.se.tpn.view.TpNModule;

public final class TwoPN {
	@Inject private TpnControllerFactory controllerfactory;
	private static final int FIELDSIZE = 4;

	public void run() {
		TpnControllerInterface controller = controllerfactory.create(FIELDSIZE, 1);

		new GUI(controller);
		new TUI(controller);
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TpNModule());
        TwoPN main = injector.getInstance(TwoPN.class);
        main.run();
	}
}