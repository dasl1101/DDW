package com.web.DDW.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    // restTemplate : 스프링에서 http 통신에 유용하게 쓸수 있도록 제공해주는 템플릿
    @Test
    void board_loading() {

        //when
        String body = this.restTemplate.getForObject( "/board/list", String.class);
        //getForObject : 주어진 URL 주소로 HTTP GET 메서드로 객체로 결과를 반환받음


        //then
        assertThat(body).contains("DDW 커뮤니티");
    }

}