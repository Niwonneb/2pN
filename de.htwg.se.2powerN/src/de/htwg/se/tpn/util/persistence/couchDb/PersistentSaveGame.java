package de.htwg.se.tpn.util.persistence.couchDb;

import de.htwg.se.tpn.model.GameFieldInterface;
import de.htwg.se.tpn.model.SaveGame;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import java.util.List;

/**
 * Created by Sergej on 19/04/16.
 */
public class PersistentSaveGame extends CouchDbDocument {

    private static final long serialVersionUID = 7638702989285440560L;

    /**
     * @TypeDiscriminator is used to mark properties that makes this class's
     *                    documents unique in the database.
     */

    @TypeDiscriminator
    private String id;
    private String rev;
    private List<PersistentTile> tiles;
    @JsonIgnore
    private int size;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getRevision() {
        return rev;
    }

    @Override
    public void setRevision(String rev) {
        this.rev = rev;
    }

    public List<PersistentTile> getTiles() {
        return tiles;
    }

    public void setTiles(List<PersistentTile> tiles) {
        this.tiles = tiles;
    }

    public int getSize() {
        return (int)Math.sqrt(this.tiles.size());
    }
}
