package de.htwg.se.tpn;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerFactory;
import de.htwg.se.tpn.controller.TpnControllerInterface;

public class TpNModule implements Module {
	
	public void configure(Binder binder) {
		binder.install(new FactoryModuleBuilder()
	      	  .implement(TpnControllerInterface.class, TpnController.class)
	          .build(TpnControllerFactory.class));
    }
}
