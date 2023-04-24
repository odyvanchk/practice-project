package com.practice.shop.web.controller.security.jwt;

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

    public RefreshSession generateAndSaveRefreshToken(String fingerprint, Long id) {
        RefreshSession refreshSession = refreshSessionRepository.findRefreshSessionByFingerprint(fingerprint);
        if (refreshSession == null) {
            refreshSession = new RefreshSession();
        }
        refreshSession.setToken(UUID.randomUUID().toString());
        refreshSession.setCreatedAt(Timestamp.from(Instant.now().atZone(ZoneId.of("Etc/UTC")).toInstant()));
        refreshSession.setUserId(id);
        refreshSession.setExpiresIn(Timestamp.from(LocalDateTime.now().plusDays(expTime).atZone(ZoneId.of("Etc/UTC")).toInstant()));
        refreshSession.setFingerprint(fingerprint);

        return refreshSessionRepository.save(refreshSession);
    }

    public long getExpTime(String fingerprint) {
        RefreshSession refreshSession = refreshSessionRepository.findRefreshSessionByFingerprint(fingerprint);
        return ((refreshSession.getExpiresIn().getTime() - (Timestamp.from(Instant.now()).getTime())) / 1000);
    }

    public Long validateToken(String token, String fingerprint) {
        RefreshSession session = refreshSessionRepository.findRefreshSessionByFingerprint(fingerprint);
        if (session == null || !Objects.equals(session.getToken(), token) || session.getExpiresIn().before(Timestamp.from(Instant.now()))) {
            if (session != null) {
                refreshSessionRepository.delete(session);
            }
            throw new InvalidRefreshTokenException();
        }
        return session.getUserId();
    }
}
