package de.htwg.se.tpn.util.persistence.hibernate;

import java.util.List;

import de.htwg.se.tpn.model.*;
import de.htwg.se.tpn.util.persistence.ITpnDao;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDao implements ITpnDao {

    private SaveGame copySaveGame(PersistentSaveGame pSaveGame) {
        if (pSaveGame == null) {
            return null;
        }
        PersistentTile[][] pGrid = pSaveGame.getGameField();
        Tile[][] grid = new Tile[pGrid.length][pGrid.length];

        int rowNr = 0;
        for (PersistentTile[] row : pGrid) {
            int tileNr = 0;
            for (PersistentTile tile : row) {
                grid[rowNr][tileNr] = new NumberTile(tile.getValue());
                ++tileNr;
            }
            ++rowNr;
        }

        GameFieldInterface gameField = new GameField(grid);
        SaveGame saveGame = new SaveGame(gameField , pSaveGame.getId());

        return saveGame;
    }

    private PersistentSaveGame copySaveGame(SaveGame saveGame) {
        if(saveGame == null) {
            return null;
        }
        PersistentSaveGame pSaveGame = new PersistentSaveGame();
        pSaveGame.setId(saveGame.getId());

        GameFieldInterface gameField = saveGame.getGameField();
        int height = gameField.getHeight();

        PersistentTile[][] pGrid = new PersistentTile[height][height];

        for (int rowNr = 0; rowNr < height; ++rowNr) {
            for (int tileNr = 0; tileNr < height; ++tileNr) {
                pGrid[rowNr][tileNr] = new PersistentTile(gameField.getValue(rowNr, tileNr));
            }
        }

        pSaveGame.setGameField(pGrid);

        return pSaveGame;
    }

    @Override
    public void createOrUpdateGame(GameFieldInterface game, String id) {
        Transaction tx = null;
        Session session = null;

        try {
            session = HibernateUtil.getInstance().getCurrentSession();
            tx = session.beginTransaction();

            PersistentSaveGame pSaveGame = copySaveGame(new SaveGame(game, id));

            session.saveOrUpdate(pSaveGame);

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public SaveGame findGame(String id) {
        Session session = HibernateUtil.getInstance().getCurrentSession();
        session.beginTransaction();

        return copySaveGame((PersistentSaveGame) session.get(PersistentSaveGame.class, id));
    }

    @Override
    public void closeDb() {

    }
}