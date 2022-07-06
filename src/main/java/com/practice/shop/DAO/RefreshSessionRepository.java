package com.practice.shop.DAO;

import com.practice.shop.models.RefreshSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshSessionRepository extends JpaRepository<RefreshSession, Long> {
    boolean existsRefreshSessionByFingerprint(String fingerprint);
    RefreshSession findRefreshSessionByFingerprint(String fingerprint);
}
