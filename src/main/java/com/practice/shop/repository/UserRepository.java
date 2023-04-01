package com.practice.shop.repository;

import com.practice.shop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email) ;

    Optional<User> findUserByEmail(String email);

  //  boolean existsUserByIdAndUserRolesIn(int id, Collection<Set<UserRole>> userRoles);
}
