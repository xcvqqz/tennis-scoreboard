package util;

import exception.DataBaseException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DataBaseInitalizer {

    private static final String DB_ERROR = "Database Error";

    protected static void execute(String dataBasePath) {
        String sql;
            try {
                sql = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(
                        HibernateUtil.class.getClassLoader().getResource(dataBasePath)).toURI())));
            } catch (IOException | URISyntaxException e) {
                throw new DataBaseException(e.getMessage());
            }


        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            if(isDataBaseEmpty(session)){
                try {
                    session.beginTransaction();
                    session.createNativeQuery(sql, void.class).executeUpdate();
                    session.getTransaction().commit();
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    throw new DataBaseException(DB_ERROR);
                }
            }
        }
    }

    private static boolean isDataBaseEmpty(Session session) {
        Query<Long> query = session.createQuery("SELECT COUNT(p) FROM Player p", Long.class);
        Long count = query.uniqueResult();
        return count == 0;
    }
}
