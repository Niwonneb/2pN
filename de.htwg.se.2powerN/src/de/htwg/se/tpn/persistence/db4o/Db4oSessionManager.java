package de.htwg.se.tpn.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

/**
 * Created by Sergej on 23/03/16.
 */
public class Db4oSessionManager {
    private static final String FILENAME = "tpn.db";

    private static ObjectContainer db;

    private Db4oSessionManager() {
    }

    /**
     * @return objectContainer
     */
    public static ObjectContainer getDbObjectContainer() {
        if (db == null) {
            createDB();
        } else if (db.ext().isClosed()) {
            createDB();
        }
        return db;
    }

    /**
     * create DB
     */
    private static void createDB() {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        db = Db4oEmbedded.openFile(configuration, FILENAME);
    }
}
