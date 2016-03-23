package de.htwg.se.tpn.util.persistence;

import de.htwg.se.tpn.controller.TpnControllerInterface;

/**
 * Created by Sergej on 23/03/16.
 */
public interface ITpnDao {

    /**
     * @param game for database
     */
    void createOrUpdateGame(TpnControllerInterface game);

    /**
     * @param id id of game
     * @return game
     */
    TpnControllerInterface findGame(String id);

    /**
     * close database
     */
    void closeDb();
}