package com.web.DDW.config.auth;

import com.web.DDW.domain.user.User;
import com.web.DDW.domain.user.UserRepository;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
//Security User Service
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private final HttpSession session;

    // name이 DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + name));

        session.setAttribute("user", new UserDto.Response(user));

        // 시큐리티 세션에 유저정보 저장
        return new CustomUserDetails(user);
    }

}
