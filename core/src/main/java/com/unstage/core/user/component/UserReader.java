package com.unstage.core.user.component;

import com.unstage.core.exception.EntityNotFoundException;
import com.unstage.core.user.entity.Role;
import com.unstage.core.user.entity.User;
import com.unstage.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserReader {

    private final UserRepository userRepository;

    public Optional<User> readBy(final String socialUserId) {
        return userRepository.findBySocialUserId(socialUserId);
    }

    public Role readRole(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId))
                .getRole();
    }

    public User read(final Long userId) {
        log.debug("유저 조회 실행 - id={}", userId);

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));

        log.debug("유저 조회 완료 - id={}, name={}", user.getId(), user.getNickname());

        return user;
    }
}
