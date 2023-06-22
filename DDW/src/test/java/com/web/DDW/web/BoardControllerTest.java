package com.web.DDW.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void board_loading() {
        //when
        String body = this.restTemplate.getForObject("board/list", String.class);
        // restTemplate : 스프링에서 http 통신에 유용하게 쓸수 있도록 제공해주는 템플릿

        //then
        assertThat(body).contains("DDW 커뮤니티");
    }

}