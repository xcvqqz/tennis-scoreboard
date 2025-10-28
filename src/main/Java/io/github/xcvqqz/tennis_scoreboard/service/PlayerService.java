package io.github.xcvqqz.tennis_scoreboard.service;


import io.github.xcvqqz.tennis_scoreboard.dao.PlayerDAO;
import io.github.xcvqqz.tennis_scoreboard.model.Player;

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