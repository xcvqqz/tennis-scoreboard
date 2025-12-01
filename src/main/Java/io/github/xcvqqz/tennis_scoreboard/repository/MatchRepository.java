package io.github.xcvqqz.tennis_scoreboard.repository;

import io.github.xcvqqz.tennis_scoreboard.entity.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import io.github.xcvqqz.tennis_scoreboard.util.HibernateUtil;

import java.util.List;

public class MatchRepository {

    private static final String COUNT_ALL_QUERY = "SELECT COUNT(m) FROM Match";
    private static final String SELECT_ALL_QUERY  = "FROM Match";
    private static final String NAME_FILTER_JPQL = "SELECT COUNT(m) FROM Match m";

    public void save(Match match){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public Long countAll(String playerName) {

        StringBuilder hql = new StringBuilder(COUNT_ALL_QUERY);

        if(hasFilterByName(playerName)){
            hql.append(NAME_FILTER_JPQL);
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(hql.toString(), Long.class);

            if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
                query.setParameter("playerName", playerName);
            }

            return query.getSingleResult();
        }
    }

        public List<Match> findAll(String playerName, int offset, int limit){

            StringBuilder hql = new StringBuilder(SELECT_ALL_QUERY);

            if(hasFilterByName(playerName)){
                hql.append(NAME_FILTER_JPQL);
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

    private boolean hasFilterByName(String playerName) {
        return playerName != null && !playerName.isBlank();
    }

}