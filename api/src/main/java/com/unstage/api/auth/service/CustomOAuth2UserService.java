package com.unstage.api.auth.service;

import com.unstage.api.auth.dto.OidcUserInfo;
import com.unstage.core.user.entity.User;
import com.unstage.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private final UserService userService;
    private final OidcUserService delegate = new OidcUserService();

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("여기는?");
        log.debug("user idToken: {}", userRequest.getIdToken().getSubject());

        OidcUser oidcUser = delegate.loadUser(userRequest);
        
        // Extract user info from the ID token claims
        Map<String, Object> attributes = oidcUser.getAttributes();
        OidcUserInfo userInfo = OidcUserInfo.fromAttributes(attributes);
        
        // Save or update user in the database
        User user = userService.saveOrUpdateUser(
                userInfo.getId(),
                userInfo.getNickname(),
                userInfo.getProfileImageUrl()
        );
        
        // Create authorities based on user role
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getKey());
        
        // Return a new OidcUser with the user's role as authority
        return new DefaultOidcUser(
                Collections.singleton(authority),
                oidcUser.getIdToken(),
                oidcUser.getUserInfo()
        );
    }
}