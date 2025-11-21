package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.repository.MatchRepository;;
import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PaginationResponseDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Match;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.MatchMapper;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository();
    private final MatchMapper matchMapper = MatchMapper.INSTANCE;
    private static final int DEFAULT_PAGE_SIZE = 5;

    public FinishedMatchesPersistenceService() {}

    public void save(MatchDTO match) {
        Match finishedMatch = matchMapper.toEntity(match);
        matchRepository.save(finishedMatch);
    }

    public PaginationResponseDTO getFinishedMatches(String playerName, Long currentPage){

        String formattedName = Player.formatName(playerName);

        int pageSize = DEFAULT_PAGE_SIZE;

        int offset = (int) (currentPage - 1) * DEFAULT_PAGE_SIZE;

        Long totalMatches = matchRepository.countFinishedMatches(formattedName);

        Long totalPages = (long) Math.ceil((double) totalMatches / DEFAULT_PAGE_SIZE);

        List<Match> matches =  matchRepository.findFinishedMatches(formattedName,  offset, pageSize);

        return new PaginationResponseDTO(matches, totalPages);
    }
}