package service;



//реализует логику подсчёта счёта матча по очкам/геймам/сетам


import dto.MatchDTO;
import dto.PlayerDTO;

public class MatchScoreCalculationService {

    private final MatchDTO matchDTO;

    public MatchScoreCalculationService(MatchDTO matchDTO) {
        this.matchDTO = matchDTO;
    }


    public void addPoint(PlayerDTO getsPlayerPoint) {
        if (matchDTO.isMatchOver()) {
            System.out.println("КОНЕЦ ИГРЫ. ПОБЕДИЛ ИГРОК " + getsPlayerPoint.getName());
            return;
        }

        PlayerDTO player1 = matchDTO.getPlayerOne();
        PlayerDTO player2 = matchDTO.getPlayerTwo();

        boolean isPlayer1Scoring = getsPlayerPoint.equals(player1);

        handlePoint(isPlayer1Scoring ? player1 : player2,
                    isPlayer1Scoring ? player2 : player1);
    }


    private void handlePoint(PlayerDTO scoringPlayer, PlayerDTO opponent) {

        if (scoringPlayer.getMatchScore() == 40) {

            if(isDeuce(scoringPlayer, opponent)) {
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
        // Сбрасываем счет очков и преимущество
        winningPlayer.setMatchScore(0);
        losingPlayer.setMatchScore(0);

        // Увеличиваем счет геймов
        incrementMatchGame(winningPlayer);

    }


    private void checkSetWin(PlayerDTO winningPlayer, PlayerDTO losingPlayer){
        if(winningPlayer.getMatchGame() == 7){
            incrementMatchSet(winningPlayer);
            winningPlayer.setMatchGame(0);
            losingPlayer.setMatchGame(0);
        }
    }


    private void checkMatchWin(PlayerDTO winningPlayer, PlayerDTO losingPlayer){
        if(winningPlayer.getMatchSet() == 2){
            winningPlayer.setMatchSet(0);
            losingPlayer.setMatchSet(0);
            matchDTO.setMatchOver(true);
        }
    }

    private boolean isDeuce(PlayerDTO scoringPlayer, PlayerDTO opponent){
        return (scoringPlayer.getMatchScore() == 40 && opponent.getMatchScore() == 40);
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






        private void incrementMatchScore (PlayerDTO playerDTO){
            switch (playerDTO.getMatchScore()) {
                case 0:
                    playerDTO.setMatchScore(15);
                    break;
                case 15:
                    playerDTO.setMatchScore(30);
                    break;
                case 30:
                    playerDTO.setMatchScore(40);
                    break;
            }
        }

        private void incrementMatchGame (PlayerDTO playerDTO){
            playerDTO.setMatchGame(playerDTO.getMatchGame() + 1);
        }

        private void incrementMatchSet (PlayerDTO playerDTO){
         playerDTO.setMatchSet(playerDTO.getMatchSet() + 1);
        }
    }