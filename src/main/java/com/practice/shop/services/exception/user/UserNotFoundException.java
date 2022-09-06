package com.practice.shop.services.exception.user;

public class UserNotFoundException extends RuntimeException {
    public static String message = "User with email %s doesn`t exist";

    public UserNotFoundException(String email) {
        super(String.format(message, email));
    }

    public UserNotFoundException() {
        super("User not found");
    }
}
