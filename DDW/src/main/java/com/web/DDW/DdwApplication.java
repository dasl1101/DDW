package com.web.DDW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
@EnableJpaAuditing //엔티티의 생성/수정 시각 자동으로 기록
public class DdwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdwApplication.class, args);
	}


}
