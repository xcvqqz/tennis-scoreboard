package io.github.xcvqqz.tennis_scoreboard.dto;

public class MatchDTO {

    private PlayerDTO playerOne;
    private PlayerDTO playerTwo;
    private boolean matchOver;
    private PlayerDTO winner;
    private boolean openTieBreak;

    public MatchDTO(PlayerDTO playerOne, PlayerDTO playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.matchOver = false;
        this.openTieBreak = false;
    }

    public MatchDTO() {
        this.matchOver = false;
        this.openTieBreak = false;
    }

    public PlayerDTO getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(PlayerDTO playerOne) {
        this.playerOne = playerOne;
    }

    public PlayerDTO getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(PlayerDTO playerTwo) {
        this.playerTwo = playerTwo;
    }

    public PlayerDTO getWinner() {
        return winner;
    }

    public void setWinner(PlayerDTO winner) {
        this.winner = winner;
    }

    public boolean isMatchOver() {
        return matchOver;
    }

    public void setMatchOver(boolean matchOver) {
        this.matchOver = matchOver;
    }

    public boolean isOpenTieBreak() {return openTieBreak;}

    public void setOpenTieBreak(boolean openTieBreak) {this.openTieBreak = openTieBreak;}
}
