package de.htwg.se.tpn.persistence.messages;

import de.htwg.se.tpn.model.GameFieldInterface;

public class LoadGameResult {

    public final GameFieldInterface gamefield;
    public final String name;

    public LoadGameResult(GameFieldInterface gamefield, String name) {
        this.gamefield = gamefield;
        this.name = name;
    }

    public String toString() {
        return "load game " + gamefield + ". success? " +  Boolean.toString(gamefield != null);
    }
}
