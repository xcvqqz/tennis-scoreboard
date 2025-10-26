package util;

import exception.DataBaseException;
import model.Match;
import model.Player;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Optional;
import java.util.Properties;

public class HibernateUtil {

    private static final Properties hibernateProperties = new Properties();
    ;
    private static SessionFactory sessionFactory;
    private static final String HIBERNATE_CONFIGURATION_ERROR = "Hibernate Configuration Error";
    private static final String HIBERNATE_CONFIGURATION_PATH = "hibernate.properties";
    private static final String DB_PATH = "sql_script/init_database.sql";


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                hibernateProperties.load(HibernateUtil.class.getClassLoader().
                        getResourceAsStream(HIBERNATE_CONFIGURATION_PATH));

                configuration.setProperties(hibernateProperties);

                configuration.addAnnotatedClass(Player.class)
                        .addAnnotatedClass(Match.class)
                        .addAnnotatedClass(Optional.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                initDatabase();

            } catch (Exception e) {
                shutdown();
                throw new DataBaseException(HIBERNATE_CONFIGURATION_ERROR);
            }
        }
        return sessionFactory;
    }

    private static void initDatabase() {
        DataBaseInitalizer.execute(DB_PATH);
    }

    private static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}