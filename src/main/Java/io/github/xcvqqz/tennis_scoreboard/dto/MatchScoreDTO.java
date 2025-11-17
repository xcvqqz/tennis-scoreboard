package io.github.xcvqqz.tennis_scoreboard.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchScoreDTO {
    @Builder.Default
    private int points = 0;
    @Builder.Default
    private int games = 0;
    @Builder.Default
    private int sets = 0;
    @Builder.Default
    private int tieBreakPoints = 0;
    @Builder.Default
    private boolean hasAdvantage = false;
}


