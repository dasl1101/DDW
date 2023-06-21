package com.web.DDW.domain.posts;

import com.web.DDW.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String owner; //작성자명이라 String으로 변경


    @Builder //빌더 패턴 클래스 생성
    //빌더를 통해 최종 값을 채운 후 DB삽입
    public Posts(String title, String content, String owner){
        this.title = title;
        this.content = content;
        this.owner = owner;
    }

    //제목 내용 수정
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
