package io.github.xcvqqz.tennis_scoreboard.util;

import io.github.xcvqqz.tennis_scoreboard.exception.BadRequestException;
import io.github.xcvqqz.tennis_scoreboard.exception.DuplicateNameException;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern SYMBOL_VALIDATION_PATTERN = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ]+$");
    private static final String MISSING_PARAMETER_MESSAGE = "Missing parameter - ";
    private static final String INVALID_NAME_LENGTH_MESSAGE = "The name must be at least 3 characters and no more than 30 characters long.";
    private static final String INVALID_FORMAT_NAME_MESSAGE = "The name must be in Russian or English only, without extra symbols";
    private static final String INVALID_START_PAGE_PARAMETER_MESSAGE = "Page numbering starts from number 1";
    private static final String INVALID_TOTAL_PAGE_PARAMETER_MESSAGE = "You have entered a page number that is out of range";
    private static final String DUPLICATE_NAME_MESSAGE = "Player 1's name matches Player 2's name. Player names must be unique";


    public Validator(){}


    public static void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new BadRequestException(MISSING_PARAMETER_MESSAGE + name);
        }

        if (name.length() < 2 || name.length() > 30) {
            throw new BadRequestException(INVALID_NAME_LENGTH_MESSAGE);
        }

        if (!SYMBOL_VALIDATION_PATTERN.matcher(name).matches()){
            throw new BadRequestException(INVALID_FORMAT_NAME_MESSAGE);
        }
    }



        public static void validate(Long page, String name, Long TotalPage) {

            if(page < 1){
                throw new BadRequestException(INVALID_START_PAGE_PARAMETER_MESSAGE);
            }
            if(page > TotalPage){
                throw new BadRequestException(INVALID_TOTAL_PAGE_PARAMETER_MESSAGE);
            }

            if(name.isBlank()){
                return;
            }

            validate(name);
    }


    public static void validateNamesUniqueness(String namePlayerOne, String namePlayerTwo) {
        if(namePlayerOne.equals(namePlayerTwo)) {
            throw new DuplicateNameException(DUPLICATE_NAME_MESSAGE);
        }
    }


}
