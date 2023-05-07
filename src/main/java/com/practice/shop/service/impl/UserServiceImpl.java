package com.practice.shop.service.impl;

import com.practice.shop.model.exception.EntityNotFoundException;
import com.practice.shop.model.user.User;
import com.practice.shop.repository.UserRepository;
import com.practice.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public void confirmEmail(String email) {
        User user = findByEmail(email);
        user.setEmailConfirm(true);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() ->
                        new EntityNotFoundException("User with email" + email + " doesn`t exist")
                );
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User doesn`t exist"));
    }

    @Override
    public User save(User userDtoToUser) {
        return userRepository.save(userDtoToUser);
    }

    @Override
    public boolean isExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
