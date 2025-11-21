package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.repository.PlayerRepository;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.PlayerMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerService {

    @Builder.Default
    private PlayerRepository playerRepository = new PlayerRepository();
    private PlayerMapper playerMapper = PlayerMapper.INSTANCE;

    public PlayerDTO createOrGetPlayerDTO(String name){
        Player player = createPlayerIfNotExists(name);
        return playerMapper.toDTO(player);
    }

   private Player createPlayerIfNotExists(String name) {
        String formattedName = Player.formatName(name);
       return playerRepository.findByName(formattedName)
               .orElseGet(() -> {
                   Player newPlayer = Player.builder()
                           .name(formattedName)
                           .build();

                   playerRepository.save(newPlayer);
                   return newPlayer;
               });
   }
}