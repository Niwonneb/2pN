package de.htwg.se.tpn.persistence.hibernate;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.htwg.se.tpn.model.*;
import de.htwg.se.tpn.persistence.AbstractDao;
import de.htwg.se.tpn.persistence.PersistenceStrategy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateDao extends AbstractDao {

    private static final Logger log = Logger.getLogger( HibernateDao.class.getName() );

    private SaveGame copySaveGame(PersistentSaveGame pSaveGame) {
        if (pSaveGame == null) {
            return null;
        }
        List<PersistentSaveGame.PersistentRow> pRows = pSaveGame.getRows();
        Tile[][] grid = new Tile[pRows.size()][pRows.size()];

        int rowNr = 0;
        for (PersistentSaveGame.PersistentRow row : pRows) {
            int tileNr = 0;
            for (PersistentTile tile : row.getTiles()) {
                Tile newTile;
                if (tile.getValue() == 0) {
                    newTile = null;
                } else {
                    newTile = new Tile(tile.getValue());
                }
                grid[rowNr][tileNr] = newTile;
                ++tileNr;
            }
            ++rowNr;
        }

        GameFieldInterface gameField = new GameField(grid);
        SaveGame saveGame = new SaveGame(gameField , pSaveGame.getSaveGameId());

        return saveGame;
    }

    private PersistentSaveGame copySaveGame(SaveGame saveGame) {
        if(saveGame == null) {
            return null;
        }
        PersistentSaveGame pSaveGame = new PersistentSaveGame();
        pSaveGame.setSaveGameId(saveGame.getId());

        GameFieldInterface gameField = saveGame.getGameField();
        int height = gameField.getHeight();

        List<PersistentSaveGame.PersistentRow> rows = new LinkedList<>();

        for (int rowNr = 0; rowNr < height; ++rowNr) {
            PersistentSaveGame.PersistentRow curRow = new PersistentSaveGame.PersistentRow();
            curRow.setSaveGame(pSaveGame);
            for (int tileNr = 0; tileNr < height; ++tileNr) {
                int value = gameField.getValue(rowNr, tileNr);
                PersistentTile pTile = new PersistentTile(value);
                pTile.setRow(curRow);

                curRow.getTiles().add(tileNr, pTile);
            }
            rows.add(rowNr, curRow);
        }

        pSaveGame.setRows(rows);

        return pSaveGame;
    }

    @Override
    public boolean createOrUpdateGame(GameFieldInterface game, String id) {
        Transaction tx = null;

        if (findGame(id) != null) {
            return false;
        }

        try {
            SessionFactory sessionFactory = HibernateUtil.getInstance();
            Session session = sessionFactory.getCurrentSession();
            sessionFactory.close();
            tx = session.beginTransaction();


            PersistentSaveGame pSaveGame = copySaveGame(new SaveGame(game, id));

            session.saveOrUpdate(pSaveGame);
            for (PersistentSaveGame.PersistentRow pRow : pSaveGame.getRows()) {
                session.saveOrUpdate(pRow);
                for (PersistentTile pTile : pRow.getTiles()) {
                    session.saveOrUpdate(pTile);
                }
            }

            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null)
                tx.rollback();
            log.log(Level.SEVERE, ex.toString(), ex);
            return false;
        }
        return true;
    }

    @Override
    public SaveGame findGame(String id) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.close();
        session.beginTransaction();
        return copySaveGame(session.get(PersistentSaveGame.class, id));
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public PersistenceStrategy getStrategy() {
        return PersistenceStrategy.hibernate;
    }
}