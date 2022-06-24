package com.practice.shop.services.exception.handler;

import com.practice.shop.services.exception.EntityAlreadyExistsException;
import com.practice.shop.services.exception.UserHasNoRolesException;
import com.practice.shop.services.exception.UserNotFoundException;
import com.practice.shop.services.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@RestControllerAdvice
    public class GlobalExceptionHandler {

    @ExceptionHandler({UserHasNoRolesException.class, EntityAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorTransfer handleCustomException(RuntimeException ex, WebRequest request) {
        return new ErrorTransfer(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorTransfer handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ErrorTransfer(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorTransfer handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorTransfer errors = new ErrorTransfer();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorTransfer handleWrongPasswordException(WrongPasswordException ex, WebRequest request) {
        return new ErrorTransfer(ex.getMessage());
    }



}
