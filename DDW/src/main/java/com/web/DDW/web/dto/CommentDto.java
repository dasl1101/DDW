package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Comment;
import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommentDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        private Long id;
        private User user;
        private Posts posts;
        private String comment;
        private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
        private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

        //dto->entity
        public Comment toEntity(){
            Comment comments = Comment.builder()
                    .id(id)
                    .user(user)
                    .posts(posts)
                    .comment(comment)
                    .createdDate(createdDate)
                    .modifiedDate(modifiedDate)
                    .build();

            return comments;
        }

    }

    @RequiredArgsConstructor
    @Getter
    public static class Response{
        private Long id;
        private String comment;
        private String createdDate;
        private String modifiedDate;
        private String nickName;
        private Long postsId;
        private Long userId;

        public Response(Comment comment){
            this.id = comment.getId();
            this.comment = comment.getComment();
            this.createdDate = comment.getCreatedDate();
            this.modifiedDate = comment.getModifiedDate();
            this.nickName = comment.getUser().getNickName();
            this.postsId = comment.getPosts().getId();
            this.userId = comment.getUser().getId();

        }
    }
}
