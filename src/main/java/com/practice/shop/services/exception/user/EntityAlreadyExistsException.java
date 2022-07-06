package com.practice.shop.services.exception.user;


public class EntityAlreadyExistsException extends RuntimeException {
    public static String message = "User with this email: %s already exist";

    public EntityAlreadyExistsException(String email) {
        super(String.format(message, email));
    }
}
