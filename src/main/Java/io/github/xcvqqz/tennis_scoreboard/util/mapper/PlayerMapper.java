package io.github.xcvqqz.tennis_scoreboard.util.mapper;


import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {


    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(target = "id", ignore = true)                    // игнорируем id
    @Mapping(target = "matchesAsPlayerOne", ignore = true)    // игнорируем списки матчей
    @Mapping(target = "matchesAsPlayerTwo", ignore = true)
    @Mapping(target = "wonMatches", ignore = true)
    @Mapping(source = "name", target = "name")               // маппим только name
    Player toEntity(PlayerDTO playerDTO);

    // Маппинг из Entity в DTO
    @Mapping(target = "matchScoreDTO", ignore = true)        // игнорируем matchScoreDTO
    @Mapping(source = "name", target = "name")               // маппим только name
    PlayerDTO toDTO(Player player);

}
