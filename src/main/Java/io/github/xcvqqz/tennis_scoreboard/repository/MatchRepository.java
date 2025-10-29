package io.github.xcvqqz.tennis_scoreboard.repository;

import io.github.xcvqqz.tennis_scoreboard.model.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import io.github.xcvqqz.tennis_scoreboard.util.HibernateUtil;

import java.util.List;

public class MatchRepository {

    public List<Match> findAll(){
        List<Match> allMatches;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            allMatches = session.createQuery("from Match").getResultList();
        }
        return allMatches;
    }

    public Match findById(int id){
        Match match;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            match = session.find(Match.class, id);
            session.getTransaction().commit();
        }
        return match;
    }

    public void save(Match match){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public void update(int idMatchToBeUpdated, Match updatedMatch){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            Match matchToBeUpdated = session.find(Match.class, idMatchToBeUpdated);

            matchToBeUpdated.setPlayerOne(updatedMatch.getPlayerOne());
            matchToBeUpdated.setPlayerTwo(updatedMatch.getPlayerTwo());
            matchToBeUpdated.setWinner(updatedMatch.getWinner());

            session.getTransaction().commit();
        }
    }



    //сколько всего матчей завершённых
    public Long countFinishedMatches(String playerName, int offset, int limit) {

        StringBuilder sb = new StringBuilder("SELECT COUNT(m) FROM Match m");

        if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
            sb.append(" WHERE m.playerOne.name LIKE :playerName OR m.playerTwo.name LIKE :playerName");
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(sb.toString(), Long.class);

            if (!playerName.trim().isEmpty() || !playerName.isBlank()) {
                query.setParameter("playerName", playerName);
            }
            return query.
                    setFirstResult(offset).
                    setMaxResults(limit).
                    getSingleResult();
        }
    }


        //найти список всех матчей

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






    public void delete(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Match matchToBeDeleted = session.find(Match.class, id);
            session.remove(matchToBeDeleted);
            session.getTransaction().commit();
        }
    }





}