package io.github.xcvqqz.tennis_scoreboard.repository;

import io.github.xcvqqz.tennis_scoreboard.model.Player;
import org.hibernate.Session;
import io.github.xcvqqz.tennis_scoreboard.util.HibernateUtil;
import java.util.Optional;

public class PlayerRepository {



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
}