package com.practice.shop.services.exception;

public class WrongPasswordException extends RuntimeException{
    public static String message  = "User with email %s has another password";

    public WrongPasswordException(String email) {
        super(String.format(message, email));
    }
}
