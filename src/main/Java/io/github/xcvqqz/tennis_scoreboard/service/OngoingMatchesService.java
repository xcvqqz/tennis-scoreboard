package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Match;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.MatchMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<UUID, MatchDTO> ongoingMatches = new HashMap<>();
    private MatchMapper matchMapper = MatchMapper.INSTANCE;

    public OngoingMatchesService(){}

    public void createNewOngoingMatch(UUID uuid, PlayerDTO playerOneDTO, PlayerDTO playerTwoDTO){
        MatchDTO matchDTO = createNewMatchDTO(playerOneDTO, playerTwoDTO);
        addNewOngoingMatches(uuid, matchDTO);
    }

    public void deleteOngoingMatch(MatchDTO match){
        ongoingMatches.remove(match);
    }

    public MatchDTO getOngoingMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    private MatchDTO createNewMatchDTO(PlayerDTO playerOneDTO, PlayerDTO playerTwoDTO){
        MatchDTO matchDTO = matchMapper.toDTO(new Match());
        matchDTO.setPlayerOne(playerOneDTO);
        matchDTO.setPlayerTwo(playerTwoDTO);
        return matchDTO;
    }

    private void addNewOngoingMatches(UUID uuid, MatchDTO matchDTO) {
        ongoingMatches.put(uuid, matchDTO);
    }
}