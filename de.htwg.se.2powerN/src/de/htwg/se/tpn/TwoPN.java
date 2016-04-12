package de.htwg.se.tpn;

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
        controller = new TpnController(FIELDSIZE, 1, new HibernateDao());
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