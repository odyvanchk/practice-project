package com.practice.shop.services.exception;

public class UserNotFoundedException extends RuntimeException{
    public static String message  = "User with email %s doesn`t exist";

    public UserNotFoundedException(String email) {
        super(String.format(message, email));
    }
}
