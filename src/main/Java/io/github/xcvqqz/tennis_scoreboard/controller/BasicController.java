package io.github.xcvqqz.tennis_scoreboard.controller;

import io.github.xcvqqz.tennis_scoreboard.model.Player;
import io.github.xcvqqz.tennis_scoreboard.util.Validator;
import io.github.xcvqqz.tennis_scoreboard.util.mapper.PlayerMapper;
import jakarta.servlet.http.HttpServlet;
import io.github.xcvqqz.tennis_scoreboard.service.FinishedMatchesPersistenceService;
import io.github.xcvqqz.tennis_scoreboard.service.OngoingMatchesService;
import io.github.xcvqqz.tennis_scoreboard.service.PlayerService;
import io.github.xcvqqz.tennis_scoreboard.util.UUIDUtil;

public class BasicController extends HttpServlet {

    protected final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected final UUIDUtil uuidUtil = new UUIDUtil();
    protected final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    protected final PlayerService playerService = new PlayerService();
    protected final PlayerMapper playerMapper = PlayerMapper.INSTANCE;

}
