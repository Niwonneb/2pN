package de.htwg.se.tpn.util.persistence;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;

/**
 * Created by Sergej on 23/03/16.
 */
public interface ITpnDao {

    /**
     * @param game for database
     */
    void createOrUpdateGame(GameFieldInterface game, String id);

    /**
     * @param id id of game
     * @return game
     */
    SaveGame findGame(String id);

    /**
     * close database
     */
    void closeDb();
}