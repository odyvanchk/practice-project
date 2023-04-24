package com.practice.shop.web.controller.security.jwt;

import com.practice.shop.model.user.AuthEntity;
import com.practice.shop.web.dto.AuthDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JwtResponse {

    private final RefreshTokenService refreshTokenService;

    public AuthDto getResponse(AuthEntity auth, String fingerprint, HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh", auth.getRefresh());
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/users");
        cookie.setMaxAge((int) refreshTokenService.getExpTime(fingerprint));

        response.addCookie(cookie);

        return new AuthDto(auth.getAccess(), auth.getExpTime(), auth.getId(), auth.getRoles());
    }
}
