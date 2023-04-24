package com.practice.shop.repository;

import com.practice.shop.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email) ;

    Optional<User> findUserByEmail(String email);

}
