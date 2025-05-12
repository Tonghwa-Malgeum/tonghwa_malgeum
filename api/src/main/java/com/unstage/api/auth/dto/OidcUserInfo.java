package com.unstage.api.auth.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OidcUserInfo {
    private String id;
    private String nickname;
//    private String email;
    private String profileImageUrl;

    public static OidcUserInfo fromAttributes(Map<String, Object> attributes) {
        return OidcUserInfo.builder()
                .id(String.valueOf(attributes.get("sub")))
                .nickname((String) attributes.get("nickname"))
//                .email((String) kakaoAccount.get("email"))
                .profileImageUrl((String) attributes.get("picture"))
                .build();

//        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
//        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
//
//        return OidcUserInfo.builder()
//                .id(String.valueOf(attributes.get("id")))
//                .nickname((String) profile.get("nickname"))
//                .email((String) kakaoAccount.get("email"))
//                .profileImageUrl((String) profile.get("profile_image_url"))
//                .build();
    }
}