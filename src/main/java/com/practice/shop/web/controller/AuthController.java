package com.practice.shop.web.controller;

import com.practice.shop.model.user.AuthEntity;
import com.practice.shop.service.AuthService;
import com.practice.shop.service.EmailService;
import com.practice.shop.service.UserService;
import com.practice.shop.web.controller.security.JwtResponse;
import com.practice.shop.web.dto.AuthDto;
import com.practice.shop.web.dto.UserDto;
import jakarta.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtResponse jwtResponse;
    private final UserService userService;

    @PreAuthorize("permitAll()")
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) {
        UserDto userDto = authService.registerUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/auth")
    public AuthDto loginUser(@RequestBody UserDto user, HttpServletResponse response) {
        AuthEntity auth = authService.login(user);
        return jwtResponse.getResponse(auth, response);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/auth/refresh")
    public AuthDto getTokensFromRefresh(@CookieValue(name = "refresh")  String token,
                                        @RequestBody Long id, HttpServletResponse response) {
        AuthEntity auth = authService.loginWithRefreshToken(token, id);
        return jwtResponse.getResponse(auth, response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/confirm/{email}")
    public String confirmEmail(@PathVariable String email) {
        userService.confirmEmail(email);
        return "email is confirmed. You can close the window.";
    }

}
