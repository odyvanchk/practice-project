package com.practice.shop.services.exception;

public class InvalidRefreshTokenException extends RuntimeException {
    public static final String MESSAGE = "Refresh token is not valid";

    public InvalidRefreshTokenException() {
        super(MESSAGE);
    }

}
