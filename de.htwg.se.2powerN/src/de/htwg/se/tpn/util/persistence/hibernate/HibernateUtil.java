package de.htwg.se.tpn.util.persistence.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private HibernateUtil() {
    }

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            createSession();
        }
        return sessionFactory;
    }

    private static void createSession() {
        Configuration hibConfiguration = new Configuration()
                .addResource("/hibernate.cfg.xml")
                .configure();

        serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(hibConfiguration.getProperties())
                .buildServiceRegistry();

        sessionFactory = hibConfiguration.buildSessionFactory(serviceRegistry);
    }

}
