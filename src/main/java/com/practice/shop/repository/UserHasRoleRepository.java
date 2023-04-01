package com.practice.shop.repository;

import com.practice.shop.model.user.UserActiveRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserHasRoleRepository extends JpaRepository<UserActiveRole, Integer> {

    boolean existsByUseridAndRoleId(Long userid, Integer roleId);
}
