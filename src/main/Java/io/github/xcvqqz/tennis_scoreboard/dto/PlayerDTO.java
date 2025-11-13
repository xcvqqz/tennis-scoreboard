package io.github.xcvqqz.tennis_scoreboard.dto;

public class PlayerDTO {

    private String name;
    private MatchScoreDTO matchScoreDTO;
    private Long id;

    public PlayerDTO(String name) {
        this.id = id;
        this.name = name;
        this.matchScoreDTO = new MatchScoreDTO();
    }

    public PlayerDTO() {

        this.matchScoreDTO = new MatchScoreDTO();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public MatchScoreDTO getMatchScoreDTO() {
        return matchScoreDTO;
    }

    public void setMatchScoreDTO(MatchScoreDTO matchScoreDTO) {
        this.matchScoreDTO = matchScoreDTO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

