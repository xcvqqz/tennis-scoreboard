package io.github.xcvqqz.tennis_scoreboard.repository;

import io.github.xcvqqz.tennis_scoreboard.entity.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import io.github.xcvqqz.tennis_scoreboard.util.HibernateUtil;

import java.util.List;

public class MatchRepository {

    private static final String COUNT_MATCHES_SQL_QUERY = "SELECT COUNT(m) FROM Match";
    private static final String FIND_MATCHES_SQL_QUERY = "FROM Match";
    private static final String SQL_CONDITION = "SELECT COUNT(m) FROM Match m";

    public void save(Match match){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public Long countFinishedMatches(String playerName) {

        StringBuilder sb = new StringBuilder(COUNT_MATCHES_SQL_QUERY);

        if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
            sb.append(SQL_CONDITION);
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(sb.toString(), Long.class);

            if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
                query.setParameter("playerName", playerName);
            }

            return query.getResultStream().findFirst().orElse(0L);
        }
    }

        public List<Match> findFinishedMatches(String playerName, int offset, int limit){

            StringBuilder hql = new StringBuilder(FIND_MATCHES_SQL_QUERY);

            if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
                hql.append(SQL_CONDITION);
            }

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Query<Match> query = session.createQuery(hql.toString(), Match.class);

                if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
                    query.setParameter("playerName", playerName);
                }

                return query.
                        setFirstResult(offset).
                        setMaxResults(limit).
                        list();
            }
    }
}