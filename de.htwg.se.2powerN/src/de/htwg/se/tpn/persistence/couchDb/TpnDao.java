package de.htwg.se.tpn.persistence.couchDb;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import de.htwg.se.tpn.persistence.AbstractDao;
import de.htwg.se.tpn.persistence.PersistenceStrategy;

/**
 * Created by Sergej on 12/04/16.
 */
public class TpnDao extends AbstractDao {

    private final Mapper mapper;
    public TpnDao() {
        this.mapper = new Mapper();
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean createOrUpdateGame(GameFieldInterface game, String id) {
        GameFieldInterface persistentGame = mapper.getPersistentGame(game);
        if (findGame(id) == null) {
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
    public PersistenceStrategy getStrategy() {
        return PersistenceStrategy.couchdb;
    }
}
