package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String owner;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.owner = entity.getOwner();
        this.modifiedDate = entity.getModifiedDate();
    }
}
