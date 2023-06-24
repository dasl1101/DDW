package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.user.User;
import lombok.*;


//Dto클래스는 Entity와 유사하지만 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
//Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이기에 변경하지 않는 것이 좋고
//Controller에서 사용할 Request와 Response용 Dto는 View를 위한 클래스이기에 자주 변경할 필요가 있다.


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostsSaveRequestDto {
    private Long id;
    private String title;
    private String content;
    private String owner;
    private User user;


    public Posts toEntity() {
            Posts posts = Posts.builder()
                .id(id)
                .title(title)
                .content(content)
                .owner(owner)
                .user(user)
                .build();
        return posts;
    }
}
