package com.web.DDW.domain.user;

import com.web.DDW.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickName; //회원가입 유저의 id, 소셜로그인 유저의 초기값=이메일주소

    private String name;

    @Column(nullable = false,  unique = true)
    private String email;

    @Column
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    //회원정보 수정
    public void modify(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트/기존데이터 보존
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}
