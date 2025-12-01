package io.github.xcvqqz.tennis_scoreboard.domain_model;

import io.github.xcvqqz.tennis_scoreboard.entity.Player;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OngoingMatch {

    private Player playerOne;
    private Player playerTwo;
    private Player winner;

    @Builder.Default
    private MatchScore playerOneScore = new MatchScore();
    @Builder.Default
    private MatchScore playerTwoScore = new MatchScore();

    @Builder.Default
    private boolean openTieBreak = false;
    @Builder.Default
    private boolean deuceSituation = false;
    @Builder.Default
    private boolean matchOver =  false;

}