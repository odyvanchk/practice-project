package com.practice.shop.services;

import com.practice.shop.DTO.UserDto;

public interface UserService {

    void confirmEmail(String email);

    UserDto findByEmail(String email);
}
