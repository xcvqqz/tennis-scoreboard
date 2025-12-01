package io.github.xcvqqz.tennis_scoreboard.dto;

import io.github.xcvqqz.tennis_scoreboard.entity.Match;

import java.util.List;

public record PaginationResponseDTO (
    List<Match> matches,
    Long totalPage
) {}