package de.htwg.se.tpn.persistence.couchDb;

import de.htwg.se.tpn.model.GameFieldInterface;

/**
 * Created by Sergej on 12/04/16.
 */
public class Mapper {

    public GameFieldInterface getPersistentGame(GameFieldInterface gameField) {
        GameFieldInterface persistentGame = new PersistentGame();

        return persistentGame;
    }

}
