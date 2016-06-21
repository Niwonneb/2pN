package de.htwg.se.tpn.persistence.db4o;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import de.htwg.se.tpn.persistence.AbstractDao;
import de.htwg.se.tpn.persistence.PersistenceStrategy;

public class Db4oDao extends AbstractDao {

    @Override
    public boolean createOrUpdateGame(GameFieldInterface game, String id) {
        if (findGame(id) == null) {
            Db4oSessionManager.getDbObjectContainer().store(new SaveGame(game, id));
        } else {
            SaveGame old = findGame(id);
            Db4oSessionManager.getDbObjectContainer().delete(old);
            old.setGameField(game);
            Db4oSessionManager.getDbObjectContainer().store(old);
        }
        close();
        return true;
    }

    @Override
    public SaveGame findGame(String id) {
        ObjectSet<SaveGame> objectSet = Db4oSessionManager.getDbObjectContainer().query(new Predicate<SaveGame>() {
            private static final long serialVersionUID = 4362352052175053179L;

            @Override
            public boolean match(SaveGame game) {
                return game.getId().equals(id);
            }
        });
        if (objectSet.isEmpty()) {
            close();
            return null;
        }
        SaveGame game = objectSet.get(0);
        close();
        return game;
    }

    @Override
    public PersistenceStrategy getStrategy() {
        return PersistenceStrategy.db4o;
    }

    @Override
    public boolean init() {
        return true;
    }

    private void close() {
        Db4oSessionManager.getDbObjectContainer().close();
    }
}
