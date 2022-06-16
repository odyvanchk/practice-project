package com.practice.shop.services.exception.handler;

import com.practice.shop.services.exception.EntityAlreadyExistsException;
import com.practice.shop.services.exception.UserHasNoRolesException;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex, WebRequest request) {
       Map<String, String> error = new HashMap<>();
       error.put("msg", ex.getMessage());
        return error;
    }

    @ExceptionHandler(UserHasNoRolesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleUserHasNoRolesException(UserHasNoRolesException ex, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("msg", ex.getMessage());
        return error;
    }
}
