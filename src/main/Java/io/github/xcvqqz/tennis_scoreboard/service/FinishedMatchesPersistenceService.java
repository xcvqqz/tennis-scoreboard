package io.github.xcvqqz.tennis_scoreboard.service;



//инкапсулирует чтение и запись законченных матчей в БД

import io.github.xcvqqz.tennis_scoreboard.repository.MatchRepository;
import io.github.xcvqqz.tennis_scoreboard.repository.PlayerRepository;
import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PaginationResponseDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Match;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.PlayerMapper;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository();
    private final PlayerRepository playerRepository = new PlayerRepository();

    private static final int DEFAULT_PAGE_SIZE = 10;

    public FinishedMatchesPersistenceService() {}

    public void save(MatchDTO match) {
        Player playerOne = PlayerMapper.INSTANCE.toEntity(match.getPlayerOne());
        Player playerTwo = PlayerMapper.INSTANCE.toEntity(match.getPlayerTwo());
        Player winner = PlayerMapper.INSTANCE.toEntity(match.getWinner());
        Match endedMatch = new Match(playerOne, playerTwo, winner);
        matchRepository.save(endedMatch);
    }


    public List<Match> findAllMatches(){
        return matchRepository.findAll();
    }



    // 1) Общее колличество матчей+ (totalMatches) +
    // 2) Лист всех матчей (List<Match> matches) +
    // 3) Общее колличество страниц (totalPages) +
    //

    public PaginationResponseDTO getFinishedMatches(String playerName, Long currentPage){

        //1. сколько в себя вмещает одна страница
        int pageSize = DEFAULT_PAGE_SIZE;

        // 4. Вычисляем сдвиг (offset) для SQL
        int offset = (int) (currentPage - 1) * DEFAULT_PAGE_SIZE;

        // 2. Получаем общее количество матчей по фильтру
        Long totalMatches = matchRepository.countFinishedMatches(playerName, offset, pageSize);

        // 3. Вычисляем общее количество страниц
        Long totalPages = (long) Math.ceil((double) totalMatches / DEFAULT_PAGE_SIZE);

        List<Match> matches =  matchRepository.findFinishedMatches(playerName,  offset, pageSize);

        return new PaginationResponseDTO(matches, totalPages);

    }



    

}
