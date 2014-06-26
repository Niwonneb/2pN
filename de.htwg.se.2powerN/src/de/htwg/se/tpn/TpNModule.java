package de.htwg.se.tpn;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerFactory;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldFactory;
import de.htwg.se.tpn.model.GameFieldInterface;

public class TpNModule implements Module {
	
	public void configure(Binder binder) {
		binder.install(new FactoryModuleBuilder()
		      .implement(GameFieldInterface.class, GameField.class)
		      .build(GameFieldFactory.class));
		binder.install(new FactoryModuleBuilder()
	      	  .implement(TpnControllerInterface.class, TpnController.class)
	          .build(TpnControllerFactory.class));
    }
}
