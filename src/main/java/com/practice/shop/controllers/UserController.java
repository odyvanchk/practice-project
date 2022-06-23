package com.practice.shop.controllers;

import com.practice.shop.DTO.UserDto;
import com.practice.shop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("permitAll()")
    @CrossOrigin
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth")
    public Map<String,String> loginUser(@RequestBody UserDto user) {
        HashMap<String, String> response = new HashMap<>();
        response.put("token", userService.login(user));
        return response;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/lessons/book")
    public String d(){
        return "hello, STUDENT";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
