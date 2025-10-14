package dto;

public class PlayerDTO {

    private String name;
    private int matchScore;           //15-30-40-
    private int matchGame;           //1-2-3-4-5-6-7
    private int matchSet;           //1-2(Best of 3)
    private int tieBreakPoint;
    private boolean advantage;


    public PlayerDTO(String name) {
        this.name = name;
        this.matchScore = 0;
        this.matchGame = 0;
        this.matchSet = 0;
        this.tieBreakPoint = 0;
        this.advantage = false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public int getMatchGame() {
        return matchGame;
    }

    public void setMatchGame(int matchGame) {
        this.matchGame = matchGame;
    }

    public int getMatchSet() {
        return matchSet;
    }

    public void setMatchSet(int matchSet) {
        this.matchSet = matchSet;
    }

    public boolean isAdvantage() {return advantage;}

    public void setAdvantage(boolean advantage) {
        this.advantage = advantage;}

    public int getTieBreakPoint() {
        return tieBreakPoint;
    }

    public void setTieBreakPoint(int tieBreakPoint) {
        this.tieBreakPoint = tieBreakPoint;
    }
}
