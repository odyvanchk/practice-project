package com.practice.shop.services.impl;

import com.practice.shop.DAO.UserHasRoleRepository;
import com.practice.shop.DAO.UserRepository;
import com.practice.shop.DTO.UserDto;
import com.practice.shop.controllers.security.filter.JwtProvider;
import com.practice.shop.models.User;
import com.practice.shop.models.UserActiveRole;
import com.practice.shop.models.UserRole;
import com.practice.shop.services.UserService;
import com.practice.shop.services.exception.EntityAlreadyExistsException;
import com.practice.shop.services.exception.UserHasNoRolesException;
import com.practice.shop.services.exception.UserNotFoundException;
import com.practice.shop.services.exception.WrongPasswordException;
import com.practice.shop.services.mappers.UserDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserHasRoleRepository userHasRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDtoMapper userDtoMapper;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public UserDto registerUser(UserDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException(user.getEmail());
        }
        if (!user.isStudent() && !user.isTeacher()){
            throw new UserHasNoRolesException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(userDtoMapper.toEntity(user));
        saveUserRoles(user, newUser.getId());

        return userDtoMapper.toDTO(newUser);
    }

    @Override
    public String login(UserDto user) {
        User foundUser = userRepository.findUserByEmail(user.getEmail());
        if (foundUser == null){
            throw new UserNotFoundException(user.getEmail());
        }
        if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return jwtProvider.generateAccessToken(user.getEmail());
        }
        else {
            throw new WrongPasswordException(user.getEmail());
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        User foundUser = userRepository.findUserByEmail(email);
        if (foundUser == null){
            throw new UserNotFoundException(email);
        }else {
            return userDtoMapper.toDTO(foundUser);
        }
    }


    private void saveUserRoles(UserDto user, Integer userId){
        if (user.isStudent()) {
            userHasRoleRepository.save(new UserActiveRole(userId, UserRole.STUDENT.getRoleNumber()));
        }
        if (user.isTeacher()) {
            userHasRoleRepository.save(new UserActiveRole(userId, UserRole.TEACHER.getRoleNumber()));
        }
    }
}
