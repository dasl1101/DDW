package com.web.DDW.domain.item;

import com.web.DDW.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
