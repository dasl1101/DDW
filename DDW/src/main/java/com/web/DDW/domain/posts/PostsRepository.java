package com.web.DDW.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //DB Layer 접근자 = DAO
    //기본적인 CRUD메서드가 자동 생성된다.
}
