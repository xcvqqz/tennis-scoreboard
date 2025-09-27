package test_hibernate;

import model.Player;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class TestMain {


        public static void main(String[] args) {

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
//
//
//
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
//                        Player player = session.find(Player.class, playerGromila.getId());
//                        player.setName("Gromila2");
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


                try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                        session.beginTransaction();

                        List<Player> playerList = session.createQuery("from Player where name = 'Max'").getResultList();
                        session.createQuery("update Player set name ='bot' WHERE LENGTH(name)=4").executeUpdate();

                        session.getTransaction().commit();


                } catch (Exception e) {
                        System.err.println("Error during testing:");
                        e.printStackTrace();}


}
}
