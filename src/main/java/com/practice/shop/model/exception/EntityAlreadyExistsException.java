package com.practice.shop.model.exception;


public class EntityAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "User with this email: %s already exist";

    public EntityAlreadyExistsException(String email) {
        super(String.format(MESSAGE, email));
    }
}
