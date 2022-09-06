package com.practice.shop.services.impl;

import com.practice.shop.DAO.UserHasRoleRepository;
import com.practice.shop.DAO.UserRepository;
import com.practice.shop.DTO.UserDto;
import com.practice.shop.models.User;
import com.practice.shop.services.UserService;
import com.practice.shop.services.exception.user.UserNotFoundException;
import com.practice.shop.services.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserHasRoleRepository userHasRoleRepository;

    public void confirmEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        user.setEmailConfirm(true);
        userRepository.save(user);
    }

    public UserDto findByEmail(String email) {
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser == null) {
            throw new UserNotFoundException(email);
        } else {
            return UserMapper.INSTANCE.userToUserDto(foundUser, userHasRoleRepository);
        }
    }
}
