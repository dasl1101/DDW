package com.web.DDW.web.dto;

import com.web.DDW.domain.user.Role;
import com.web.DDW.domain.user.User;
import lombok.*;

import javax.validation.constraints.Email;
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

        @NotBlank(message = "아이디는 필수 입력값입니다.")
        @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
        private String name; //아이디

        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        private String nickName;
       // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$",
       //         message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
        private String password;

        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Email (message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        private Role role;

        // 암호화 처리 한 비밀번호를 저장하기 위한 encryptPassword 메소드
        public void encryptPassword(String BCryptpassword) {
            this.password = BCryptpassword;
        }

        public User toEntity() {
            User user = User.builder()
                    .id(id)
                    .nickName(nickName)
                    .name(name)
                    .password(password)
                    .email(email)
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
        private final String nickName;
        private final String name;
        private final String email;
        private final Role role;
        private final String modifiedDate;

        // Entity -> dto
        public Response(User user) {
            this.id = user.getId();
            this.nickName = user.getNickName();
            this.name = user.getName();
            this.email = user.getEmail();
            this.role = user.getRole();
            this.modifiedDate = user.getModifiedDate();
        }

    }


}
