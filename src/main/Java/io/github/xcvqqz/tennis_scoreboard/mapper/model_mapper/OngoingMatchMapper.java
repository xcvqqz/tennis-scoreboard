package io.github.xcvqqz.tennis_scoreboard.mapper.model_mapper;



import io.github.xcvqqz.tennis_scoreboard.domain_model.MatchScore;
import io.github.xcvqqz.tennis_scoreboard.domain_model.OngoingMatch;
import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.entity.Match;
import io.github.xcvqqz.tennis_scoreboard.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OngoingMatchMapper {

    OngoingMatchMapper INSTANCE = Mappers.getMapper(OngoingMatchMapper.class);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    OngoingMatch toModel(MatchDTO matchDTO);

    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    @Mapping(source = "winner", target = "winner")
    Match toEntity(OngoingMatch ongoingMatch);
}