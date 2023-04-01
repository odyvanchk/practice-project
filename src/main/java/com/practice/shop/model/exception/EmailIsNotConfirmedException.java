package com.practice.shop.model.exception;

public class EmailIsNotConfirmedException extends RuntimeException {

    public static final String MESSAGE = "Email is not confirmed. Please, check your email.";

    public EmailIsNotConfirmedException() {
        super(MESSAGE);
    }

}
