package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.domain_model.OngoingMatch;
import io.github.xcvqqz.tennis_scoreboard.mapper.model_mapper.OngoingMatchMapper;
import io.github.xcvqqz.tennis_scoreboard.repository.MatchRepository;;
import io.github.xcvqqz.tennis_scoreboard.dto.PaginationResponseDTO;
import io.github.xcvqqz.tennis_scoreboard.entity.Match;
import io.github.xcvqqz.tennis_scoreboard.entity.Player;
import io.github.xcvqqz.tennis_scoreboard.mapper.entity_mapper.MatchMapper;

import java.util.List;

public class FinishedMatchesPersistenceService {

    private final MatchRepository matchRepository = new MatchRepository();
    private final MatchMapper matchMapper = MatchMapper.INSTANCE;
    private final OngoingMatchMapper  ongoingMatchMapper = OngoingMatchMapper.INSTANCE;
    private static final int DEFAULT_PAGE_SIZE = 5;

    public FinishedMatchesPersistenceService() {}

    public void save(OngoingMatch match) {
        Match finishedMatch = ongoingMatchMapper.toEntity(match);
        matchRepository.save(finishedMatch);
    }

    public PaginationResponseDTO getFinishedMatches(String playerName, Long currentPage){

        String formattedName = Player.normalizeName(playerName);

        int pageSize = DEFAULT_PAGE_SIZE;

        int offset = (int) (currentPage - 1) * DEFAULT_PAGE_SIZE;

        Long totalMatches = matchRepository.countAll(formattedName);

        Long totalPages = (long) Math.ceil((double) totalMatches / DEFAULT_PAGE_SIZE);

        List<Match> matches =  matchRepository.findAll(formattedName,  offset, pageSize);

        return new PaginationResponseDTO(matches, totalPages);
    }
}