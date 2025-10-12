package service;



//реализует логику подсчёта счёта матча по очкам/геймам/сетам


import dto.MatchDTO;
import dto.PlayerDTO;

public class MatchScoreCalculationService {

    private MatchDTO matchDTO;

    MatchScoreCalculationService(MatchDTO matchDTO) {
        this.matchDTO = matchDTO;
    }


    public void addPoint(PlayerDTO playerAddedPoint) {
        if (matchDTO.isMatchOver()) {
            return;
        }

        PlayerDTO player1 = matchDTO.getPlayerOne();
        PlayerDTO player2 = matchDTO.getPlayerTwo();

        boolean isPlayer1Scoring = playerAddedPoint.equals(player1);

        handlePoint(isPlayer1Scoring ? player1 : player2,
                isPlayer1Scoring ? player2 : player1);
    }


    private void handlePoint(PlayerDTO scoringPlayer, PlayerDTO opponent) {

        if (scoringPlayer.getMatchScore() == 40) {
            winGame(scoringPlayer, opponent);
        } else {
            incrementMatchScore(scoringPlayer);
        }
    }


    private void winGame(PlayerDTO winningPlayer, PlayerDTO losingPlayer) {
        // Сбрасываем счет очков и преимущество
        winningPlayer.setMatchScore(0);
        losingPlayer.setMatchScore(0);


        // Увеличиваем счет геймов
        incrementMatchGame(winningPlayer);


        // Проверяем, не выиграл ли игрок сет
        checkSetWin(winningPlayer, losingPlayer);
    }


    private void checkSetWin(PlayerDTO winningPlayer, PlayerDTO losingPlayer){
        if(winningPlayer.getMatchGame() >= 7){
            incrementMatchSet(winningPlayer);
            winningPlayer.setMatchGame(0);
            losingPlayer.setMatchGame(0);
        }
        checkMatchWin(winningPlayer, losingPlayer);
    }


    private void checkMatchWin(PlayerDTO winningPlayer, PlayerDTO losingPlayer){
        if(winningPlayer.getMatchSet() >= 2){
            winningPlayer.setMatchSet(0);
            losingPlayer.setMatchSet(0);
            matchDTO.setMatchOver(true);
        }
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
            switch (playerDTO.getMatchGame()) {
                case 0:
                    playerDTO.setMatchGame(1);
                    break;
                case 1:
                    playerDTO.setMatchGame(2);
                    break;
                case 2:
                    playerDTO.setMatchGame(3);
                    break;
                case 3:
                    playerDTO.setMatchGame(4);
                    break;
                case 4:
                    playerDTO.setMatchGame(5);
                    break;
                case 5:
                    playerDTO.setMatchGame(6);
                    break;

            }
        }


        private void incrementMatchSet (PlayerDTO playerDTO){
            switch (playerDTO.getMatchSet()) {
                case 0:
                    playerDTO.setMatchSet(1);
                    break;
                case 1:
                    playerDTO.setMatchSet(2);
                    break;
            }
        }







    }







