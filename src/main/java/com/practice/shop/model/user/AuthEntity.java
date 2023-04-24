package com.practice.shop.model.user;

import java.util.Set;
import lombok.Data;

/**
 * @author Ermakovich Kseniya
 */
@Data
public class AuthEntity {

    private String access;
    private long expTime;
    private String refresh;
    private Long id;
    private Set<UserRole> roles;
}
