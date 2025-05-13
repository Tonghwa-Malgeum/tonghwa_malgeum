package com.unstage.core.user.component;

import com.unstage.core.user.entity.Role;
import com.unstage.core.user.entity.User;
import com.unstage.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserWriter {

    private final UserRepository userRepository;

    public Long save(final String socialUserId, final String nickname, final String profileImageUrl) {
        return userRepository.save(
                User.builder()
                        .socialUserId(socialUserId)
                        .nickname(nickname)
                        .profileImageUrl(profileImageUrl)
                        .role(Role.USER)
                        .build()
        ).getId();
    }
}
