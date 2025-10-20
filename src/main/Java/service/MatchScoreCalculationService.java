package service;



//реализует логику подсчёта счёта матча по очкам/геймам/сетам


import dto.MatchDTO;
import dto.PlayerDTO;

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
        if (matchDTO.isMatchOver()) {
            matchDTO.setWinner(getsPlayerPoint);

            System.out.println("КОНЕЦ ИГРЫ. ПОБЕДИЛ ИГРОК " + getsPlayerPoint.getName());
            return;
        }

        PlayerDTO playerOne = matchDTO.getPlayerOne();
        PlayerDTO playerTwo = matchDTO.getPlayerTwo();

        boolean isPlayerOneScoring = getsPlayerPoint.equals(playerOne);

        handlePoint(isPlayerOneScoring ? playerOne : playerTwo,
                    isPlayerOneScoring ? playerTwo : playerOne);
    }


    private void handlePoint(PlayerDTO scoringPlayer, PlayerDTO opponent) {

        if(matchDTO.isOpenTieBreak() || isTieBreakSituation(scoringPlayer, opponent)) {
            handleTieBreakSituation(scoringPlayer, opponent);
            return;
        }

        if (scoringPlayer.getMatchScore() == FORTY_SCORE_POINTS) {
            if(isDeuceSituation(scoringPlayer, opponent)) {
                handleDeuceSituation(scoringPlayer, opponent);
                return;
            }

            winGame(scoringPlayer, opponent);
        } else {
            incrementMatchScore(scoringPlayer);
        }


            checkSetWin(scoringPlayer, opponent);
            checkMatchWin(scoringPlayer, opponent);
    }


    private void winGame(PlayerDTO winningPlayer, PlayerDTO losingPlayer) {
        winningPlayer.setMatchScore(RESET_SCORE);
        losingPlayer.setMatchScore(RESET_SCORE);
        incrementMatchGame(winningPlayer);
    }


    private void checkSetWin(PlayerDTO winningPlayer, PlayerDTO losingPlayer){
        if(winningPlayer.getMatchGame() == 7){
            incrementMatchSet(winningPlayer);
            winningPlayer.setMatchGame(RESET_SCORE);
            losingPlayer.setMatchGame(RESET_SCORE);
        }
    }


    private void checkMatchWin(PlayerDTO winningPlayer, PlayerDTO losingPlayer){
        if(winningPlayer.getMatchSet() == 2){
            winningPlayer.setMatchSet(RESET_SCORE);
            losingPlayer.setMatchSet(RESET_SCORE);
            matchDTO.setMatchOver(true);
            matchDTO.setWinner(winningPlayer);
        }
    }

    private boolean isDeuceSituation(PlayerDTO scoringPlayer, PlayerDTO opponent){
        return (scoringPlayer.getMatchScore() == FORTY_SCORE_POINTS && opponent.getMatchScore() == FORTY_SCORE_POINTS);
    }

    private void handleDeuceSituation(PlayerDTO scoringPlayer, PlayerDTO opponent) {
        if(scoringPlayer.isAdvantage()) {
            resetAdvantage(scoringPlayer, opponent);
            winGame(scoringPlayer, opponent);
            return;
        }
        if (opponent.isAdvantage()){
            resetAdvantage(scoringPlayer, opponent);
            return;
        }
        addAdvantage(scoringPlayer, opponent);
    }


    private void addAdvantage(PlayerDTO scoringPlayer, PlayerDTO opponent) {
            scoringPlayer.setAdvantage(true);
            opponent.setAdvantage(false);
    }

    private void resetAdvantage(PlayerDTO scoringPlayer, PlayerDTO opponent){
            scoringPlayer.setAdvantage(false);
            opponent.setAdvantage(false);
    }

    private boolean isTieBreakSituation(PlayerDTO scoringPlayer, PlayerDTO opponent) {
        return scoringPlayer.getMatchGame() == TIEBREAK_SITUATION_POINTS && opponent.getMatchGame() == TIEBREAK_SITUATION_POINTS;
    }


    private void handleTieBreakSituation(PlayerDTO scoringPlayer, PlayerDTO opponent){

        if(!matchDTO.isOpenTieBreak()){
            matchDTO.setOpenTieBreak(true);
            incrementTieBreakPoint(scoringPlayer);
            return;
        }

        incrementTieBreakPoint(scoringPlayer);

        if(scoringPlayer.getTieBreakPoint() >= TIEBREAK_WINNER_POINTS && hasTwoPointLead(scoringPlayer, opponent)) {
                incrementMatchSet(scoringPlayer);
                scoringPlayer.setMatchGame(RESET_SCORE);
                opponent.setMatchGame(RESET_SCORE);
                scoringPlayer.setTieBreakPoint(RESET_SCORE);
                opponent.setTieBreakPoint(RESET_SCORE);
                matchDTO.setOpenTieBreak(false);
                checkMatchWin(scoringPlayer, opponent);
        }
    }



        private boolean hasTwoPointLead(PlayerDTO scoringPlayer, PlayerDTO opponent) {
         return Math.abs(scoringPlayer.getTieBreakPoint() - opponent.getTieBreakPoint()) >= BREAK_POINT_ADVANTAGE;
    }


        private void incrementMatchScore (PlayerDTO player){
            switch (player.getMatchScore()) {
                case ZERO_POINTS:
                    player.setMatchScore(FIFTEEN_SCORE_POINTS);
                    break;
                case FIFTEEN_SCORE_POINTS:
                    player.setMatchScore(THIRTY_SCORE_POINTS);
                    break;
                case THIRTY_SCORE_POINTS:
                    player.setMatchScore(FORTY_SCORE_POINTS);
                    break;
            }
        }


    private void incrementMatchGame (PlayerDTO player){
            player.setMatchGame(player.getMatchGame() + INCREMENT_VALUE);
        }

        private void incrementMatchSet (PlayerDTO player){
         player.setMatchSet(player.getMatchSet() + INCREMENT_VALUE);
        }

    private void incrementTieBreakPoint (PlayerDTO player){
        player.setTieBreakPoint(player.getTieBreakPoint() + INCREMENT_VALUE);
    }
}