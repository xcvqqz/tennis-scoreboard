package io.github.xcvqqz.tennis_scoreboard.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}