package service;



//инкапсулирует чтение и запись законченных матчей в БД

import dao.MatchDAO;
import dao.PlayerDAO;
import dto.MatchDTO;
import model.Match;
import model.Player;
import util.mapper.PlayerMapper;

public class FinishedMatchesPersistenceService {

    private final MatchDAO matchDAO = new MatchDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    public FinishedMatchesPersistenceService() {}

    public void save(MatchDTO match) {
        Player playerOne = PlayerMapper.INSTANCE.toEntity(match.getPlayerOne());
        Player playerTwo = PlayerMapper.INSTANCE.toEntity(match.getPlayerTwo());
        Player winner = PlayerMapper.INSTANCE.toEntity(match.getWinner());
        Match endedMatch = new Match(playerOne, playerTwo, winner);
        matchDAO.save(endedMatch);
    }

}
