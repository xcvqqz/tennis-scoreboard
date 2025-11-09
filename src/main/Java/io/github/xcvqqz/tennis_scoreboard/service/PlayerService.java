package io.github.xcvqqz.tennis_scoreboard.service;


import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.repository.PlayerRepository;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.PlayerMapper;


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
       String formattedName = formatName(name);
       return playerRepository.findByName(formattedName)
               .orElseGet(() -> {
                   Player newPlayer = new Player(formattedName);
                   playerRepository.save(newPlayer);
                   return newPlayer;
               });
   }

   private String formatName(String name){
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase().trim();
    }
}