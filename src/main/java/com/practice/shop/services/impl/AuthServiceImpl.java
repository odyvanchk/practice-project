package com.practice.shop.services.impl;

import com.practice.shop.DAO.UserHasRoleRepository;
import com.practice.shop.DAO.UserRepository;
import com.practice.shop.DTO.UserDto;
import com.practice.shop.controllers.security.jwt.AccessTokenService;
import com.practice.shop.controllers.security.jwt.RefreshTokenService;
import com.practice.shop.models.User;
import com.practice.shop.models.UserActiveRole;
import com.practice.shop.models.UserRole;
import com.practice.shop.services.AuthService;
import com.practice.shop.services.exception.WrongPasswordException;
import com.practice.shop.services.exception.user.EmailIsNotConfirmedException;
import com.practice.shop.services.exception.user.EntityAlreadyExistsException;
import com.practice.shop.services.exception.user.UserHasNoRolesException;
import com.practice.shop.services.exception.user.UserNotFoundException;
import com.practice.shop.services.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserHasRoleRepository userHasRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final EmailServiceImpl emailService;

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
        User newUser = userRepository.save(UserMapper.INSTANCE.userDtoToUser(user));
        saveUserRoles(user, newUser.getId());

        UserMapper.INSTANCE.updateUserDto(user, newUser);
        return user;
    }

    @Override
    public Map<String, String> login(UserDto user) {
        User foundUser = userRepository.findUserByEmail(user.getEmail());
        if (foundUser == null) {
            throw new UserNotFoundException(user.getEmail());
        }
        if (!foundUser.isEmailConfirm()) {
            throw new EmailIsNotConfirmedException();
        }
        if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
           return getAccessRefreshTokens(user.getEmail(), user.getFingerprint(), foundUser.getId());
        }
        else {
            throw new WrongPasswordException(user.getEmail());
        }
    }


    @Override
    public Map<String, String> loginWithRefreshToken(String token, String fingerprint) {
        int userId = refreshTokenService.validateToken(token, fingerprint);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        return getAccessRefreshTokens(user.get().getEmail(), fingerprint, userId);
    }


    private void saveUserRoles(UserDto user, Integer userId){
        if (user.isStudent()) {
            userHasRoleRepository.save(new UserActiveRole(userId, UserRole.STUDENT.getRoleNumber()));
        }
        if (user.isTeacher()) {
            userHasRoleRepository.save(new UserActiveRole(userId, UserRole.TEACHER.getRoleNumber()));
        }
    }

    private Map<String, String> getAccessRefreshTokens(String email, String fingerprint, int userId) {
        Map<String, String> tokens = new HashMap<>(2);
        String accessToken = accessTokenService.generateAccessTokenByEmail(email);
        tokens.put("access", accessToken);
        tokens.put("accessExpTime", accessTokenService.getExpirationTime(accessToken).toString());
        tokens.put("refresh", refreshTokenService.generateAndSaveRefreshToken( fingerprint, userId).getToken());
        return tokens;
    }
}
