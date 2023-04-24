package com.practice.shop.web.controller;

import com.practice.shop.model.user.AuthEntity;
import com.practice.shop.service.AuthService;
import com.practice.shop.service.EmailService;
import com.practice.shop.service.UserService;
import com.practice.shop.web.controller.security.jwt.JwtResponse;
import com.practice.shop.web.dto.AuthDto;
import com.practice.shop.web.dto.UserDto;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public AuthDto loginUser(@RequestBody UserDto user, HttpServletResponse response) throws JSONException {
        AuthEntity auth = authService.login(user);
        return jwtResponse.getResponse(auth, user.getFingerprint() , response);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/auth/refresh")
    public AuthDto getTokensFromRefresh(@CookieValue(name = "refresh")  String token, @RequestBody Map<String, String> map, HttpServletResponse response) throws JSONException {
        AuthEntity auth = authService.loginWithRefreshToken(token, map.get("fingerprint"));
        return jwtResponse.getResponse(auth, map.get("fingerprint"), response);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/confirm/{email}")
    public String confirmEmail(@PathVariable String email) {
        userService.confirmEmail(email);
        return "email is confirmed. You can close the window.";
    }


    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/lessons/student")
    public String testStudent() throws JSONException {
        return (new JSONObject()).put("txt", "hello, stud").toString();    }

    @PreAuthorize(" hasRole('TEACHER')")
    @GetMapping("/lessons/teacher")
    public String testTeacher() throws JSONException {
        return (new JSONObject()).put("txt", "hello, Teacher").toString();
    }

}
