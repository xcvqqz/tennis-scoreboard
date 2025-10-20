package service;



//хранит текущие матчи и позволяет их записывать/читать

import dto.MatchDTO;
import util.UUIDUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    private static final Map<UUID, MatchDTO> ongoingMatches = new HashMap<>();

    public OngoingMatchesService() {}


    public void addNewOngoingMatches(MatchDTO matchDTO, UUID uuid) {
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
