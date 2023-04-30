package com.practice.shop.web.dto;

import com.practice.shop.model.user.UserRole;
import java.util.Set;

/**
 * @author Ermakovich Kseniya
 */
public record AuthDto (

        String access,
        long expTime,
        Long id,
        Set<UserRole> roles

) {}
