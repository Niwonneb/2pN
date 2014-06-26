package de.htwg.se.tpn;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import de.htwg.se.tpn.controller.TpnControllerFactory;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.view.GUI;
import de.htwg.se.tpn.view.TUI;

public final class TwoPN {
	@Inject private TpnControllerFactory controllerfactory;

	public void run() {
		TpnControllerInterface controller = controllerfactory.create(4, 1);

		new GUI(controller);
		new TUI(controller);
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new TpNModule());
        TwoPN main = injector.getInstance(TwoPN.class);
        main.run();
	}
}