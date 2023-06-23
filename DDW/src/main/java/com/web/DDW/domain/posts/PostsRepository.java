package com.web.DDW.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //DB Layer 접근자 = DAO
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //jpa에서 제공하지 않는 메소드는 쿼리로 작성
    List<Posts> findAllDesc();

}
