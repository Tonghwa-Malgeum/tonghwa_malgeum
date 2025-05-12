package com.unstage.core.user.service;

import com.unstage.core.user.entity.Role;
import com.unstage.core.user.entity.User;
import com.unstage.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<User> findByKakaoId(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }

    @Transactional
    public User createUser(String kakaoId, String nickname, String profileImageUrl) {
        User user = User.builder()
                .kakaoId(kakaoId)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user, String nickname, String profileImageUrl) {
        user.update(nickname, profileImageUrl);
        return userRepository.save(user);
    }

    @Transactional
    public User saveOrUpdateUser(String kakaoId, String nickname, String profileImageUrl) {
        Optional<User> existingUser = findByKakaoId(kakaoId);
        
        if (existingUser.isPresent()) {
            return updateUser(existingUser.get(), nickname, profileImageUrl);
        } else {
            return createUser(kakaoId, nickname, profileImageUrl);
        }
    }
}