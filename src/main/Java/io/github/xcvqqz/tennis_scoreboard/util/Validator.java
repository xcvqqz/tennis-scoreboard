package io.github.xcvqqz.tennis_scoreboard.util;

import io.github.xcvqqz.tennis_scoreboard.exception.BadRequestException;
import io.github.xcvqqz.tennis_scoreboard.exception.DuplicateNameException;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor
public class Validator {

    private static final Pattern SYMBOL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ]+$");
    private static final String MISSING_PARAMETER_MESSAGE = "Missing parameter - name";
    private static final String INVALID_NAME_LENGTH_MESSAGE = "The name must be at least 3 characters and no more than 30 characters long.";
    private static final String INVALID_FORMAT_NAME_MESSAGE = "The name must be in Russian or English only, without extra symbols";
    private static final String DUPLICATE_NAME_MESSAGE = "Player 1's name matches Player 2's name. Player names must be unique";

    public static void validate(String playerOneName, String playerTwoName) {
        if ((playerOneName == null || playerOneName.isBlank()) || (playerTwoName == null || playerTwoName.isBlank())) {
            throw new BadRequestException(MISSING_PARAMETER_MESSAGE);
        }

        if ((playerOneName.length() < 2 || playerOneName.length() > 30) || (playerTwoName.length() < 2 || playerTwoName.length() > 30)) {
            throw new BadRequestException(INVALID_NAME_LENGTH_MESSAGE);
        }

        if ((!SYMBOL_VALIDATION_PATTERN.matcher(playerOneName).matches()) || (!SYMBOL_VALIDATION_PATTERN.matcher(playerTwoName).matches())) {
            throw new BadRequestException(INVALID_FORMAT_NAME_MESSAGE);
        }

        if(playerOneName.equals(playerTwoName)) {
            throw new DuplicateNameException(DUPLICATE_NAME_MESSAGE);
        }
    }
}