package com.practice.shop.web.controller.security.jwt;

import com.practice.shop.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AccessTokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration-time-access}")
    private int expTime;

    public String generateAccessTokenByEmail(User user) {
        Date date = Date.from(LocalDateTime.now().plusMinutes(expTime).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("password", user.getPassword())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public boolean validateToken(String token) throws JwtException {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
    }

    public Long getExpirationTime(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        System.out.println(claims.getSubject());
        return claims.getExpiration().getTime();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
