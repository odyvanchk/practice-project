package com.practice.shop.repository;

import com.practice.shop.model.user.RefreshSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshSessionRepository extends JpaRepository<RefreshSession, Long> {

    RefreshSession findByToken(String token);

    RefreshSession findByUserId(Long userId);

}
