package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String owner;
    private final Long userId;
    public PostsResponseDto (Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.owner = entity.getOwner();
        this.userId = entity.getUser().getId();
    }
}
