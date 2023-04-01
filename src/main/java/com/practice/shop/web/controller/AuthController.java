package com.practice.shop.web.controller;

import com.practice.shop.service.AuthService;
import com.practice.shop.service.EmailService;
import com.practice.shop.service.UserService;
import com.practice.shop.web.dto.UserDto;
import com.practice.shop.web.controller.security.jwt.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtResponse jwtResponse;
    private final UserService userService;
    private final EmailService emailService;

    @PreAuthorize("permitAll()")
    @PostMapping("/registration")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user) throws MessagingException {
        UserDto userDto = authService.registerUser(user);
        emailService.sendConfirmEmail(user.getEmail(),"confirm registration", "click");
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
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

    @PreAuthorize("permitAll()")
    @GetMapping("/confirm/{email}")
    public String confirmEmail(@PathVariable String email) {
        userService.confirmEmail(email);
        return "email is confirmed. You can close the window.";
    }


    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/lessons/student")
    public String testStudent(){
        return "hello, STUDENT";
    }

    @PreAuthorize(" hasRole('TEACHER')")
    @GetMapping("/lessons/teacher")
    public String testTeacher() throws JSONException {
        return (new JSONObject()).put("txt", "hello, Teacher").toString();
    }

}
