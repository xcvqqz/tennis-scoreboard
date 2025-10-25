package dto;

public class PlayerDTO {

    private String name;
    private MatchScoreDTO matchScoreDTO;

    public PlayerDTO(String name) {
        this.name = name;
        this.matchScoreDTO = new MatchScoreDTO();
    }

    public PlayerDTO() {}

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
}

