package com.web.DDW.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    // Security
    Optional<User> findByName(String name);

    // OAuth
    Optional<User> findByEmail(String email);

    // user GET
    User findByNickName(String nickName);

    // 중복 검사> 중복인 경우 true, 중복되지 않은경우 false 리턴
    boolean existsByName(String name);
    boolean existsByNickName(String nickName);
    boolean existsByEmail(String email);
}
