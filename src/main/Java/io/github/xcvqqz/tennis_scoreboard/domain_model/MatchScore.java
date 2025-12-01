package io.github.xcvqqz.tennis_scoreboard.domain_model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchScore {

    private int points;
    private int games;
    private int sets;
    private int tieBreakPoints;
    private boolean advantage;

}