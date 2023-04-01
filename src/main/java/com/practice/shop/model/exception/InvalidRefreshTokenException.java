package com.practice.shop.model.exception;

public class InvalidRefreshTokenException extends RuntimeException {
    public static final String MESSAGE = "Refresh token is not valid";

    public InvalidRefreshTokenException() {
        super(MESSAGE);
    }

}
