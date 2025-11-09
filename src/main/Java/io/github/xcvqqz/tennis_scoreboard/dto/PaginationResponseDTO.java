package io.github.xcvqqz.tennis_scoreboard.dto;

import io.github.xcvqqz.tennis_scoreboard.model.Match;

import java.util.List;

public class PaginationResponseDTO {

    private List<Match> matches;

    private Long totalPage;


    public PaginationResponseDTO(List<Match> matches, Long totalPage) {
        this.matches = matches;
        this.totalPage = totalPage;
    }


    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage= totalPage;
    }

}
