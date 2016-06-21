package de.htwg.se.tpn.controller;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObservable;

public interface TpnControllerInterface {
    int getValue(int row, int column);

    void gameInit(GameFieldInterface gamefield, int inserts);

    int getSize();

    boolean actionLeft();

    boolean actionRight();

    boolean actionUp();

    boolean actionDown();

    void gamereset(int size);

    void insert(int chance, int row, int column);

    void loadGame(String id);

    void saveGame(String id);
}
