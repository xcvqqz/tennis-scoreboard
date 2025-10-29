package io.github.xcvqqz.tennis_scoreboard.service;


import io.github.xcvqqz.tennis_scoreboard.repository.PlayerRepository;
import io.github.xcvqqz.tennis_scoreboard.model.Player;

public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(){
        this.playerRepository = new PlayerRepository();
    }


   public Player createPlayerIfNotExists(String name) {

       return playerRepository.findByName(name)
               .orElseGet(() -> {
                   Player newPlayer = new Player(name);
                   playerRepository.save(newPlayer);
                   return newPlayer;
               });
   }




}