package com.web.DDW.web.dto;

import com.web.DDW.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Dto클래스는 Entity와 유사하지만 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.
//Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스이기에 변경하지 않는 것이 좋고
//Controller에서 사용할 Request와 Response용 Dto는 View를 위한 클래스이기에 자주 변경할 필요가 있다.


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String owner;

    @Builder
    public PostsSaveRequestDto(String title, String content, String owner) {
        this.title = title;
        this.content = content;
        this.owner = owner;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .owner(owner)
                .build();
    }
}
