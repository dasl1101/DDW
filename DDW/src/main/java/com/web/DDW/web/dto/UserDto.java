package com.web.DDW.web.dto;

import com.web.DDW.domain.user.Role;
import com.web.DDW.domain.user.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserDto {

    //회원 Service 요청(Request) DTO 클래스
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;
        private String name;
        private String password;
        private String email;
        private String google_token;
        private Role role;


        public User toEntity() {
            User user = User.builder()
                    .id(id)
                    .name(name)
                    .password(password)
                    .email(email)
                    .google_token(google_token)
                    .role(role.USER)
                    .build();
            return user;
        }
    }

    //인증된 사용자 정보를 세션에 저장
    //세션 저장용 Dto 클래스 생성

    @Getter
    public static class Response implements Serializable {

        private final Long id;
        private final String name;
        private final String email;
        private final String google_token;
        private final Role role;
        private final String modifiedDate;

        // Entity -> dto
        public Response(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.google_token = user.getGoogle_token();
            this.role = user.getRole();
            this.modifiedDate = user.getModifiedDate();
        }
    }


}
