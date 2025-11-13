package io.github.xcvqqz.tennis_scoreboard.util.mapper;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    @Mapping(source = "winner", target = "winner")
    Match toEntity(MatchDTO matchDTO);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    @Mapping(target = "winner", ignore = true)
    MatchDTO toDTO(Match match);
}