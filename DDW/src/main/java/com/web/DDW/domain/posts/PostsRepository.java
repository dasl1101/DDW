package com.web.DDW.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //DB Layer 접근자 = DAO
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //jpa에서 제공하지 않는 메소드는 쿼리로 작성
    //select p<-왜이렇게 쓰는지 알아보기
    List<Posts> findAllDesc();

    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(@Param("id")Long id); //@Param을 안써서 오류남...


}
