package io.github.xcvqqz.tennis_scoreboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    private String name;
    @Builder.Default
    private MatchScoreDTO matchScoreDTO =  new MatchScoreDTO();

}