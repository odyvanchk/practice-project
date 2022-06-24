package com.practice.shop.DAO;

import com.practice.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email) ;
}
