package com.practice.shop.DAO;

import com.practice.shop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.validation.constraints.Pattern;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(@Pattern(regexp = ".+@.+\\.[a-zA-Z0-9]+", message = "email should contain @ and .") String email) ;

    User findUserByEmail(@Pattern(regexp = ".+@.+\\.[a-zA-Z0-9]+", message = "email should contain @ and .") String email);
}
