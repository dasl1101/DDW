package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String owner;
    private int view;
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.owner = entity.getOwner();
        this.modifiedDate = entity.getModifiedDate();
        this.view = entity.getView();
    }
}
