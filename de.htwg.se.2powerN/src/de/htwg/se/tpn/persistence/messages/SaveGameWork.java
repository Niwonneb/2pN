package de.htwg.se.tpn.persistence.messages;

import de.htwg.se.tpn.model.GameFieldInterface;

public class SaveGameWork {

    public final GameFieldInterface gamefield;
    public final String id;

    public SaveGameWork(GameFieldInterface gamefield, String id) {
        this.gamefield = gamefield;
        this.id = id;
    }
}
