package com.practice.shop.web.controller.security.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
@AllArgsConstructor
public class JwtResponse {

    private final RefreshTokenService refreshTokenService;


    public String getResponse(Map<String, String> tokens, String fingerprint, HttpServletResponse response) throws JSONException {
        Cookie cookie = new Cookie("refresh", tokens.get("refresh"));
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setPath("/api/v1/users");
        cookie.setMaxAge(refreshTokenService.getExpTime(fingerprint));

        response.addCookie(cookie);

        return new JSONObject().put("token", tokens.get("access"))
                .put("expTime", tokens.get("accessExpTime"))
                .toString();
    }
}
