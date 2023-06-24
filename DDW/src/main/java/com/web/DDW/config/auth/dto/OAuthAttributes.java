package com.web.DDW.config.auth.dto;

import com.web.DDW.domain.user.Role;
import com.web.DDW.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

//OAuth DTO Class
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String nickName;
    private String name;
    private String email;
    private Role role;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String nickName, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
    }
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .nickName((String)attributes.get("email"))
                .email((String)attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .nickName(email) //소셜로그인유저 닉네임 기본값은 이메일(나중에 수정가능하게 만들기)
                .email(email)
                .role(Role.SOCIAL)
                .build();
    }
}
