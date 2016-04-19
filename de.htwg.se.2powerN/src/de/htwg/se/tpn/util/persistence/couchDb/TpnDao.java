package de.htwg.se.tpn.util.persistence.couchDb;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import de.htwg.se.tpn.util.persistence.ITpnDao;

/**
 * Created by Sergej on 12/04/16.
 */
public class TpnDao implements ITpnDao {

    private final Mapper mapper;
    public TpnDao() {
        this.mapper = new Mapper();
    }

    @Override
    public boolean createOrUpdateGame(GameFieldInterface game, String id) {
        GameFieldInterface persistentGame = mapper.getPersistentGame(game);
        if (findGame(game.getId()) == null) {
            CouchDbSession.getCouchDbConnector().create(persistentGame);
        } else {

        }
        return true;
    }

    @Override
    public SaveGame findGame(String id) {

        return null;
    }

    @Override
    public void closeDb() {
        // no need
    }
}
