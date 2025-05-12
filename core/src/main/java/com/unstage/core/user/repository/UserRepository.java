package com.unstage.core.user.repository;

import com.unstage.core.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByKakaoId(String kakaoId);
    boolean existsByKakaoId(String kakaoId);
}