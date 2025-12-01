package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.domain_model.OngoingMatch;
import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.mapper.entity_mapper.MatchMapper;
import io.github.xcvqqz.tennis_scoreboard.mapper.model_mapper.OngoingMatchMapper;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
public class OngoingMatchesService {

    private static final Map<UUID, OngoingMatch> ongoingMatches = new HashMap<>();

    private OngoingMatchMapper ongoingMatchMapper = OngoingMatchMapper.INSTANCE;

    public void createNewOngoingMatch(UUID uuid, PlayerDTO playerOneDTO, PlayerDTO playerTwoDTO){
        OngoingMatch ongoingMatch = ongoingMatchMapper.toModel(createNewMatchDTO(playerOneDTO, playerTwoDTO));
        addNewOngoingMatches(uuid, ongoingMatch);
    }

    public void deleteOngoingMatch(OngoingMatch ongoingMatch){
        ongoingMatches.remove(ongoingMatch);
    }

    public OngoingMatch getOngoingMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    private MatchDTO createNewMatchDTO(PlayerDTO playerOneDTO, PlayerDTO playerTwoDTO){
        MatchDTO matchDTO = new MatchDTO(playerOneDTO, playerTwoDTO);
        return matchDTO;
    }

    private void addNewOngoingMatches(UUID uuid, OngoingMatch ongoingMatch) {
        ongoingMatches.put(uuid, ongoingMatch);
    }
}