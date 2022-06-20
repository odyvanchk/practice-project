package com.practice.shop.services.exception.handler;

import com.practice.shop.services.exception.EntityAlreadyExistsException;
import com.practice.shop.services.exception.UserHasNoRolesException;
import com.practice.shop.services.exception.UserNotFoundedException;
import com.practice.shop.services.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@EnableWebSecurity
@RestControllerAdvice
    public class GlobalExceptionHandler {

    @ExceptionHandler({UserHasNoRolesException.class, EntityAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTransfer handleCustomException(RuntimeException ex, WebRequest request) {
        return new ErrorTransfer(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorTransfer handleUserNotFoundException(UserNotFoundedException ex, WebRequest request) {
        return new ErrorTransfer(ex.getMessage());
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorTransfer handleWrongPasswordException(WrongPasswordException ex, WebRequest request) {
        return new ErrorTransfer(ex.getMessage());
    }



}
