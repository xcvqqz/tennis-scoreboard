package io.github.xcvqqz.tennis_scoreboard.util.mapper;


import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {


    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Player toEntity(PlayerDTO playerDTO);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    PlayerDTO toDTO(Player player);

}
