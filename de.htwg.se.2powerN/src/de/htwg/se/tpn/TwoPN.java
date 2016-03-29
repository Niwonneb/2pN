package de.htwg.se.tpn;

import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.util.persistence.db4o.TpnDao;
import de.htwg.se.tpn.view.GUI;
import de.htwg.se.tpn.view.TUI;

import java.util.Scanner;


public final class TwoPN {
    private static final int FIELDSIZE = 4;
    private TpnControllerInterface controller;
    private static TwoPN instance = null;

    public TwoPN() {
        controller = new TpnController(FIELDSIZE, 1, new TpnDao());
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