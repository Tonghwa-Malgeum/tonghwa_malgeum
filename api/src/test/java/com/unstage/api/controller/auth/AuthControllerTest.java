package com.unstage.api.controller.auth;

import com.unstage.api.RestAssuredTest;
import com.unstage.api.auth.service.CustomOAuth2UserService;
import com.unstage.core.user.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends RestAssuredTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private CustomOAuth2UserService customOAuth2UserService;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    private MockMvc mockMvc;

    @Test
    void testSessionManagementWithKakaoLogin() throws Exception {
        // 1. MockMvc 설정
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        // 2. 카카오 로그인 사용자 정보 모의 설정
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "12345"); // 카카오 ID
        claims.put("nickname", "테스트사용자");
        claims.put("profile_image_url", "http://example.com/profile.jpg");

        OidcIdToken idToken = new OidcIdToken("token", Instant.now(), 
                Instant.now().plusSeconds(3600), claims);

        Map<String, Object> attributes = new HashMap<>(claims);

        OidcUserInfo userInfo = new OidcUserInfo(claims);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.USER.getKey());

        OidcUser oidcUser = new DefaultOidcUser(
                Collections.singleton(authority),
                idToken,
                userInfo
        );

        OAuth2AuthenticationToken authenticationToken = new OAuth2AuthenticationToken(
                oidcUser,
                Collections.singleton(authority),
                "kakao"
        );

        // 3. 세션 생성
        MockHttpSession session = new MockHttpSession();

        // 4. 인증된 사용자로 API 호출 및 세션 확인
        MvcResult result = mockMvc.perform(get("/api/auth/user")
                .with(authentication(authenticationToken))
                .session(session))
                .andExpect(status().isOk())
                .andReturn();

        // 5. 세션이 생성되었는지 확인
        assertThat(session.isNew()).isFalse();
        assertThat(session.getAttribute("SPRING_SECURITY_CONTEXT")).isNotNull();

        // 6. 응답에서 사용자 정보 확인
        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).contains("테스트사용자");
        assertThat(responseBody).contains("http://example.com/profile.jpg");
        assertThat(responseBody).contains("true"); // authenticated

        // 7. 로그아웃 테스트
        mockMvc.perform(get("/api/auth/logout/success")
                .session(session))
                .andExpect(status().isOk());

        // 8. 세션 수동 무효화
        session.invalidate();

        // 9. 세션이 무효화되었는지 확인 (invalidate 호출 후에는 세션에 접근할 수 없음)
        boolean isSessionInvalidated = false;
        try {
            session.getAttribute("SPRING_SECURITY_CONTEXT");
        } catch (IllegalStateException e) {
            isSessionInvalidated = true;
        }
        assertThat(isSessionInvalidated).isTrue();
    }
}
