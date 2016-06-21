package de.htwg.se.tpn.persistence.couchDb;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

/**
 * Created by Sergej on 12/04/16.
 */
public class CouchDbSession {
    private static CouchDbConnector couchDbConnector;
    private static final String HOST = "lenny2.in.htwg-konstanz.de";
    private static final int PORT = 5984;
    private CouchDbSession() {}

    public static CouchDbConnector getCouchDbConnector() {
        if (couchDbConnector == null) {
            HttpClient httpClient = new StdHttpClient.Builder().host(HOST).port(PORT).build();

            CouchDbInstance couchDbInstance = new StdCouchDbInstance(httpClient);
            couchDbConnector = new StdCouchDbConnector("tpn", couchDbInstance);
            couchDbConnector.createDatabaseIfNotExists();
        }
        return couchDbConnector;
    }
}
