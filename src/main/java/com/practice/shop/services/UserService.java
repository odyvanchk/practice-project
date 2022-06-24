package com.practice.shop.services;

import com.practice.shop.DTO.UserDto;

import java.util.Map;

public interface UserService {

    UserDto registerUser(UserDto user);

    Map<String,String> login(UserDto user);

    public UserDto findByEmail(String email);
    }
