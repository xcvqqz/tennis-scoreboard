package service;



//хранит текущие матчи и позволяет их записывать/читать

import dao.MatchDAO;
import dto.MatchDTO;
import model.Match;
import util.UUIDUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {


    private final UUIDUtil uuidUtil = new UUIDUtil();
    private static final Map<UUID, MatchDTO> ongoingMatches = new HashMap<>();

    public OngoingMatchesService() {}



    public void addNewMatch(MatchDTO matchDTO) {
        ongoingMatches.put(uuidUtil.getNewUUID(), matchDTO);
    }

    public Map <UUID, MatchDTO> getOngoingMatches() {
        return ongoingMatches;
    }


    public MatchDTO getOngoingMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }





}
