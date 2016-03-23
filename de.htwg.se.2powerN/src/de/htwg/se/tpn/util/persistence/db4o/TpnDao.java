package de.htwg.se.tpn.util.persistence.db4o;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import de.htwg.se.tpn.util.persistence.ITpnDao;
/**
 * Created by Sergej on 23/03/16.
 */
public class TpnDao implements ITpnDao {

    @Override
    public void createOrUpdateGame(GameFieldInterface game, String id) {
        if (findGame(id) == null) {
            Db4oSessionManager.getDbObjectContainer().store(new SaveGame(game, id));
        } else {
            SaveGame old = findGame(id);
            old.setGameField(game);
            Db4oSessionManager.getDbObjectContainer().store(old);
        }
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
            return null;
        }
        return objectSet.get(0);
    }

    @Override
    public void closeDb() {
        Db4oSessionManager.getDbObjectContainer().close();
    }
}
