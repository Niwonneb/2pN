package de.htwg.se.tpn.persistence.couchDb;

import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

public class PersistentTile extends CouchDbDocument {

    private static final long serialVersionUID = -7739542476179017937L;

    PersistentTile(int value) {
        setValue(value);
    }

    PersistentTile() {}

    /**
     * @TypeDiscriminator is used to mark properties that makes this class's
     *                    documents unique in the database.
     */
    @TypeDiscriminator
    public String id;

    public Integer value = 0;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
