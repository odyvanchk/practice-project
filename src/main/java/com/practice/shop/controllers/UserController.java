package com.practice.shop.controllers;

import com.practice.shop.DTO.UserDto;
import com.practice.shop.controllers.security.jwt.JwtResponse;
import com.practice.shop.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final AuthService authService;
    private final JwtResponse jwtResponse;


    @PreAuthorize("permitAll()")
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(authService.registerUser(user), HttpStatus.CREATED);
    }

    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth")
    public  String loginUser(@RequestBody UserDto user, HttpServletResponse response) throws JSONException {
        Map<String, String> tokens = authService.login(user);
        return jwtResponse.getResponse(tokens, user.getFingerprint() , response);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/auth/refresh")
    public String getTokensFromRefresh(@CookieValue(name = "refresh")  String token, @RequestBody Map<String, String> map, HttpServletResponse response) throws JSONException {
        Map<String, String> tokens = authService.loginWithRefreshToken(token, map.get("fingerprint"));
        return jwtResponse.getResponse(tokens, map.get("fingerprint"), response);
    }

    @PreAuthorize(" hasRole('STUDENT')")
    @GetMapping("/lessons/student")
    public String testStudent(){
        return "hello, STUDENT";
    }

    @PreAuthorize(" hasRole('TEACHER')")
    @GetMapping("/lessons/teacher")
    public String testTeacher(){
        return "hello, Teacher";
    }

}
