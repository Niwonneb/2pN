package de.htwg.se.tpn.persistence.couchDb;

import de.htwg.se.tpn.model.GameField;
import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import de.htwg.se.tpn.model.Tile;
import de.htwg.se.tpn.persistence.AbstractDao;
import de.htwg.se.tpn.persistence.PersistenceStrategy;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

public class CouchDbDao extends AbstractDao {

    private static CouchDbConnector db;

    public CouchDbDao() {

        HttpClient client = null;
        try {
            client = new StdHttpClient.Builder().url(
                    "http://lenny2.in.htwg-konstanz.de:5984").build();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("a_tpn_game", true);
    }

    private SaveGame copySaveGame(PersistentSaveGame pSaveGame) {
        if (pSaveGame == null) {
            return null;
        }

        int size = pSaveGame.getSize();

        Tile[][] grid = new Tile[size][size];

        for (int i = 0; i < size * size; ++i) {
            int row = Math.floorDiv(i, size);
            int column = i % size;
            if (pSaveGame.getTiles().get(i).getValue() == 0) {
                grid[row][column] = null;
            } else {
                grid[row][column] = new Tile(pSaveGame.getTiles().get(i).getValue());
            }
        }

        GameFieldInterface gameField = new GameField(grid);
        SaveGame saveGame = new SaveGame(gameField , pSaveGame.getId());

        return saveGame;
    }

    private PersistentSaveGame copySaveGame(GameFieldInterface saveGame, String id) {
        if(saveGame == null) {
            return null;
        }
        PersistentSaveGame pSaveGame = new PersistentSaveGame();
        pSaveGame.setId(id);

        GameFieldInterface gameField = saveGame;
        int height = gameField.getHeight();

        List<PersistentTile> tiles = new LinkedList<>();

        for (int rowNr = 0; rowNr < height; ++rowNr) {

            for (int tileNr = 0; tileNr < height; ++tileNr) {
                int value = gameField.getValue(rowNr, tileNr);
                tiles.add(new PersistentTile(value));
            }
        }

        pSaveGame.setTiles(tiles);

        return pSaveGame;
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean createOrUpdateGame(GameFieldInterface game, String id) {
        if (findGame(id) == null) {
            db.create(copySaveGame(game, id));

            return true;
        } else {
            db.update(copySaveGame(game, id));
        }

        return false;
    }

    @Override
    public SaveGame findGame(String _id) {
        ViewQuery query = new ViewQuery()
                .designDocId("_design/findid")
                .viewName("game")
                .key(_id);

        List<PersistentSaveGame> result = null;

        result = db.queryView(query, PersistentSaveGame.class);
        if (result.size() == 0) {
            return null;
        }

        return copySaveGame(result.get(0));
    }

    @Override
    public PersistenceStrategy getStrategy() {
        return PersistenceStrategy.couchdb;
    }
}
