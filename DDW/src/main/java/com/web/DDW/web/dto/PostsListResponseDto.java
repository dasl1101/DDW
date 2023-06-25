package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import lombok.Getter;


@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String owner;
    private String modifiedDate;
    private int view;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.owner = entity.getOwner();
        this.modifiedDate = entity.getModifiedDate();
        this.view = entity.getView();
    }
}
