package io.github.xcvqqz.tennis_scoreboard.dto;

import io.github.xcvqqz.tennis_scoreboard.model.Match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDTO {
    private List<Match> matches;
    private Long totalPage;
}
