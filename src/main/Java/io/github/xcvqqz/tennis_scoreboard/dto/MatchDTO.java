package io.github.xcvqqz.tennis_scoreboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MatchDTO {

    private PlayerDTO playerOne;
    private PlayerDTO playerTwo;
    @Builder.Default
    private boolean matchOver =  false;
    @Builder.Default
    private PlayerDTO winner = null;
    @Builder.Default
    private boolean openTieBreak = false;
    @Builder.Default
    private boolean deuceSituation = false;

}