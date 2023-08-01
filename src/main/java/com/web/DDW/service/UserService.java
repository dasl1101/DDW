package com.web.DDW.service;


import com.web.DDW.domain.posts.Posts;
import com.web.DDW.domain.posts.PostsRepository;
import com.web.DDW.domain.user.User;
import com.web.DDW.domain.user.UserRepository;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final PostsRepository postsRepository;

    //회원가입
    @Transactional
    public void userJoin(UserDto.Request dto) {
        dto.encryptPassword(encoder.encode((dto.getPassword())));

        userRepository.save(dto.toEntity());
    }


    // 회원가입 시, 유효성 검사 및 중복 체크
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        // 유효성 검사, 중복 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            System.out.println(":::::::::::::::validKeyName : "+ validKeyName);
            System.out.println(":::::::::::::::error.getDefaultMessage() : "+ error.getDefaultMessage());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // 회원수정 (dirty checking) */
    @Transactional
    public void modify(UserDto.Request dto) {
        User user = userRepository.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPassword = encoder.encode(dto.getPassword());
        user.modify(dto.getNickName(), encPassword);
    }

    //회원 탈퇴 및 게시글 삭제
    @Transactional
    public void delete(Long id) {
        //작성한 게시글 있는지 확인 후 삭제
        int postCount = postsRepository.countPostsByUserId(id);
        if(postCount >0){
            postsRepository.deletePostsByUserId(id);
        }

        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + id));
        userRepository.delete(user);
        //JpaRepository에서 delete 메소드를 지원하고 있음
        //엔티티를 파라미터로 삭제할 수도 있고 deleteById 메소드를 이용하면 id로 삭제도 가능

    }

}
