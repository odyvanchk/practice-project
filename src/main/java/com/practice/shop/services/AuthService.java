package com.practice.shop.services;

import com.practice.shop.DTO.UserDto;

import java.util.Map;

public interface AuthService {

    UserDto registerUser(UserDto user);

    Map<String,String> login(UserDto user);

    Map<String, String> loginWithRefreshToken(String token, String fingerprint);
}
