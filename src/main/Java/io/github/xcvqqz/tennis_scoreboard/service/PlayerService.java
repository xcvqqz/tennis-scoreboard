package io.github.xcvqqz.tennis_scoreboard.service;


import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.repository.PlayerRepository;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.PlayerMapper;

import java.util.UUID;

public class PlayerService {

    private PlayerRepository playerRepository;
    private PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    public PlayerService(){
        this.playerRepository = new PlayerRepository();
    }


    public PlayerDTO createOrGetPlayerDTO(String name){
        Player player = createPlayerIfNotExists(name);
        return playerMapper.toDTO(player);
    }


   private Player createPlayerIfNotExists(String name) {
       return playerRepository.findByName(name)
               .orElseGet(() -> {
                   Player newPlayer = new Player(name);
                   playerRepository.save(newPlayer);
                   return newPlayer;
               });
   }








}