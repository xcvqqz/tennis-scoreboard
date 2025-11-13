package io.github.xcvqqz.tennis_scoreboard.dto;

public class MatchScoreDTO {
    private int points;
    private int games;
    private int sets;
    private int tieBreakPoints;
    private boolean hasAdvantage;

    public MatchScoreDTO(){
        this.points = 0;
        this.games = 0;
        this.sets = 0;
        this.tieBreakPoints = 0;
        this.hasAdvantage = false;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getTieBreakPoints() {
        return tieBreakPoints;
    }

    public void setTieBreakPoints(int tieBreakPoints) {
        this.tieBreakPoints = tieBreakPoints;
    }

    public boolean isHasAdvantage() {
        return hasAdvantage;
    }

    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }
}


