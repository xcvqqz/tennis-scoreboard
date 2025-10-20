package test_hibernate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Match;
import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain {

        @PersistenceContext
        private EntityManager em;


        public static void main(String[] args) {




            /*получение всех Player в лист*/

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                session.beginTransaction();
//                List<Player> players = session.createQuery("from Player").list();
//                for (Player player : players) {
//                        if(player.getId() == 1){
//                                System.out.println(player.getName() + " " + player.getId());
//                        }
//                }
//                session.getTransaction().commit();
//
//
//        } catch (Exception e) {
//                System.err.println("Error during testing:");
//                e.printStackTrace();}
//------------------------------------------------------------------------------------------------------------------------


            /*создание и сохранение двух Player*/
//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                        session.beginTransaction();
//                        Player player1 = new Player("Gromila");
//                        Player player2 = new Player("Pooqer");
//                        session.persist(player1);
//                        session.persist(player2);
//                        session.getTransaction().commit();
//
//
//                } catch (Exception e) {
//                        System.err.println("Error during testing:");
//                        e.printStackTrace();}

//------------------------------------------------------------------------------------------------------------------------

                /*delete*/
//
//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                        session.beginTransaction();
//                        Player playerGromila = new Player("Gromila");
//                        Player playerPooqer = new Player("Pooqer");
//                        session.persist(playerGromila);
//                        session.persist(playerPooqer);
//
//                        Player player1 = session.find(Player.class, playerGromila);
//                        Player player2 = session.find(Player.class, playerPooqer);
//

//                        session.getTransaction().commit();
//
//
//                } catch (Exception e) {
//                        System.err.println("Error during testing:");
//                        e.printStackTrace();}
// ---------------------------------------------------------------------------------------------------------------------

                /*update*/

//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                        session.beginTransaction();
//                        Player playerGromila = new Player("Gromila");
//                        session.persist(playerGromila);
//
//                        Player player2 = session.find(Player.class, playerGromila.getId());
//                        player2.setName("Gromila2");
//                        System.out.println(player.getName());
//
//                        session.getTransaction().commit();
//
//
//                } catch (Exception e) {
//                        System.err.println("Error during testing:");
//                        e.printStackTrace();}
//
//------------------------------------------------------------------------------------------------------------------------

                /*HQL*/
//                в SQL работаем с таблицей, В HQL - с сущностями. (он не знает таблицы, только сущности)
//
//                SQL - SELECT * FROM players;
//                HQL - FROM Player WHERE name = 'Max'

//
//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                        session.beginTransaction();
//
//                        List<Player> playerList = session.createQuery("from Player where name = 'Max'").getResultList();
//                        session.createQuery("update Player set name ='bot' WHERE LENGTH(name)=4").executeUpdate();
//
//                        session.getTransaction().commit();
//
//
//                } catch (Exception e) {
//                        System.err.println("Error during testing:");
//                        e.printStackTrace();}
//--------------------------------------------------------------------------------------------------------------
//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                        session.beginTransaction();
//
//
//                        Match match = session.find(Match.class, 3l);
//                        Player player = match.getWinner();
//                        System.out.println(player);
//
//
//                        session.getTransaction().commit();
//
//                } catch (Exception e) {
//                        System.err.println("Error during testing:");
//                        e.printStackTrace();}
//----------------------------------------------------------------------------------------------------------------
//
//                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//                        session.beginTransaction();
//
//
//                        Match match = new Match();
//                     Player playerBobby = new Player();
//                     Player playerMargo = new Player();
//
//                     playerBobby.setName("Bobby");
//                     playerMargo.setName("Margo");
//
//                     match.setPlayerOne(playerBobby);
//                     match.setPlayerTwo(playerMargo);
//                     match.setWinner(playerMargo);
//
//                     playerBobby.setMatchesAsPlayerOne(new ArrayList<>(Collections.singleton(match)));
//                     playerMargo.setMatchesAsPlayerTwo(new ArrayList<>(Collections.singleton(match)));
//                     playerMargo.setWonMatches(new ArrayList<>(Collections.singleton(match)));
//
//
//                     for (Match m : playerBobby.getMatchesAsPlayerOne()){
//                             System.out.println("игрок номер 1 : " + m.getPlayerOne().getName());
//                     }
//
//                        for (Match m : playerMargo.getMatchesAsPlayerTwo()){
//                                System.out.println("игрок номер 2 : " + m.getPlayerTwo().getName());
//                        }
//
//                        for (Match m : playerMargo.getWonMatches()){
//                                System.out.println("ПОБЕДИТЕЛЬ : " + m.getWinner().getName());
//                        }
//
//                     session.getTransaction().commit();
//
//                } catch (Exception e) {
//                        System.err.println("Error during testing:");
//                        e.printStackTrace();}

//---------------------------------------------------------------------------------------------------------------

//CASCADE TEST

                try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                        session.beginTransaction();


                        Match match = new Match();
                        Player playerBobby = new Player();
                        Player playerMargo = new Player();

                        playerBobby.setName("Bobby");
                        playerMargo.setName("Margo");

                        match.setPlayerOne(playerBobby);
                        match.setPlayerTwo(playerMargo);
                        match.setWinner(playerMargo);

                        playerBobby.setMatchesAsPlayerOne(new ArrayList<>(Collections.singleton(match)));
                        playerMargo.setMatchesAsPlayerTwo(new ArrayList<>(Collections.singleton(match)));
                        playerMargo.setWonMatches(new ArrayList<>(Collections.singleton(match)));

                        session.persist(playerMargo);
                        session.persist(playerBobby);
                        //если стоит cascade = CascadeType.PERSIST , то session.persist(match) хибер сам вызовет



                    List<Player> playerList = session.createQuery("from Player").getResultList();
                    List<Match> matchList = session.createQuery("from Match ").getResultList();

                    for(Player player : playerList){
                        System.out.println(player);
                    }

                    for (Match m : matchList){
                        System.out.println(m);
                    }
                        session.getTransaction().commit();

                } catch (Exception e) {
                        System.err.println("Error during testing:");
                        e.printStackTrace();}
//-------------------------------------------------------------------------------------------------------------------------


}

}
