package com.web.DDW.config.auth;

import com.web.DDW.config.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    //static 관련설정은 무시
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers( "/css/**", "/js/**", "/img/**");
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().ignoringAntMatchers("/api/**") // REST API 사용 예외처리
                .and()
                .authorizeRequests() //authorizeRequests가 선언되어야만 antMatchers옵션을 사용가능
                .antMatchers("/", "/auth/**", "/board/list","/board/posts-view").permitAll() //전체유저 열람권한
                .anyRequest().authenticated() //나머지는 인증된 사용자(로그인한 사용자)에게만 열람가능
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .defaultSuccessUrl("/board/list")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint() // OAuth2 로그인 성공 후 가져올 설정들
                .userService(customOAuth2UserService); // 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
    }

}
