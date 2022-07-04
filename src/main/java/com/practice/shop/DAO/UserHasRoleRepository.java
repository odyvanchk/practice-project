package com.practice.shop.DAO;

import com.practice.shop.models.UserActiveRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHasRoleRepository extends JpaRepository<UserActiveRole, Integer> {

    boolean existsByUseridAndRoleId(Integer userid, Integer roleId);
}
