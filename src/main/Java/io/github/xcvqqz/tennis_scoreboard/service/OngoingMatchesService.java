package io.github.xcvqqz.tennis_scoreboard.service;



//хранит текущие матчи и позволяет их записывать/читать

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<UUID, MatchDTO> ongoingMatches = new HashMap<>();

    public OngoingMatchesService() {}


    public void addNewOngoingMatches(UUID uuid, MatchDTO matchDTO) {
        ongoingMatches.put(uuid, matchDTO);
    }

    public Map <UUID, MatchDTO> getOngoingMatches() {
        return ongoingMatches;
    }

    public void deleteOngoingMatch(MatchDTO match){
        ongoingMatches.remove(match);
    }

    public MatchDTO getOngoingMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }





}
