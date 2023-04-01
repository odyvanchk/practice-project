package com.practice.shop.service;

import com.practice.shop.model.user.User;

public interface UserService {

    void confirmEmail(String email);

    User findByEmail(String email);

    User findById(Long userId);

    User save(User userDtoToUser);

    boolean isExistByEmail(String email);
}
