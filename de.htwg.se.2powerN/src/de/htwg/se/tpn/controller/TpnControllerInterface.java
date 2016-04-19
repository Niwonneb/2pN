package de.htwg.se.tpn.controller;

import de.htwg.se.tpn.util.observer.Event;
import de.htwg.se.tpn.util.observer.IObservable;

public interface TpnControllerInterface extends IObservable {
    int getValue(int row, int column);

    void gameInit(int size, int inserts);

    int getSize();

    boolean processInput(String input);

    boolean actionLeft();

    boolean actionRight();

    boolean actionUp();

    boolean actionDown();

    void gamereset(int size);

    void insert(int chance, int row, int column);

    void loadGame(String id);

    boolean saveGame(String id);

    final class GameLoadedEvent extends Event {
    }

    final class NewFieldEvent extends Event {
    }

    final class GameOverEvent extends Event {
    }

    final class NewGameEvent extends Event {
    }
}
