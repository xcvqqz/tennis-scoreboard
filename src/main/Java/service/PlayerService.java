package service;


import dao.PlayerDAO;
import model.Player;

public class PlayerService {
    private PlayerDAO playerDAO;

    public PlayerService(){
        this.playerDAO = new PlayerDAO();
    }


   public Player createPlayerIfNotExists(String name) {
       return playerDAO.findByName(name)
               .orElseGet(() -> {
                   Player newPlayer = new Player(name);
                   playerDAO.save(newPlayer);
                   return newPlayer;
               });
   }
}