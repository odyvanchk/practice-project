package com.practice.shop.services;

import com.practice.shop.DTO.UserDto;

public interface UserService {

    UserDto registerUser(UserDto user);

    UserDto login(UserDto user);

    public UserDto findByEmail(String email);
    }
