import io.github.xcvqqz.tennis_scoreboard.dto.MatchDTO;
import io.github.xcvqqz.tennis_scoreboard.dto.PlayerDTO;
import io.github.xcvqqz.tennis_scoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {

    private MatchDTO match;
    private MatchScoreCalculationService scoreCalculationService;
    private PlayerDTO playerOne;
    private PlayerDTO playerTwo;

    @BeforeEach
    public void setUp() {
        playerOne = PlayerDTO.builder().build();
        playerTwo = PlayerDTO.builder().build();

        match = MatchDTO.builder().
                playerOne(playerOne).
                playerTwo(playerTwo).
                build();

        scoreCalculationService = MatchScoreCalculationService
                .builder()
                .matchDTO(match)
                .build();

    }

    @Test
    public void shouldContinueGameWhenPlayerScoresAtDeuce() {
        setPlayerScores(40, 40);

        scoreCalculationService.addPoint(playerOne);
        assertFalse(isGameFinished());
    }


    @Test
    public void shouldWinGameWhenScoringAtFortyLove() {
        setPlayerScores(40, 0);

        scoreCalculationService.addPoint(playerOne);
        assertTrue(isGameFinished());
    }

    @Test
    public void shouldStartTiebreakAtSixSix() {
        setPlayerGames(6, 6);

        scoreCalculationService.addPoint(playerOne);
        assertTrue(match.isOpenTieBreak());
    }


    @Test
    public void shouldWinSetWhenWinningSeventhGame() {
        playerOne.getMatchScoreDTO().setGames(6);
        playerOne.getMatchScoreDTO().setPoints(40);
        playerTwo.getMatchScoreDTO().setGames(0);
        playerTwo.getMatchScoreDTO().setPoints(0);


        scoreCalculationService.addPoint(playerOne);
        assertTrue(playerOne.getMatchScoreDTO().getSets() > 0);
    }


    @Test
    public void shouldEndMatchAfterWinningTwoSets() {

        playerOne.getMatchScoreDTO().setSets(1);
        playerOne.getMatchScoreDTO().setGames(6);
        playerOne.getMatchScoreDTO().setPoints(40);
        playerTwo.getMatchScoreDTO().setGames(0);
        playerTwo.getMatchScoreDTO().setGames(0);
        playerTwo.getMatchScoreDTO().setPoints(0);

        scoreCalculationService.addPoint(playerOne);
        assertTrue(match.isMatchOver());
    }


    @Test
    public void shouldSetScoreToFifteenLoveWhenPlayerOneScoresFirstPoint() {
        scoreCalculationService.addPoint(playerOne);

        assertAll(
                () -> assertEquals(15, playerOne.getMatchScoreDTO().getPoints()),
                () -> assertEquals(0, playerTwo.getMatchScoreDTO().getPoints()));

    }


    @Test
    public void shouldNotEndTiebreakWhenLeadIsOnlyOnePoint(){

        match.setOpenTieBreak(true);

        playerOne.getMatchScoreDTO().setTieBreakPoints(9);
        playerTwo.getMatchScoreDTO().setTieBreakPoints(9);

        scoreCalculationService.addPoint(playerOne);

        assertTrue(match.isOpenTieBreak());
    }


    @Test
    public void shouldEndTiebreakWhenLeadIsTwoPoint(){

        match.setOpenTieBreak(true);
        setTieBreakPoints(10,9);

        scoreCalculationService.addPoint(playerOne);

        assertFalse(match.isOpenTieBreak());
    }


    private void setPlayerScores(int playerOneScore, int playerTwoScore) {
        playerOne.getMatchScoreDTO().setPoints(playerOneScore);
        playerTwo.getMatchScoreDTO().setPoints(playerTwoScore);
    }

    private void setPlayerGames(int playerOneGames, int playerTwoGames) {
        playerOne.getMatchScoreDTO().setGames(playerOneGames);
        playerTwo.getMatchScoreDTO().setGames(playerTwoGames);
    }

    private void setTieBreakPoints(int playerOneTieBreakPoints, int playerTwoTieBreakPoints){
        playerOne.getMatchScoreDTO().setTieBreakPoints(playerOneTieBreakPoints);
        playerTwo.getMatchScoreDTO().setTieBreakPoints(playerTwoTieBreakPoints);
    }

    private boolean isGameFinished() {
        return playerOne.getMatchScoreDTO().getPoints() == 0 &&
                playerTwo.getMatchScoreDTO().getPoints() == 0;
    }
}