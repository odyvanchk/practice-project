package com.practice.shop.controllers;

import com.practice.shop.DTO.UserDto;
import com.practice.shop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
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
    public  String loginUser(@RequestBody UserDto user, HttpServletResponse response) throws JSONException {
        Map<String, String> tokens = userService.login(user);
        Cookie cookie = new Cookie("refresh", tokens.get("refresh"));
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 24 * 60 * 60);

        response.addCookie(cookie);
        return new JSONObject().put("token", tokens.get("accessToken")).toString();
    }


    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/lessons/book")
    public String test(){
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
