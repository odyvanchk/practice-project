package com.practice.shop.service.impl;

import com.practice.shop.model.exception.EmailIsNotConfirmedException;
import com.practice.shop.model.exception.EntityAlreadyExistsException;
import com.practice.shop.model.exception.UserHasNoRolesException;
import com.practice.shop.model.exception.WrongPasswordException;
import com.practice.shop.model.user.AuthEntity;
import com.practice.shop.model.user.User;
import com.practice.shop.model.user.UserActiveRole;
import com.practice.shop.model.user.UserRole;
import com.practice.shop.repository.UserHasRoleRepository;
import com.practice.shop.repository.UserRoleRepository;
import com.practice.shop.service.AuthService;
import com.practice.shop.service.UserService;
import com.practice.shop.web.controller.security.jwt.AccessTokenService;
import com.practice.shop.web.controller.security.jwt.RefreshTokenService;
import com.practice.shop.web.dto.UserDto;
import com.practice.shop.web.mappers.UserMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserHasRoleRepository userHasRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final UserMapper userMapper;
    List<UserRole> userRoles;

    public AuthServiceImpl(UserService userService, UserHasRoleRepository userHasRoleRepository, PasswordEncoder passwordEncoder, AccessTokenService accessTokenService, RefreshTokenService refreshTokenService, UserRoleRepository userRoleRepository, UserMapper userMapper) {
        this.userService = userService;
        this.userHasRoleRepository = userHasRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.userRoles = userRoleRepository.findAll();
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto registerUser(UserDto user) {
        if (userService.isExistByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException(user.getEmail());
        }
        if (!user.isStudent() && !user.isTeacher()) {
            throw new UserHasNoRolesException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userService.save(userMapper.userDtoToUser(user));
        saveUserRoles(user, newUser.getId());

        userMapper.updateUserDto(user, newUser);
        return user;
    }

    @Override
    public AuthEntity login(UserDto user) {
        User foundUser = userService.findByEmail(user.getEmail());
        if (!foundUser.isEmailConfirm()) {
            throw new EmailIsNotConfirmedException();
        }
        if (passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            Map<String, String> map = getAccessRefreshTokens(user.getEmail(), user.getFingerprint(), foundUser.getId());
            AuthEntity auth = new AuthEntity();
            auth.setId(foundUser.getId());
            auth.setRoles(foundUser.getUserRoles());
            auth.setAccess(map.get("access"));
            auth.setExpTime(Long.parseLong(map.get("accessExpTime")));
            auth.setRefresh(map.get("refresh"));
            return auth;
        } else {
            throw new WrongPasswordException(user.getEmail());
        }
    }


    @Override
    public AuthEntity loginWithRefreshToken(String token, String fingerprint) {
        Long userId = refreshTokenService.validateToken(token, fingerprint);
        User user = userService.findById(userId);
        Map<String, String> map = getAccessRefreshTokens(user.getEmail(), fingerprint, user.getId());
        AuthEntity auth = new AuthEntity();
        auth.setId(user.getId());
        auth.setRoles(user.getUserRoles());
        auth.setAccess(map.get("access"));
        auth.setExpTime(Long.parseLong(map.get("accessExpTime")));
        auth.setRefresh(map.get("refresh"));
        return auth;
    }


    private void saveUserRoles(UserDto user, Long userId) {
        if (user.isStudent()) {
            userHasRoleRepository.save(new UserActiveRole(userId, getUserRoleId("STUDENT")));
        }
        if (user.isTeacher()) {
            userHasRoleRepository.save(new UserActiveRole(userId, getUserRoleId("TEACHER")));
        }
    }

    private Map<String, String> getAccessRefreshTokens(String email, String fingerprint, Long userId) {
        Map<String, String> tokens = new HashMap<>(3);
        User user = userService.findByEmail(email);
        String accessToken = accessTokenService.generateAccessTokenByEmail(user);
        tokens.put("access", accessToken);
        tokens.put("accessExpTime", accessTokenService.getExpirationTime(accessToken).toString());
        tokens.put("refresh", refreshTokenService.generateAndSaveRefreshToken(fingerprint, userId).getToken());
        return tokens;
    }

    private Integer getUserRoleId(String name) {
        return userRoles.stream()
                .filter(role -> role.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No role in db with name:" + name))
                .getId();
    }
}
