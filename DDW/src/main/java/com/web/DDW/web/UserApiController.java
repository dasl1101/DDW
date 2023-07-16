package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.domain.user.User;
import com.web.DDW.domain.user.UserRepository;
import com.web.DDW.service.UserService;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor

@RestController
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @PutMapping("/api/v1/user")
    public ResponseEntity<String> modify(@RequestBody UserDto.Request dto) {
        userService.modify(dto);

        // 변경된 세션 등록
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getName(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}