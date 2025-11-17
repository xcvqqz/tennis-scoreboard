package io.github.xcvqqz.tennis_scoreboard.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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



}
