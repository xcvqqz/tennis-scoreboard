package util;

import exception.DataBaseException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.Properties;

public class HibernateUtil {

    private static final Properties hibernateProperties  = new Properties();;
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    private static final String HIBERNATE_CONFIGURATION_ERROR = "Hibernate Configuration Error";
    private static final String HIBERNATE_CONFIGURATION_PATH = "hibernate.properties";
    private static final String DB_PATH = "sql_script/init_database.sql";


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                hibernateProperties.load(HibernateUtil.class.getClassLoader().
                        getResourceAsStream(HIBERNATE_CONFIGURATION_PATH));
                registryBuilder.applySettings(hibernateProperties);
                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(model.Player.class);
                sources.addAnnotatedClass(model.Match.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                initDatabase(DB_PATH);

            } catch (Exception e) {
                shutdown();
                throw new DataBaseException(HIBERNATE_CONFIGURATION_ERROR);
            }
        }
        return sessionFactory;
    }


    private static void initDatabase(String db) {
        DataBaseInitalizer.execute(db);
    }

    private static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}