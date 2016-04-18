package de.htwg.se.tpn;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.util.persistence.db4o.Db4oDao;
import de.htwg.se.tpn.util.persistence.hibernate.HibernateDao;
import de.htwg.se.tpn.view.GUI;
import de.htwg.se.tpn.view.TUI;


public final class TwoPN {
    private static final int FIELDSIZE = 4;
    private TpnControllerInterface controller;
    private static TwoPN instance = null;

    public TwoPN() {

        Injector injector = Guice.createInjector(new TwoPNModule());
        controller = injector.getInstance(TpnControllerInterface.class);
    }

    public static TwoPN getInstance() {
        if (instance == null) {
            instance = new TwoPN();
        }
        return instance;
    }

    public TpnControllerInterface getController() {
        return this.controller;
    }

    public static void main(String[] args) {
        TwoPN game = getInstance();
        game.startGUI();
        game.startTUI();
    }

    public void startTUI() {
        TUI tui = new TUI(controller);
    }

    public void startGUI() {
        GUI gui = new GUI(controller);
    }
}