package de.htwg.se.tpn.controller;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObservable;

public interface TpnControllerInterface {

    void gameInit(GameFieldInterface gamefield, int inserts);

    boolean actionLeft();

    boolean actionRight();

    boolean actionUp();

    boolean actionDown();

    void gamereset(int size);

    void loadGame(String id);

    void saveGame(String id);
}
