package dao;

import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDAO {


    public List<Player> findAll(){
    List<Player> allPlayers;
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
        allPlayers = session.createQuery("from Player").getResultList();
    }
      return allPlayers;
}

    public Player findById(int id){
    Player player;
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
           player = session.find(Player.class, id);
           session.getTransaction().commit();
    }
     return player;
}


    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Player player = session.createQuery("FROM Player WHERE name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
            return Optional.ofNullable(player);
        }
    }



    public void save(Player player){
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }














    public void update(int updatePlayer, Player updatedPlayer){
    try(Session session = HibernateUtil.getSessionFactory().openSession()){

        Player playerToBeUpdated = session.find(Player.class, updatePlayer);

        playerToBeUpdated.getMatchesAsPlayerTwo().clear();
        playerToBeUpdated.getMatchesAsPlayerOne().clear();
        playerToBeUpdated.getWonMatches().clear();

        playerToBeUpdated.setName(updatedPlayer.getName());
        playerToBeUpdated.setMatchesAsPlayerOne(updatedPlayer.getMatchesAsPlayerOne());
        playerToBeUpdated.setMatchesAsPlayerTwo(updatedPlayer.getMatchesAsPlayerTwo());
        playerToBeUpdated.setWonMatches(updatedPlayer.getWonMatches());

        session.getTransaction().commit();
        }
    }

    public void delete(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Player playerToBeDeleted = session.find(Player.class, id);
            session.remove(playerToBeDeleted);
            session.getTransaction().commit();
        }
    }

}