package com.practice.shop.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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