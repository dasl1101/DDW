package com.web.DDW.domain.item;

import com.web.DDW.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i ORDER BY i.id DESC") //jpa에서 제공하지 않는 메소드는 쿼리로 작성
    List<Item> findAllDesc();
}
