package de.htwg.se.tpn.util.persistence;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;

public class SaveAndLoadService {
    private ITpnDao gameDao;
    public SaveAndLoadService(ITpnDao gameDao) {
        this.gameDao = gameDao;
    }

    public boolean saveGame(GameFieldInterface game, String id) {
        boolean success = gameDao.createOrUpdateGame(game, id);
        gameDao.closeDb();
        return success;
    }

    public SaveGame loadGame(String id) {
        gameDao.closeDb();
        return gameDao.findGame(id);
    }
}
