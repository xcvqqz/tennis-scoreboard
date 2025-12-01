package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.repository.PlayerRepository;
import io.github.xcvqqz.tennis_scoreboard.entity.Player;
import io.github.xcvqqz.tennis_scoreboard.mapper.entity_mapper.PlayerMapper;
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
       return playerRepository.findByName(name)
               .orElseGet(() -> {
                   Player newPlayer = Player.builder()
                           .name(name)
                           .build();

                  return playerRepository.save(newPlayer).get();
               });
   }
}