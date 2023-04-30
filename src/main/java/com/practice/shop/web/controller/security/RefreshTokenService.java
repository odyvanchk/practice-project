package com.practice.shop.web.controller.security;

import com.practice.shop.model.exception.InvalidRefreshTokenException;
import com.practice.shop.model.user.RefreshSession;
import com.practice.shop.repository.RefreshSessionRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Value("${jwt.expiration-time-refresh}")
    private int expTime;

    private final RefreshSessionRepository refreshSessionRepository;

    public RefreshTokenService(RefreshSessionRepository refreshSessionRepository){
        this.refreshSessionRepository = refreshSessionRepository;
    }

    public RefreshSession generateAndSaveRefreshToken(Long id) {
        RefreshSession refreshSession = refreshSessionRepository.findByUserId(id);
        if (refreshSession == null) {
            refreshSession = new RefreshSession();
        }
        refreshSession.setToken(UUID.randomUUID().toString());
        refreshSession.setCreatedAt(Timestamp.from(Instant.now().atZone(ZoneId.of("Etc/UTC")).toInstant()));
        refreshSession.setUserId(id);
        refreshSession.setExpiresIn(Timestamp.from(LocalDateTime.now().plusDays(expTime).atZone(ZoneId.of("Etc/UTC")).toInstant()));
        refreshSession.setFingerprint("fingerprint");

        return refreshSessionRepository.save(refreshSession);
    }

    public long getExpTime(String refresh) {
        RefreshSession refreshSession = refreshSessionRepository.findByToken(refresh);
        return ((refreshSession.getExpiresIn().getTime() - (Timestamp.from(Instant.now()).getTime())) / 1000);
    }

    public Long validateToken(String token, Long id) {
        RefreshSession session = refreshSessionRepository.findByUserId(id);
        if (session == null || !Objects.equals(session.getToken(), token) || session.getExpiresIn().before(Timestamp.from(Instant.now()))) {
            if (session != null) {
                refreshSessionRepository.delete(session);
            }
            throw new InvalidRefreshTokenException();
        }
        return session.getUserId();
    }
}
