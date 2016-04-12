package de.htwg.se.tpn.util.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil() {
    }

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            createSession();
        }
        return sessionFactory;
    }

    private static void createSession() {
        Configuration configuration = new Configuration()
                .addResource("/hibernate.cfg.xml")
                .configure(); // configures settings from hibernate.cfg.xml

        configuration.addAnnotatedClass(PersistentTile.class);
        configuration.addAnnotatedClass(PersistentSaveGame.class);
        configuration.addAnnotatedClass(PersistentSaveGame.PersistentRow.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        // If you miss the below line then it will complaing about a missing dialect setting
        serviceRegistryBuilder.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

}
