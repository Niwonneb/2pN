package de.htwg.se.tpn.util.persistence.db4o;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.util.persistence.ITpnDao;

/**
 * Created by Sergej on 23/03/16.
 */
public class TpnDao implements ITpnDao {

    @Override
    public void createOrUpdateGame(TpnControllerInterface game) {
        if (findGame(game.getId()) == null) {
            Db4oSessionManager.getDbObjectContainer().store(game);
        } else {
            TpnControllerInterface old = findGame(game.getId());

            Db4oSessionManager.getDbObjectContainer().store(old);
        }
    }

    @Override
    public TpnControllerInterface findGame(final String id) {
        ObjectSet<TpnControllerInterface> objectSet = Db4oSessionManager.getDbObjectContainer().query(new Predicate<TpnControllerInterface>() {
            private static final long serialVersionUID = 7407252057775053179L;

            @Override
            public boolean match(TpnControllerInterface game) {
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
