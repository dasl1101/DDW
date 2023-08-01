package com.web.DDW.domain.posts;

import com.web.DDW.domain.BaseTimeEntity;
import com.web.DDW.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor //기본 생성자 자동 추가  = Public Post(){}
@Getter
@Entity
public class Posts extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성 전략 = auto_increment
    private Long id;  //Spring Boot에서 Long으로 선언하면 bigint로 타입이 지정된다.

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @Column
    private String owner; //작성자명이라 String으로 변경

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view; //조회수

    @ManyToOne(fetch = FetchType.LAZY)  //User 입장에선 Posts와 다대일 관계이므로 @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //게시글이 삭제되면 댓글도 삭제
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;

    //제목 내용 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
