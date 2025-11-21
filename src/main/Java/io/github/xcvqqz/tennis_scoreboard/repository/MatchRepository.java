package io.github.xcvqqz.tennis_scoreboard.repository;

import io.github.xcvqqz.tennis_scoreboard.model.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import io.github.xcvqqz.tennis_scoreboard.util.HibernateUtil;

import java.util.List;

public class MatchRepository {

    public void save(Match match){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public Long countFinishedMatches(String playerName) {

        StringBuilder sb = new StringBuilder("SELECT COUNT(m) FROM Match m");

        if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
            sb.append(" WHERE m.playerOne.name LIKE :playerName OR m.playerTwo.name LIKE :playerName");
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

            StringBuilder hql = new StringBuilder("FROM Match");

            if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
                hql.append(" m WHERE m.playerOne.name LIKE :playerName OR  m.playerTwo.name LIKE :playerName");
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