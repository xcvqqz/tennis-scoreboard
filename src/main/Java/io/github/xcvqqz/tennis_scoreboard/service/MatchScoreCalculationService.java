package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.MatchScoreDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;

public class MatchScoreCalculationService {

    private final MatchDTO matchDTO;
    private static final int FORTY_SCORE_POINTS = 40;
    private static final int FIFTEEN_SCORE_POINTS = 15;
    private static final int THIRTY_SCORE_POINTS = 30;
    private static final int RESET_SCORE = 0;
    private static final int ZERO_POINTS = 0;
    private static final int BREAK_POINT_ADVANTAGE = 2;
    private static final int TIEBREAK_WINNER_POINTS = 7;
    private static final int INCREMENT_VALUE = 1;
    private static final int TIEBREAK_SITUATION_POINTS = 6;

    public MatchScoreCalculationService(MatchDTO matchDTO) {
        this.matchDTO = matchDTO;
    }

    public void addPoint(PlayerDTO getsPlayerPoint) {

        MatchScoreDTO playerOneScore = matchDTO.getPlayerOne().getMatchScoreDTO();
        MatchScoreDTO playerTwoScore = matchDTO.getPlayerTwo().getMatchScoreDTO();

        boolean isPlayerOneScoring = getsPlayerPoint.equals(matchDTO.getPlayerOne());

        handlePoint(isPlayerOneScoring ? playerOneScore : playerTwoScore,
                    isPlayerOneScoring ? playerTwoScore : playerOneScore);

        isWinner(getsPlayerPoint);
    }

    private void handlePoint(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {

        if(matchDTO.isOpenTieBreak() || isTieBreakSituation(scoringSide, opposingSide)) {
            handleTieBreakSituation(scoringSide, opposingSide);
            return;
        }

        if (scoringSide.getPoints() == FORTY_SCORE_POINTS) {
            if(isDeuceSituation(scoringSide, opposingSide)) {
                handleDeuceSituation(scoringSide, opposingSide);
                return;
            }

            winGame(scoringSide, opposingSide);
        } else {
            incrementPoints(scoringSide);
        }

            checkSetWin(scoringSide, opposingSide);
            checkMatchWin(scoringSide, opposingSide);
    }

    private void winGame(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        scoringSide.setPoints(RESET_SCORE);
        opposingSide.setPoints(RESET_SCORE);
        incrementGames(scoringSide);
    }


    private void checkSetWin(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
        if(scoringSide.getGames() == 7){
            incrementSets(scoringSide);
            scoringSide.setGames(RESET_SCORE);
            opposingSide.setGames(RESET_SCORE);
        }
    }

    private void checkMatchWin(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
        if(scoringSide.getSets() == 2){
            scoringSide.setSets(RESET_SCORE);
            opposingSide.setSets(RESET_SCORE);
            matchDTO.setMatchOver(true);
        }
    }

    private boolean isWinner(PlayerDTO pointWinner) {
       if(matchDTO.isMatchOver()){
           matchDTO.setWinner(pointWinner);
           return true;
        } else {
           return false;
        }
    }

    private boolean isDeuceSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
        return (scoringSide.getPoints() == FORTY_SCORE_POINTS && opposingSide.getPoints() == FORTY_SCORE_POINTS);
    }

    private void handleDeuceSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        if(scoringSide.isHasAdvantage()) {
            resetAdvantage(scoringSide, opposingSide);
            winGame(scoringSide, opposingSide);
            return;
        }
        if (opposingSide.isHasAdvantage()){
            resetAdvantage(scoringSide, opposingSide);
            return;
        }
        addAdvantage(scoringSide, opposingSide);
    }

    private void addAdvantage(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
            scoringSide.setHasAdvantage(true);
            opposingSide.setHasAdvantage(false);
    }

    private void resetAdvantage(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
            scoringSide.setHasAdvantage(false);
            opposingSide.setHasAdvantage(false);
    }

    private boolean isTieBreakSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        return scoringSide.getGames() == TIEBREAK_SITUATION_POINTS && opposingSide.getGames() == TIEBREAK_SITUATION_POINTS;
    }

    private void handleTieBreakSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
        if(!matchDTO.isOpenTieBreak()){
            matchDTO.setOpenTieBreak(true);
            incrementTieBreakPoints(scoringSide);
            return;
        }

        incrementTieBreakPoints(scoringSide);

        if(scoringSide.getTieBreakPoints() >= TIEBREAK_WINNER_POINTS && hasTwoPointLead(scoringSide,opposingSide)) {
                incrementSets(scoringSide);
                scoringSide.setGames(RESET_SCORE);
                opposingSide.setGames(RESET_SCORE);
                scoringSide.setGames(RESET_SCORE);
                opposingSide.setGames(RESET_SCORE);
                matchDTO.setOpenTieBreak(false);
                checkMatchWin(scoringSide,opposingSide);}
    }

    private boolean hasTwoPointLead(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        return Math.abs(scoringSide.getTieBreakPoints() - opposingSide.getTieBreakPoints()) >= BREAK_POINT_ADVANTAGE;}

        private void incrementPoints (MatchScoreDTO scoringSide){
            switch (scoringSide.getPoints()) {
                case ZERO_POINTS:
                    scoringSide.setPoints(FIFTEEN_SCORE_POINTS);
                    break;
                case FIFTEEN_SCORE_POINTS:
                    scoringSide.setPoints(THIRTY_SCORE_POINTS);
                    break;
                case THIRTY_SCORE_POINTS:
                    scoringSide.setPoints(FORTY_SCORE_POINTS);
                    break;
            }
    }

    private void incrementGames(MatchScoreDTO scoringSide){
        scoringSide.setGames(scoringSide.getGames() + INCREMENT_VALUE);
    }

    private void incrementSets (MatchScoreDTO scoringSide){
            scoringSide.setSets(scoringSide.getSets() + INCREMENT_VALUE);
    }

    private void incrementTieBreakPoints (MatchScoreDTO scoringSide){
        scoringSide.setTieBreakPoints(scoringSide.getTieBreakPoints() + INCREMENT_VALUE);
    }
}