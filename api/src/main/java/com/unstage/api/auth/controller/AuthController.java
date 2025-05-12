package com.unstage.api.auth.controller;

import com.unstage.api.auth.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser == null) {
            return ResponseEntity.ok(UserResponse.builder()
                    .authenticated(false)
                    .build());
        }

        return ResponseEntity.ok(UserResponse.builder()
                .authenticated(true)
                .nickname(oidcUser.getAttribute("nickname"))
                .email(oidcUser.getEmail())
                .profileImageUrl(oidcUser.getAttribute("profile_image_url"))
                .role(oidcUser.getAuthorities().iterator().next().getAuthority())
                .build());
    }

    @GetMapping("/login/success")
    public ResponseEntity<Map<String, String>> loginSuccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout/success")
    public ResponseEntity<Map<String, String>> logoutSuccess() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }
}