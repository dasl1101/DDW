package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String owner;
    private int view;
    private final String createdDate, modifiedDate;
    private final Long userId;
    private List<CommentDto.Response> comments;
    public PostsResponseDto (Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.owner = entity.getOwner();
        this.view = entity.getView();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
        this.userId = entity.getUser().getId();
        this.comments = entity.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());


    }
}
