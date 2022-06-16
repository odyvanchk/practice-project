package com.practice.shop.services.exception;


public class EntityAlreadyExistsException extends RuntimeException {
    public static String message = "Entity with this email: %s already exist.";

    public EntityAlreadyExistsException(String email) {
        super(String.format(message, email));
    }
}
