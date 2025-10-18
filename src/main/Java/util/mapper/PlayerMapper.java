package util.mapper;


import dto.PlayerDTO;
import model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {


    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);


    @Mapping(source = "name", target = "name")
    Player toEntity(PlayerDTO playerDTO);

    @Mapping(source = "name", target = "name")
    PlayerDTO toDTO(Player player);

}
