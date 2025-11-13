import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.service.MatchScoreCalculationService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchScoreCalculationServiceTest {


    private final MatchDTO matchDTOTest = createNewTestMatch();
    private final MatchScoreCalculationService matchScoreCalculationServiceTest = new MatchScoreCalculationService(matchDTOTest);


    //1) Если игрок 1 выигрывает очко при счёте 40-40, гейм не заканчивается
    @Test
    public void shouldСontinueGameAfterFortyFortyGamePoints() {
        assertFalse(isGameOverAfterFortyForty(matchDTOTest));
    }

    //2) Если игрок 1 выигрывает очко при счёте 40-0, то он выигрывает и гейм
    @Test
    public void shouldWinGameWhenScoringAtFortyZero() {
        assertTrue(isGameOverAfterFortyZeroMatchScorePoints(matchDTOTest));
    }

    //3) При счёте 6-6 начинается тайбрейк вместо обычного гейма
    @Test
    public void shouldSTartTiebreakAtSixSixScoreGame() {
        assertTrue(isTieBreakStartsAfterSixSixMatchGamePoints(matchDTOTest));
    }


    //4) если игрок 1 набирает 7 очков в гейме, то он выигрывает сет
    @Test
    public void shouldWinSetWhenMatchGameAtSevenZero() {
        assertTrue(isGameOverAfterSevenZeroMatchGamePoint(matchDTOTest));
    }


    //5) если игрок 1 набирает 2 очка в сете, то игра заканчивается
    @Test
    public void shouldMatchOverAfterTwoSetPoints() {
        assertTrue(isMatchOverAfterTwoSetPoint(matchDTOTest));
    }


    private MatchDTO createNewTestMatch() {
        PlayerDTO playerOneTest = new PlayerDTO();
        PlayerDTO playerTwoTest = new PlayerDTO();
        return new MatchDTO(playerOneTest, playerTwoTest);
    }

    private void setFortyScoreForPlayer(PlayerDTO playerDTOTets) {
        playerDTOTets.getMatchScoreDTO().setMatchScore(40);
    }

    private boolean isGameOverAfterFortyForty(MatchDTO matchDTO) {
        setFortyScoreForPlayer(matchDTOTest.getPlayerOne());
        setFortyScoreForPlayer(matchDTOTest.getPlayerTwo());
        matchScoreCalculationServiceTest.addPoint(matchDTO.getPlayerOne());
        if (matchDTO.getPlayerOne().getMatchScoreDTO().getMatchScore() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isGameOverAfterFortyZeroMatchScorePoints(MatchDTO matchDTO) {
        matchDTO.getPlayerOne().getMatchScoreDTO().setMatchScore(40);
        matchDTO.getPlayerTwo().getMatchScoreDTO().setMatchScore(0);
        matchScoreCalculationServiceTest.addPoint(matchDTO.getPlayerOne());
        if (matchDTO.getPlayerOne().getMatchScoreDTO().getMatchScore() == 0) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isTieBreakStartsAfterSixSixMatchGamePoints(MatchDTO matchDTO) {
        matchDTO.getPlayerOne().getMatchScoreDTO().setMatchGame(6);
        matchDTO.getPlayerTwo().getMatchScoreDTO().setMatchGame(6);
        matchScoreCalculationServiceTest.addPoint(matchDTO.getPlayerOne());
        if (matchDTO.isOpenTieBreak()) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isGameOverAfterSevenZeroMatchGamePoint(MatchDTO matchDTO) {
        matchDTOTest.getPlayerOne().getMatchScoreDTO().setMatchGame(6);
        matchDTOTest.getPlayerOne().getMatchScoreDTO().setMatchScore(40);
        matchScoreCalculationServiceTest.addPoint(matchDTO.getPlayerOne());
        if (matchDTO.getPlayerOne().getMatchScoreDTO().getMatchSet() == 1) {
            return true;
        } else {
            return false;
        }
    }



    private boolean isMatchOverAfterTwoSetPoint(MatchDTO matchDTO) {
        matchDTOTest.getPlayerOne().getMatchScoreDTO().setMatchScore(40);
        matchDTOTest.getPlayerOne().getMatchScoreDTO().setMatchGame(6);
        matchDTOTest.getPlayerOne().getMatchScoreDTO().setMatchSet(1);
        matchScoreCalculationServiceTest.addPoint(matchDTO.getPlayerOne());
        if (matchDTO.isMatchOver()) {
            return true;
        } else {
            return false;
        }
    }


}







//Покроем юнит тестами подсчёт очков в матче. Примеры кейсов:
//
//1) Если игрок 1 выигрывает очко при счёте 40-40, гейм не заканчивается
//
//3) При счёте 6-6 начинается тайбрейк вместо обычного гейма