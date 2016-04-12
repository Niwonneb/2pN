package de.htwg.se.tpn.util.persistence;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;

public class SaveAndLoadService {
    private ITpnDao gameDao;
    public SaveAndLoadService(ITpnDao gameDao) {
        this.gameDao = gameDao;
    }

    public void saveGame(GameFieldInterface game, String id) {
        gameDao.createOrUpdateGame(game, id);
        gameDao.closeDb();
    }

    public SaveGame loadGame(String id) {
        gameDao.closeDb();
        return gameDao.findGame(id);
    }
}
