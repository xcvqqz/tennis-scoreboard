package io.github.xcvqqz.tennis_scoreboard.service;

import io.github.xcvqqz.tennis_scoreboard.domain_model.MatchScore;
import io.github.xcvqqz.tennis_scoreboard.domain_model.OngoingMatch;
import lombok.*;

@AllArgsConstructor
@Builder
public class MatchScoreCalculationService {

    private final OngoingMatch ongoingMatch;
    private static final int FORTY_SCORE_POINTS = 40;
    private static final int FIFTEEN_SCORE_POINTS = 15;
    private static final int THIRTY_SCORE_POINTS = 30;
    private static final int RESET_SCORE = 0;
    private static final int ZERO_POINTS = 0;
    private static final int BREAK_POINT_ADVANTAGE = 2;
    private static final int TIEBREAK_WINNER_POINTS = 7;
    private static final int INCREMENT_VALUE = 1;
    private static final int TIEBREAK_SITUATION_POINTS = 5;

    public void addPoint(MatchScore getsPlayerPoint) {

        MatchScore playerOneScore = ongoingMatch.getPlayerOneScore();
        MatchScore playerTwoScore = ongoingMatch.getPlayerTwoScore();


        boolean isPlayerOneScoring = getsPlayerPoint == playerOneScore;

        handlePoint(isPlayerOneScoring ? playerOneScore : playerTwoScore,
                    isPlayerOneScoring ? playerTwoScore : playerOneScore);

        if(isWinner(getsPlayerPoint)) {
            ongoingMatch.setWinner(isPlayerOneScoring ?
                    ongoingMatch.getPlayerOne() :
                    ongoingMatch.getPlayerTwo());
        }
    }

    private void handlePoint(MatchScore scoringSide, MatchScore opposingSide) {

        if(ongoingMatch.isOpenTieBreak() || isTieBreakSituation(scoringSide, opposingSide)) {
            handleTieBreakSituation(scoringSide, opposingSide);
            return;
        }

        if (scoringSide.getPoints() == FORTY_SCORE_POINTS) {
            if(isDeuceSituation(scoringSide, opposingSide)) {
                ongoingMatch.setDeuceSituation(true);
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

    private void winGame(MatchScore scoringSide, MatchScore opposingSide) {
        scoringSide.setPoints(RESET_SCORE);
        opposingSide.setPoints(RESET_SCORE);
        incrementGames(scoringSide);
    }


    private void checkSetWin(MatchScore scoringSide, MatchScore opposingSide){
        if(scoringSide.getGames() == 6){
            incrementSets(scoringSide);
            scoringSide.setGames(RESET_SCORE);
            opposingSide.setGames(RESET_SCORE);
        }
    }

    private void checkMatchWin(MatchScore scoringSide, MatchScore opposingSide){
        if(scoringSide.getSets() == 2){
            scoringSide.setSets(RESET_SCORE);
            opposingSide.setSets(RESET_SCORE);
            ongoingMatch.setMatchOver(true);
        }
    }

    private boolean isWinner(MatchScore pointWinner) {
       if(ongoingMatch.isMatchOver()){
           return true;
        } else {
           return false;
        }
    }

    private boolean isDeuceSituation(MatchScore scoringSide, MatchScore opposingSide){
        return (scoringSide.getPoints() == FORTY_SCORE_POINTS && opposingSide.getPoints() == FORTY_SCORE_POINTS);
    }

    private void handleDeuceSituation(MatchScore scoringSide, MatchScore opposingSide) {
        if(scoringSide.isAdvantage()) {
            resetAdvantage(scoringSide, opposingSide);
            winGame(scoringSide, opposingSide);
            ongoingMatch.setDeuceSituation(false);
            return;
        }
        if (opposingSide.isAdvantage()){
            resetAdvantage(scoringSide, opposingSide);
            return;
        }
        addAdvantage(scoringSide, opposingSide);
    }

    private void addAdvantage(MatchScore scoringSide, MatchScore opposingSide) {
            scoringSide.setAdvantage(true);
            opposingSide.setAdvantage(false);
    }

    private void resetAdvantage(MatchScore scoringSide, MatchScore opposingSide){
            scoringSide.setAdvantage(false);
            opposingSide.setAdvantage(false);
    }

    private boolean isTieBreakSituation(MatchScore scoringSide, MatchScore opposingSide) {
        return scoringSide.getGames() == TIEBREAK_SITUATION_POINTS && opposingSide.getGames() == TIEBREAK_SITUATION_POINTS;
    }

    private void handleTieBreakSituation(MatchScore scoringSide, MatchScore opposingSide){
        if(!ongoingMatch.isOpenTieBreak()){
            ongoingMatch.setOpenTieBreak(true);
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
                scoringSide.setTieBreakPoints(RESET_SCORE);
                opposingSide.setTieBreakPoints(RESET_SCORE);
                ongoingMatch.setOpenTieBreak(false);
                checkMatchWin(scoringSide,opposingSide);}
    }

    private boolean hasTwoPointLead(MatchScore scoringSide, MatchScore opposingSide) {
        return Math.abs(scoringSide.getTieBreakPoints() - opposingSide.getTieBreakPoints()) >= BREAK_POINT_ADVANTAGE;}

        private void incrementPoints (MatchScore scoringSide){
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

    private void incrementGames(MatchScore scoringSide){
        scoringSide.setGames(scoringSide.getGames() + INCREMENT_VALUE);
    }

    private void incrementSets (MatchScore scoringSide){
            scoringSide.setSets(scoringSide.getSets() + INCREMENT_VALUE);
    }

    private void incrementTieBreakPoints (MatchScore scoringSide){
        scoringSide.setTieBreakPoints(scoringSide.getTieBreakPoints() + INCREMENT_VALUE);
    }

}