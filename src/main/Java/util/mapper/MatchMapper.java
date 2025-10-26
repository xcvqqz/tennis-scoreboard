package util.mapper;

import dto.MatchDTO;
import dto.PlayerDTO;
import model.Match;
import model.Player;
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
