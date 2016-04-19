package de.htwg.se.tpn;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.htwg.se.tpn.controller.TpnController;
import de.htwg.se.tpn.controller.TpnControllerInterface;
import de.htwg.se.tpn.util.persistence.ITpnDao;
import de.htwg.se.tpn.util.persistence.couchDb.CouchDbDao;
import de.htwg.se.tpn.util.persistence.hibernate.HibernateDao;

public class TwoPNModule extends AbstractModule {

    private static final int FIELDSIZE = 4;
    private static final int INSERTS = 1;

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("size")).toInstance(FIELDSIZE);
        bind(Integer.class).annotatedWith(Names.named("inserts")).toInstance(INSERTS);
        bind(ITpnDao.class).to(CouchDbDao.class);
        bind(TpnControllerInterface.class).to(TpnController.class);
    }
}
