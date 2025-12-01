import io.github.xcvqqz.tennis_scoreboard.domain_model.OngoingMatch;
import io.github.xcvqqz.tennis_scoreboard.entity.Player;
import io.github.xcvqqz.tennis_scoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchScoreCalculationServiceTest {

    private OngoingMatch ongoingMatch;
    private MatchScoreCalculationService scoreCalculationService;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    public void setUp() {
        playerOne = Player.builder().build();
        playerTwo = Player.builder().build();

        ongoingMatch = OngoingMatch.builder().
                playerOne(playerOne).
                playerTwo(playerTwo).
                build();

        scoreCalculationService = MatchScoreCalculationService
                .builder()
                .ongoingMatch(ongoingMatch)
                .build();
    }

    @Test
    public void shouldContinueGameWhenPlayerScoresAtDeuce() {
        setPlayerScores(40, 40);

        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());
        assertFalse(isGameFinished());
    }


    @Test
    public void shouldWinGameWhenScoringAtFortyLove() {
        setPlayerScores(40, 0);

        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());
        assertTrue(isGameFinished());
    }

    @Test
    public void shouldStartTiebreakAtSixSix() {
        setPlayerGames(5, 5);

        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());
        assertTrue(ongoingMatch.isOpenTieBreak());
    }


    @Test
    public void shouldWinSetWhenWinningSeventhGame() {
        ongoingMatch.getPlayerOneScore().setGames(5);
        ongoingMatch.getPlayerOneScore().setPoints(40);
        ongoingMatch.getPlayerTwoScore().setGames(0);
        ongoingMatch.getPlayerTwoScore().setPoints(0);


        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());
        assertTrue(ongoingMatch.getPlayerOneScore().getSets() > 0);
    }


    @Test
    public void shouldEndMatchAfterWinningTwoSets() {

        ongoingMatch.getPlayerOneScore().setSets(1);
        ongoingMatch.getPlayerOneScore().setGames(5);
        ongoingMatch.getPlayerOneScore().setPoints(40);
        ongoingMatch.getPlayerTwoScore().setGames(0);
        ongoingMatch.getPlayerTwoScore().setGames(0);
        ongoingMatch.getPlayerTwoScore().setPoints(0);

        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());
        assertTrue(ongoingMatch.isMatchOver());
    }


    @Test
    public void shouldSetScoreToFifteenLoveWhenPlayerOneScoresFirstPoint() {
        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());

        assertAll(
                () -> assertEquals(15, ongoingMatch.getPlayerOneScore().getPoints()),
                () -> assertEquals(0, ongoingMatch.getPlayerTwoScore().getPoints()));

    }


    @Test
    public void shouldNotEndTiebreakWhenLeadIsOnlyOnePoint(){

        ongoingMatch.setOpenTieBreak(true);

        ongoingMatch.getPlayerOneScore().setTieBreakPoints(9);
        ongoingMatch.getPlayerTwoScore().setTieBreakPoints(9);

        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());

        assertTrue(ongoingMatch.isOpenTieBreak());
    }


    @Test
    public void shouldEndTiebreakWhenLeadIsTwoPoint(){

        ongoingMatch.setOpenTieBreak(true);
        setTieBreakPoints(10,9);

        scoreCalculationService.addPoint(ongoingMatch.getPlayerOneScore());

        assertFalse(ongoingMatch.isOpenTieBreak());
    }


    private void setPlayerScores(int playerOneScore, int playerTwoScore) {
        ongoingMatch.getPlayerOneScore().setPoints(playerOneScore);
        ongoingMatch.getPlayerTwoScore().setPoints(playerTwoScore);
    }

    private void setPlayerGames(int playerOneGames, int playerTwoGames) {
        ongoingMatch.getPlayerOneScore().setGames(playerOneGames);
        ongoingMatch.getPlayerTwoScore().setGames(playerTwoGames);
    }

    private void setTieBreakPoints(int playerOneTieBreakPoints, int playerTwoTieBreakPoints){
        ongoingMatch.getPlayerOneScore().setTieBreakPoints(playerOneTieBreakPoints);
        ongoingMatch.getPlayerTwoScore().setTieBreakPoints(playerTwoTieBreakPoints);
    }

    private boolean isGameFinished() {
        return ongoingMatch.getPlayerOneScore().getPoints() == 0 &&
                ongoingMatch.getPlayerTwoScore().getPoints() == 0;
    }
}