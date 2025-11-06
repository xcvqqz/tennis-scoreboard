package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.model.Match;
import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.MatchMapper;

public class MatchService {

    private MatchMapper  matchMapper = MatchMapper.INSTANCE;


    public MatchDTO createNewMatchDTO(PlayerDTO playerOneDTO, PlayerDTO playerTwoDTO){
        MatchDTO matchDTO = matchMapper.toDTO(new Match());
        matchDTO.setPlayerOne(playerOneDTO);
        matchDTO.setPlayerTwo(playerTwoDTO);
        return matchDTO;
    }


}
