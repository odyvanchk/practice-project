package com.practice.shop.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "refresh_sessions")
@NoArgsConstructor
@AllArgsConstructor
public class RefreshSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_refresh", nullable = false)
    private int id;

    @Column(name = "id_user", nullable = false)
    private Long userId;

    @Column(name = "refresh_token", nullable = false)
    private String token;

    @Column(name = "expires_in", nullable = false)
    private Timestamp expiresIn;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "fingerprint", nullable = false)
    private String fingerprint;

}