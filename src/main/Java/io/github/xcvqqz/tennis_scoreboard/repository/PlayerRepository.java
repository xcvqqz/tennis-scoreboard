package io.github.xcvqqz.tennis_scoreboard.repository;

import io.github.xcvqqz.tennis_scoreboard.entity.Player;
import org.hibernate.Session;
import io.github.xcvqqz.tennis_scoreboard.util.HibernateUtil;
import java.util.Optional;

public class PlayerRepository {

    private static final String FIND_PLAYER_BY_NAME_SQL_QUERY = "FROM Player WHERE name = :name";

    public Optional<Player> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Player player = session.createQuery(FIND_PLAYER_BY_NAME_SQL_QUERY, Player.class)
                    .setParameter("name", name)
                    .uniqueResult();
            return Optional.ofNullable(player);
        }
    }

    public Optional<Player> save(Player player){
    try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
            return Optional.ofNullable(player);
        }
    }
}