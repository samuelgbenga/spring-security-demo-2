package com.samuel.security.demo2.app.repository;

import com.samuel.security.demo2.app.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
     Optional<UserEntity> findByEmail(String email);
}
