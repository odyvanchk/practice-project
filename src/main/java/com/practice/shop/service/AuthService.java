package com.practice.shop.service;

import com.practice.shop.model.user.AuthEntity;
import com.practice.shop.web.dto.UserDto;

public interface AuthService {

    UserDto registerUser(UserDto user);

    AuthEntity login(UserDto user);

    AuthEntity loginWithRefreshToken(String token, Long id);
}
