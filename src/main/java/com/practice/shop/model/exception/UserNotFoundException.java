package com.practice.shop.model.exception;

public class UserNotFoundException extends RuntimeException {
    public static final String MESSAGE = "User with email %s doesn`t exist";

    public UserNotFoundException(String email) {
        super(String.format(MESSAGE, email));
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
