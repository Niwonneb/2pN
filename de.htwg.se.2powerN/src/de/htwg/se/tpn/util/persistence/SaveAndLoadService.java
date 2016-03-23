package de.htwg.se.tpn.util.persistence;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;

/**
 * Created by Sergej on 23/03/16.
 */
public class SaveAndLoadService {
    private ITpnDao gameDao;
    public SaveAndLoadService(ITpnDao gameDao) {
        this.gameDao = gameDao;
    }

    public void saveGame(GameFieldInterface game, String id) {
        gameDao.createOrUpdateGame(game, id);
    }

    public SaveGame loadGame(String id) {
        return gameDao.findGame(id);
    }
}
