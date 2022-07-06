package com.practice.shop.services.exception;

public class UserHasNoRolesException extends RuntimeException{
    public static final String MESSAGE = "User must have at least one role.";

    public UserHasNoRolesException() {
        super(MESSAGE);
    }
}
