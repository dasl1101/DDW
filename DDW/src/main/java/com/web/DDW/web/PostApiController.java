package com.web.DDW.web;

import com.web.DDW.config.auth.LoginUser;
import com.web.DDW.service.PostsService;
import com.web.DDW.web.dto.PostsResponseDto;
import com.web.DDW.web.dto.PostsSaveRequestDto;
import com.web.DDW.web.dto.PostsUpdateRequestDto;
import com.web.DDW.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostsService postsService;

    /* CREATE */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto, @LoginUser UserDto.Response user, int view) {
        dto.setView(view); //글 수정시 조회수 초기화를 막기위한 코드
        return postsService.save(dto, user.getNickName());
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id,  @LoginUser UserDto.Response user){
        //findById : id값을 파라미터로 해서 select함
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity update(@PathVariable Long id,
                                 @RequestBody PostsUpdateRequestDto requestDto) {
        postsService.update(id, requestDto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
