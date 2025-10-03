package dao;

import model.Match;
import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class MatchDAO {

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

    public void delete(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Match matchToBeDeleted = session.find(Match.class, id);
            session.remove(matchToBeDeleted);
            session.getTransaction().commit();
        }
    }





}