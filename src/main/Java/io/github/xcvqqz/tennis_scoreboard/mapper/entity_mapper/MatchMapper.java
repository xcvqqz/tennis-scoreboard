package io.github.xcvqqz.tennis_scoreboard.mapper.entity_mapper;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    Match toEntity(MatchDTO matchDTO);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    MatchDTO toDTO(Match match);

}