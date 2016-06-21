package de.htwg.se.tpn.persistence;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;

public interface ITpnDao {

    boolean init();

    boolean createOrUpdateGame(GameFieldInterface game, String id);

    SaveGame findGame(String id);

    PersistenceStrategy getStrategy();
}