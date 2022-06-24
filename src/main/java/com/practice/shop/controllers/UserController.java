package com.practice.shop.controllers;

import com.practice.shop.DTO.UserDto;
import com.practice.shop.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

}
