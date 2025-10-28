package io.github.xcvqqz.tennis_scoreboard.util.mapper;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Match;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    @Mapping(source = "winner", target = "winner")
    @Mapping(target = "matchOver", ignore = true)
    @Mapping(target = "openTieBreak", ignore = true)
    Match toEntity(MatchDTO matchDTO);


    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "winner", ignore = true)
    MatchDTO toDTO(Match match);

}
