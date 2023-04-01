package com.practice.shop.service;

import com.practice.shop.web.dto.UserDto;

import java.util.Map;

public interface AuthService {

    UserDto registerUser(UserDto user);

    Map<String,String> login(UserDto user);

    Map<String, String> loginWithRefreshToken(String token, String fingerprint);
}
