import io.github.xcvqqz.tennis_scoreboard.exception.BadRequestException;
import io.github.xcvqqz.tennis_scoreboard.exception.DuplicateNameException;
import io.github.xcvqqz.tennis_scoreboard.util.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    private static final String MISSING_NAME_PARAM = "";
    private static final String INVALID_NAME_LENGTH = "x";
    private static final String DUPLICATE_NAME_PARAM = "duplicateName";

    @Test
    public void shouldThrowBadRequestExceptionWhenPlayerNameIsMissing(){
        assertThrows(BadRequestException.class, () -> {
            Validator.validate(MISSING_NAME_PARAM);
        });
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenPlayerNameIncorrectLength(){
        assertThrows(BadRequestException.class, () -> {
            Validator.validate(INVALID_NAME_LENGTH);
        });
    }

    @Test
    public void shouldThrowExceptionForDuplicatePlayerNames(){
        assertThrows(DuplicateNameException.class, () -> {
            Validator.validateNamesUniqueness(DUPLICATE_NAME_PARAM, DUPLICATE_NAME_PARAM);
        });
    }
}
