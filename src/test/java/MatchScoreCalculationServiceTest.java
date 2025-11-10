

import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.service.MatchScoreCalculationService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class MatchScoreCalculationServiceTest {


    private final MatchDTO matchDTOTest = createNewTestMatch();
    private final MatchScoreCalculationService matchScoreCalculationServiceTest = new MatchScoreCalculationService(matchDTOTest);


    //1) Если игрок 1 выигрывает очко при счёте 40-40, гейм не заканчивается
    @Test
    public void shouldСontinueGameAfterFortyFortyGamePoints(){
        assertFalse(isGameOverAfterFortyForty(matchDTOTest));
    }

    //2) Если игрок 1 выигрывает очко при счёте 40-0, то он выигрывает и гейм
    @Test
    public void shouldWinGameWhenScoringAtFortyZero(){

    }

    @Test
    public void shouldSTartTiebreakAtSixSixScoreGame(){

    }



    private MatchDTO createNewTestMatch(){
        PlayerDTO playerOneTest = new PlayerDTO();
        PlayerDTO playerTwoTest = new PlayerDTO();
        return new MatchDTO(playerOneTest,playerTwoTest);
    }

    private void setFortyScoreForPlayer(PlayerDTO playerDTOTets){
        playerDTOTets.getMatchScoreDTO().setMatchScore(40);
    }

    private boolean isGameOverAfterFortyForty(MatchDTO matchDTO){
        setFortyScoreForPlayer(matchDTOTest.getPlayerOne());
        setFortyScoreForPlayer(matchDTOTest.getPlayerTwo());
        matchScoreCalculationServiceTest.addPoint(matchDTO.getPlayerOne());
        if(matchDTO.getPlayerOne().getMatchScoreDTO().getMatchScore() == 0){
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