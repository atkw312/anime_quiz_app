package com.tkwang312.auth.repository;

import com.tkwang312.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// UserRepository inherits save method from JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
