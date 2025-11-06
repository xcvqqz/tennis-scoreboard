package io.github.xcvqqz.tennis_scoreboard.service;



//реализует логику подсчёта счёта матча по очкам/геймам/сетам


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

        if (scoringSide.getMatchScore() == FORTY_SCORE_POINTS) {
            if(isDeuceSituation(scoringSide, opposingSide)) {
                handleDeuceSituation(scoringSide, opposingSide);
                return;
            }

            winGame(scoringSide, opposingSide);
        } else {
            incrementMatchScore(scoringSide);
        }


            checkSetWin(scoringSide, opposingSide);
            checkMatchWin(scoringSide, opposingSide);

    }


    private void winGame(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        scoringSide.setMatchScore(RESET_SCORE);
        opposingSide.setMatchScore(RESET_SCORE);
        incrementMatchGame(scoringSide);
    }


    private void checkSetWin(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
        if(scoringSide.getMatchGame() == 7){
            incrementMatchSet(scoringSide);
            scoringSide.setMatchGame(RESET_SCORE);
            opposingSide.setMatchGame(RESET_SCORE);
        }
    }


    private void checkMatchWin(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
        if(scoringSide.getMatchSet() == 2){
            scoringSide.setMatchSet(RESET_SCORE);
            opposingSide.setMatchSet(RESET_SCORE);
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
        return (scoringSide.getMatchScore() == FORTY_SCORE_POINTS && opposingSide.getMatchScore() == FORTY_SCORE_POINTS);
    }

    private void handleDeuceSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        if(scoringSide.isAdvantage()) {
            resetAdvantage(scoringSide, opposingSide);
            winGame(scoringSide, opposingSide);
            return;
        }
        if (opposingSide.isAdvantage()){
            resetAdvantage(scoringSide, opposingSide);
            return;
        }
        addAdvantage(scoringSide, opposingSide);
    }


    private void addAdvantage(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
            scoringSide.setAdvantage(true);
            opposingSide.setAdvantage(false);
    }

    private void resetAdvantage(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){
            scoringSide.setAdvantage(false);
            opposingSide.setAdvantage(false);
    }

    private boolean isTieBreakSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
        return scoringSide.getMatchGame() == TIEBREAK_SITUATION_POINTS && opposingSide.getMatchGame() == TIEBREAK_SITUATION_POINTS;
    }


    private void handleTieBreakSituation(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide){

        if(!matchDTO.isOpenTieBreak()){
            matchDTO.setOpenTieBreak(true);
            incrementTieBreakPoint(scoringSide);
            return;
        }

        incrementTieBreakPoint(scoringSide);

        if(scoringSide.getTieBreakPoint() >= TIEBREAK_WINNER_POINTS && hasTwoPointLead(scoringSide,opposingSide)) {
                incrementMatchSet(scoringSide);
                scoringSide.setMatchGame(RESET_SCORE);
                opposingSide.setMatchGame(RESET_SCORE);
                scoringSide.setTieBreakPoint(RESET_SCORE);
                opposingSide.setTieBreakPoint(RESET_SCORE);
                matchDTO.setOpenTieBreak(false);
                checkMatchWin(scoringSide,opposingSide);
        }
    }



        private boolean hasTwoPointLead(MatchScoreDTO scoringSide, MatchScoreDTO opposingSide) {
         return Math.abs(scoringSide.getTieBreakPoint() - opposingSide.getTieBreakPoint()) >= BREAK_POINT_ADVANTAGE;
    }


        private void incrementMatchScore (MatchScoreDTO scoringSide){
            switch (scoringSide.getMatchScore()) {
                case ZERO_POINTS:
                    scoringSide.setMatchScore(FIFTEEN_SCORE_POINTS);
                    break;
                case FIFTEEN_SCORE_POINTS:
                    scoringSide.setMatchScore(THIRTY_SCORE_POINTS);
                    break;
                case THIRTY_SCORE_POINTS:
                    scoringSide.setMatchScore(FORTY_SCORE_POINTS);
                    break;
            }
        }


    private void incrementMatchGame (MatchScoreDTO scoringSide){
        scoringSide.setMatchGame(scoringSide.getMatchGame() + INCREMENT_VALUE);
        }

        private void incrementMatchSet (MatchScoreDTO scoringSide){
            scoringSide.setMatchSet(scoringSide.getMatchSet() + INCREMENT_VALUE);
        }

    private void incrementTieBreakPoint (MatchScoreDTO scoringSide){
        scoringSide.setTieBreakPoint(scoringSide.getTieBreakPoint() + INCREMENT_VALUE);
    }
}